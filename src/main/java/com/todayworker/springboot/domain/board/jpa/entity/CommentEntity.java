package com.todayworker.springboot.domain.board.jpa.entity;

import static com.todayworker.springboot.domain.board.exception.BoardErrorCode.COMMENT_ALREADY_DELETED;

import com.todayworker.springboot.domain.BaseTimeEntity;
import com.todayworker.springboot.domain.board.exception.BoardErrorCode;
import com.todayworker.springboot.domain.board.exception.BoardException;
import com.todayworker.springboot.domain.board.vo.ReplyVO;
import com.todayworker.springboot.domain.common.converter.BooleanStringConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.http.HttpStatus;

@Entity
@Table(name = "comments")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DynamicInsert
@DynamicUpdate
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

    @Column
    @Convert(converter = BooleanStringConverter.class)
    private Boolean isDeleted;

    @ManyToOne
    private BoardEntity board;

    public static CommentEntity fromReplyVO(ReplyVO replyVO, BoardEntity board) {
        return new CommentEntity(
            null,
            replyVO.getRno(),
            replyVO.getParentCommentId(),
            replyVO.getContent(),
            replyVO.getUser(),
            replyVO.getRegDate(),
            replyVO.getIsDeleted(),
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
            this.isDeleted,
            null
        );
    }

    public void changeStatusToDeleted() {
        if (isDeleted) {
            throw new BoardException(
                BoardErrorCode.of(HttpStatus.BAD_REQUEST, COMMENT_ALREADY_DELETED));
        }

        this.isDeleted = true;
    }
}
