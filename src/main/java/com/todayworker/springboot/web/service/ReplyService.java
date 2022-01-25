package com.todayworker.springboot.web.service;

import com.todayworker.springboot.domain.board.jpa.entity.CommentEntity;
import com.todayworker.springboot.domain.board.jpa.repository.BoardJpaRepository;
import com.todayworker.springboot.domain.board.jpa.repository.CommentJpaRepository;
import com.todayworker.springboot.domain.board.vo.ReplyVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
@RequiredArgsConstructor
public class ReplyService implements ReplyServiceIF {
    // TODO : 댓글은 ES가 아닌 DB에서만 관리하도록 하겠습니다.
    // TODO : 서비스 레이어에서는 로직이 실패하면 Exception을 Throw 하도록 하고, FE에 내려주는 에러 응답에 대해서는 ExceptionHandler를 통해서 처리하도록 하고자 합니다.

    private final BoardJpaRepository boardJpaRepository;
    private final CommentJpaRepository commentJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public boolean registerReply(ReplyVO vo) {
        if (vo.getBno() == null) {
            throw new IllegalArgumentException("유효하지 않은 댓글 등록 요청 [bno=null]");
        }

        boardJpaRepository.findBoardEntityByBno(vo.getBno()).ifPresent(it -> {
            it.modifyCommentEntitiesFromReply(vo);
            boardJpaRepository.save(it);
        });

        return true;
    }

    @Override
    public boolean updateReply(ReplyVO vo) {
        if (vo.getBno() == null) {
            throw new IllegalArgumentException("유효하지 않은 댓글 수정 요청 [bno=null]");
        }

        if (vo.getRno() == null) {
            throw new IllegalArgumentException("유효하지 않은 댓글 수정 요청 [rno=null]");
        }

        CommentEntity updateComment = commentJpaRepository.findCommentEntityByRno(vo.getRno())
            .get();
        updateComment.modifyComment(vo);
        commentJpaRepository.save(updateComment);

        return true;
    }

    @Override
    public boolean deleteReply(ReplyVO vo) {
        if (vo.getBno() == null) {
            throw new IllegalArgumentException("유효하지 않은 댓글 삭제 요청 [bno=null]");
        }

        if (vo.getRno() == null) {
            throw new IllegalArgumentException("유효하지 않은 댓글 삭제 요청 [rno=null]");
        }

//		commentJpaRepository.findCommentEntityByRno(vo.getRno());
        commentJpaRepository.deleteCommentEntityByRno(vo.getRno());
        return true;
    }

}
