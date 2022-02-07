package com.todayworker.springboot.domain.board.jpa.entity;

import com.todayworker.springboot.domain.BaseTimeEntity;
import com.todayworker.springboot.domain.board.vo.BoardVO;
import com.todayworker.springboot.domain.board.vo.ReplyVO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "boards")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity extends BaseTimeEntity {

    @Id // javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String bno;

    @Column
    private String categoryName;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private long cnt;

    @Column
    private String user;

    @Column
    private String regDate;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<CommentEntity> commentEntities;

    public static BoardEntity fromBoardVO(BoardVO boardVO) {
        return new BoardEntity(
            boardVO.getBoardId(),
            boardVO.getBno(),
            boardVO.getCategoriName(),
            boardVO.getTitle(),
            boardVO.getContent(),
            boardVO.getCnt(),
            boardVO.getUser(),
            boardVO.getRegDate(),
            null
        );
    }

    public void updateFromBoardVO(BoardVO boardVO) {
        this.categoryName = boardVO.getCategoriName();
        this.title = boardVO.getTitle();
        this.content = boardVO.getContent();

        // 댓글이 있다면 댓글도 엎여 쳐준다.
        if (boardVO.getCommentList() != null) {
            this.commentEntities = boardVO.getCommentList().stream()
                .map(it -> CommentEntity.fromReplyVO(it, this)).collect(
                    Collectors.toList());
        }

    }

    public void modifyCommentEntitiesFromReply(ReplyVO replyVO) {
        if (this.commentEntities == null) {
            this.commentEntities = new ArrayList<>();
        }

        this.commentEntities.add(CommentEntity.fromReplyVO(replyVO, this));
    }

    public BoardVO convertToBoardVO() {
        if (this.commentEntities == null) {
            return new BoardVO(
                this.id,
                this.bno,
                this.categoryName,
                this.title,
                this.content,
                this.cnt,
                this.user,
                this.regDate,
                null,
                null,
                null
            );
        }

        // 댓글이 존재하는 경우 댓글 까지 List로 리턴.
        return new BoardVO(
            this.id,
            this.bno,
            this.categoryName,
            this.title,
            this.content,
            this.cnt,
            this.user,
            this.regDate,
            null,
            null,
            this.commentEntities.stream().map(CommentEntity::convertToReplyVO)
                .collect(Collectors.toList())
        );
    }
}
