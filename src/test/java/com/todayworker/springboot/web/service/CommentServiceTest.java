package com.todayworker.springboot.web.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.todayworker.springboot.domain.board.jpa.entity.BoardEntity;
import com.todayworker.springboot.domain.board.jpa.entity.CommentEntity;
import com.todayworker.springboot.domain.board.jpa.repository.BoardJpaRepository;
import com.todayworker.springboot.domain.board.jpa.repository.CommentJpaRepository;
import com.todayworker.springboot.domain.board.vo.BoardVO;
import com.todayworker.springboot.domain.board.vo.ReplyVO;
import com.todayworker.springboot.elasticsearch.helper.ElasticSearchExtension;
import com.todayworker.springboot.utils.DateUtils;
import com.todayworker.springboot.utils.UuidUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(ElasticSearchExtension.class)
@Transactional
public class CommentServiceTest {

    @Autowired
    BoardJpaRepository boardJpaRepository;

    @Autowired
    CommentJpaRepository commentJpaRepository;

    @Autowired
    CommentService replyService;

    private static final BoardVO testBoard = new BoardVO(
        null,
        UuidUtils.generateNoDashUUID(),
        "Category1",
        "title1",
        "content",
        0L,
        "user1",
        DateUtils.getDatetimeString(),
        null,
        null,
        null
    );


    @Test
    @DisplayName("댓글 등록이 정상적으로 되어야 한다.")
    public void registerComment() {
        BoardEntity savedBoard = boardJpaRepository.save(BoardEntity.fromBoardVO(testBoard));
        ReplyVO testReply = new ReplyVO(
            savedBoard.getBno(),
            UuidUtils.generateNoDashUUID(),
            "댓글",
            "댓글러",
            DateUtils.getDatetimeString(),
            true
        );

        replyService.registerReply(testReply);
    }

    @Test
    @DisplayName("댓글 수정이 정상적으로 되어야 한다.")
    public void updateComment() {
        String modifiedContent = "수정된 댓글";
        BoardEntity savedBoard = boardJpaRepository.save(BoardEntity.fromBoardVO(testBoard));
        ReplyVO testReply = new ReplyVO(
            savedBoard.getBno(),
            UuidUtils.generateNoDashUUID(),
            "댓글",
            "댓글러",
            DateUtils.getDatetimeString(),
            true
        );
        savedBoard.modifyCommentEntitiesFromReply(testReply);
        boardJpaRepository.save(savedBoard);

        testReply.setContent(modifiedContent);
        replyService.updateReply(testReply);

        CommentEntity comment = commentJpaRepository.findCommentEntityByRno(testReply.getRno())
            .get();
        assertEquals(modifiedContent, comment.getContent());
    }

    @Test
    @DisplayName("댓글 삭제가 정상적으로 되어야 한다.")
    public void deleteComment() {
        BoardEntity savedBoard = boardJpaRepository.save(BoardEntity.fromBoardVO(testBoard));
        ReplyVO testReply = new ReplyVO(
            savedBoard.getBno(),
            UuidUtils.generateNoDashUUID(),
            "댓글",
            "댓글러",
            DateUtils.getDatetimeString(),
            true
        );
        savedBoard.modifyCommentEntitiesFromReply(testReply);
        boardJpaRepository.save(savedBoard);

        replyService.deleteReply(testReply);
        assertFalse(commentJpaRepository.findCommentEntityByRno(testReply.getRno()).isPresent());
    }
}
