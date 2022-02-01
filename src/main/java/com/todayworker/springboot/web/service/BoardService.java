package com.todayworker.springboot.web.service;

import com.todayworker.springboot.domain.board.es.document.BoardDocument;
import com.todayworker.springboot.domain.board.es.repository.BoardElasticSearchRepository;
import com.todayworker.springboot.domain.board.exception.BoardErrorCode;
import com.todayworker.springboot.domain.board.exception.BoardException;
import com.todayworker.springboot.domain.board.jpa.entity.BoardEntity;
import com.todayworker.springboot.domain.board.jpa.repository.BoardJpaRepository;
import com.todayworker.springboot.domain.board.vo.BoardVO;
import com.todayworker.springboot.domain.common.dto.PageableRequest;
import com.todayworker.springboot.utils.DateUtils;
import com.todayworker.springboot.utils.UuidUtils;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService implements BoardServiceIF {

    @Value("${todayworker.elasticsearch.index.board}")
    private String boardIndexName;

    private final BoardJpaRepository boardJpaRepository;
    private final BoardElasticSearchRepository boardElasticSearchRepository;


    @Transactional(readOnly = true)
    @Override
    public List<BoardVO> getBoardList(PageableRequest request) {
        return boardJpaRepository.findBoardEntityByOrderByRegDateDesc(
                PageRequest.of(request.parseFromIndexToPageOffset(), request.getPageSize())
            )
            .stream()
            .map(BoardEntity::convertToBoardVO)
            .collect(Collectors.toList());
    }

    @Override
    public BoardVO getBoard(BoardVO boardVO) {
        if (boardVO.getBno() == null || boardVO.getBno().isEmpty()) {
            throw new BoardException(
                BoardErrorCode.of(HttpStatus.BAD_REQUEST, BoardErrorCode.INVALID_BOARD,
                    "게시글 ID(bno)가 Null 일 수는 없습니다."));
        }

        // 조회수를 먼저 Update를 시도
        // update 처리 중 예외가 발생했다 하더라도, 조회는 정상적으로 수행되어야 한다.
        try {
            updateBoardCounter(boardVO.getBno());
        } catch (RuntimeException ex) {
            log.warn("board counter update failed ", ex);
        }

        return boardJpaRepository
            .findBoardEntityByBno(boardVO.getBno())
            .orElseThrow(() -> new BoardException(
                BoardErrorCode.of(HttpStatus.NOT_FOUND, BoardErrorCode.NON_EXIST_BOARD,
                    "[bno : " + boardVO.getBno() + "]")))
            .convertToBoardVO();
    }

    @Override
    public BoardVO insertBoard(BoardVO boardVO) {

        boardVO.setBno(UuidUtils.generateNoDashUUID());
        boardVO.setRegDate(DateUtils.getDatetimeString());

        BoardEntity savedBoardEntity = boardJpaRepository.save(BoardEntity.fromBoardVO(boardVO));

        boardElasticSearchRepository.save(BoardDocument.from(boardVO, boardIndexName));

        return savedBoardEntity.convertToBoardVO();
    }

    @Override
    public BoardVO updateBoard(BoardVO boardVO) {
        if (boardVO.getBno() == null || boardVO.getBno().isEmpty()) {
            throw new BoardException(
                BoardErrorCode.of(HttpStatus.BAD_REQUEST, BoardErrorCode.INVALID_BOARD,
                    "게시글 ID(bno)가 Null 일 수는 없습니다."));
        }

        try {
            BoardEntity updateEntity = boardJpaRepository.findBoardEntityByBno(boardVO.getBno())
                .orElseThrow(() -> new BoardException(
                    BoardErrorCode.of(HttpStatus.NOT_FOUND, BoardErrorCode.NON_EXIST_BOARD,
                        "[bno : " + boardVO.getBno() + "]")));
            updateEntity.updateFromBoardVO(boardVO);
            BoardEntity updatedEntity = boardJpaRepository.save(updateEntity);
            boardElasticSearchRepository.save(BoardDocument.from(boardVO, boardIndexName));
            return updatedEntity.convertToBoardVO();
        } catch (Exception ex) {
            throw new BoardException(BoardErrorCode.of(HttpStatus.INTERNAL_SERVER_ERROR,
                BoardErrorCode.BOARD_TRANSACTION_PROCESSING_ERROR), ex);
        }
    }

    @Override
    public boolean deleteBoard(BoardVO vo) {
        // TODO : FE에서 서버 예외에 대한 처리를 다시 봐줘야 될거 같습니다. 기존처럼 되어 있으면 트랜잭션을 커버 할 수 없어요

        try {
            boardJpaRepository.deleteBoardEntityByBno(vo.getBno());
            boardElasticSearchRepository.deleteById(
                BoardDocument.from(vo, boardIndexName).getBno());
            return true;
        } catch (Exception ex) {
            throw new BoardException(BoardErrorCode.of(HttpStatus.INTERNAL_SERVER_ERROR,
                BoardErrorCode.BOARD_TRANSACTION_PROCESSING_ERROR), ex);
        }
    }

    private void updateBoardCounter(String bno) {
        if (boardJpaRepository
            .updateBoardEntityCounterForVisit(bno) != 1) {
            throw new BoardException(
                BoardErrorCode.of(HttpStatus.INTERNAL_SERVER_ERROR,
                    BoardErrorCode.BOARD_UPDATE_ERROR_FOR_COUNTER)
            );
        }
    }

}
