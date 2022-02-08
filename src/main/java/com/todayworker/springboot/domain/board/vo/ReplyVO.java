package com.todayworker.springboot.domain.board.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "댓글과 관련된 요청 및 응답정보 처리 Dto(Data Transfer Object) 클래스")
public class ReplyVO {

    @ApiModelProperty(name = "댓글(Comment)의 DB PK", dataType = "Long")
    private Long commentId;

    @ApiModelProperty(name = "Board UUID without dash (for business)", dataType = "String")
    private String bno;

    @ApiModelProperty(name = "댓글 UUID without dash", dataType = "String")
    private String rno;

    @ApiModelProperty(name = "댓글 content", dataType = "String")
    private String content;

    @ApiModelProperty(name = "댓글 작성자", dataType = "String")
    private String user;

    @ApiModelProperty(name = "문자열로 정규화된 Date", dataType = "String")
    private String regDate;

    @ApiModelProperty(name = "Parent Comment Id", dataType = "Long")
    private Long parentCommentId;

    @ApiModelProperty(name = "댓글 삭제 여부(필수)", dataType = "Boolean", required = true)
    private Boolean isDeleted;

    private List<ReplyVO> nestedReplies;

    public void prePersist() {
        this.isDeleted = this.isDeleted != null && this.isDeleted; // 임시 추가 로직.
        this.parentCommentId = this.parentCommentId == null ? 0L : this.parentCommentId;
    }

    public ReplyVO convertDeletedReply() {
        if (this.getIsDeleted()) {
            this.setUser("***");
            this.setContent("삭제된 댓글 입니다.");
        }
        return this;
    }
}