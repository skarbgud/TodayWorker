package com.todayworker.springboot.domain.board.jpa.entity;

import com.todayworker.springboot.domain.BaseTimeEntity;
import com.todayworker.springboot.domain.board.exception.BoardErrorCode;
import com.todayworker.springboot.domain.board.exception.BoardException;
import com.todayworker.springboot.domain.board.vo.ReplyVO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommentEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(unique = true)
    private String rno;

    @Column
    private Long parentCommentId;

    @Column
    private String content;

    @Column
    private String user;

    @Column
    private String regDate;

    @ManyToOne
    private BoardEntity board;
    
    @PrePersist
    public void prePersist() {
        this.parentCommentId = this.parentCommentId == null ? 0L : this.parentCommentId;
    }

    public static CommentEntity fromReplyVO(ReplyVO replyVO, BoardEntity board) {
        return new CommentEntity(
            null,
            replyVO.getRno(),
            replyVO.getParentCommentId(),
            replyVO.getContent(),
            replyVO.getUser(),
            replyVO.getRegDate(),
            board
        );
    }

    public void modifyComment(ReplyVO replyVO) {
        if (!replyVO.getUser().equals(this.user)) {
            throw new BoardException(
                BoardErrorCode.of(HttpStatus.FORBIDDEN, BoardErrorCode.INVALID_WRITER,
                    "글 작성자만 수정 가능합니다."));
        }

        this.content = replyVO.getContent();
    }

    public ReplyVO convertToReplyVO() {
        return new ReplyVO(
            this.commentId,
            this.board.getBno(),
            this.rno,
            this.content,
            this.user,
            this.regDate,
            this.parentCommentId,
            null
        );
    }
}
