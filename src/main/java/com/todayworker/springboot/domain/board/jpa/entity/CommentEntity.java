package com.todayworker.springboot.domain.board.jpa.entity;

import com.todayworker.springboot.domain.BaseTimeEntity;
import com.todayworker.springboot.domain.board.vo.ReplyVO;
import javax.persistence.Column;
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
    private String content;

    @Column
    private String user;

    @Column
    private String regDate;

    @ManyToOne
    private BoardEntity board;

    public static CommentEntity fromReplyVO(ReplyVO replyVO, BoardEntity board) {
        return new CommentEntity(
            null,
            replyVO.getRno(),
            replyVO.getContent(),
            replyVO.getUser(),
            replyVO.getRegDate(),
            board
        );
    }

    public void modifyComment(ReplyVO replyVO) {
        if (!replyVO.getUser().equals(this.user)) {
            throw new RuntimeException("글 작성자만 수정이 가능합니다.");
        }

        this.content = replyVO.getContent();
    }

    public ReplyVO convertToReplyVO() {
        return new ReplyVO(
            this.board.getBno(),
            this.rno,
            this.content,
            this.user,
            this.regDate,
            true
        );
    }
}
