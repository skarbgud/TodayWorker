package com.todayworker.springboot.web.service;

import com.todayworker.springboot.domain.board.es.document.BoardDocument;
import com.todayworker.springboot.domain.board.es.repository.BoardElasticSearchRepository;
import com.todayworker.springboot.domain.board.jpa.entity.BoardEntity;
import com.todayworker.springboot.domain.board.jpa.repository.BoardJpaRepository;
import com.todayworker.springboot.domain.board.vo.BoardDetailVO;
import com.todayworker.springboot.domain.board.vo.BoardVO;
import com.todayworker.springboot.domain.common.PageableRequest;
import com.todayworker.springboot.utils.DateUtils;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
@RequiredArgsConstructor
public class BoardService implements BoardServiceIF {

    @Value("${todayworker.elasticsearch.index.board}")
    private String boardIndexName;

    private final BoardJpaRepository boardJpaRepository;
    private final BoardElasticSearchRepository boardElasticSearchRepository;


    @Transactional(readOnly = true)
    @Override
    public List<BoardVO> getBoardList(PageableRequest request) {
        // 최신 글 부터 페이징해서 가져오기
        // DB에서 Board만 쿼리한다.
        return boardJpaRepository.findBoardEntityByOrderByRegDateDesc(
                PageRequest.of(request.getFromIndex(), request.getPageSize())
            )
            .stream()
            .map(BoardEntity::convertToBoardVO)
            .collect(Collectors.toList());
    }

    @Override
    public BoardDetailVO getBoardDetail(BoardVO boardVO) {
        // TODO : 게시글 단건 조회 인가요? 일단 빈 객체만 리턴하도록하고 정확한 요건이 파악이되면 구현하겠습니다.

        return new BoardDetailVO(); // NOTHING
    }

    @Override
    public String insertBoard(BoardVO boardVO) {

        String uuidWithoutDash = UUID.randomUUID().toString().replace("-", "");

        boardVO.setBno(uuidWithoutDash);
        boardVO.setRegDate(DateUtils.getDatetimeString());

        boardJpaRepository.save(BoardEntity.fromBoardVO(boardVO));

        // TODO : 나중에 DomainEvent 처리로 수정예정
        boardElasticSearchRepository.save(BoardDocument.from(boardVO));

        // vue 라우터 이동을 위한 urlpath(boardType/bno)
        String urlPath = boardVO.getCategoriName() + "/" + boardVO.getBno();

        return urlPath;
    }

    @Override
    public String updateBoard(BoardVO boardVO) {
        // TODO : FE에서 서버 예외에 대한 처리를 다시 봐줘야 될거 같습니다. 기존처럼 되어 있으면 트랜잭션을 커버 할 수 없어요
        // bno
        String bno = boardVO.getBno();
        // id (인덱스 이름 + bno) // TODO : Update와 delete시 기존 id를 바꾸는 로직이 있는데 어떤 이유에서 일까요?
//		String id = boardIndexName + bno;

        if (boardVO.getBoardId() == null) {
            throw new IllegalArgumentException("유효하지 않은 게시글 ID :[NULL]");
        }

        try {
            BoardEntity updateEntity = boardJpaRepository.findById(boardVO.getBoardId()).get();
            updateEntity.updateFromBoardVO(boardVO);
            boardJpaRepository.save(updateEntity);
            boardElasticSearchRepository.save(BoardDocument.from(boardVO));
            // vue 라우터 이동을 위한 urlpath(boardType/bno)
            String urlPath = boardVO.getCategoriName() + "/" + boardVO.getBno();
            return urlPath;
        } catch (Exception ex) {
            // 지금은 일단 try catch로 감싸줬는데,
            throw ex; // ExceptionHandler에게 위임할 예정입니다.
        }
    }

    @Override
    public boolean deleteBoard(BoardVO vo) {
        // TODO : FE에서 서버 예외에 대한 처리를 다시 봐줘야 될거 같습니다. 기존처럼 되어 있으면 트랜잭션을 커버 할 수 없어요

        try {
            boardJpaRepository.deleteById(vo.getBoardId());
            boardElasticSearchRepository.deleteById(BoardDocument.from(vo).getBoardId());
            return true;
        } catch (Exception ex) {
            // 지금은 일단 try catch로 감싸줬는데,
            throw ex; // ExceptionHandler에게 위임할 예정입니다.
        }
    }

}
