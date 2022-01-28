package com.todayworker.springboot.domain.board.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(description = "게시글과 관련된 요청 및 응답정보 처리 Dto(Data Transfer Object) 클래스")
public class BoardVO {

    @ApiModelProperty(name = "Board ID (for DB PK)", dataType = "Long")
    private Long boardId; // board의 PK

    @ApiModelProperty(name = "Board UUID without dash (for business)", dataType = "String")
    private String bno;

    @ApiModelProperty(name = "Board Category Name", dataType = "String")
    private String categoriName;

    @ApiModelProperty(name = "Board Title", dataType = "String")
    private String title;

    @ApiModelProperty(name = "Board Content", dataType = "String")
    private String content;

    @ApiModelProperty(name = "Board 조회 수", dataType = "long")
    private long cnt;

    @ApiModelProperty(name = "Board 작성자", dataType = "String")
    private String user;

    @ApiModelProperty(name = "문자열로 정규화된 Date", dataType = "String")
    private String regDate;

    @ApiModelProperty(name = "투표 목록", dataType = "List")
    List<String> voteList;

    @ApiModelProperty(name = "해시태그 목록", dataType = "List")
    List<String> tagList;

    @ApiModelProperty(name = "댓글 목록", dataType = "List")
    List<ReplyVO> commentList;

    public List<ReplyVO> saveNewComment(ReplyVO replyVO) {
        if (commentList == null) {
            this.commentList = new ArrayList<>();
        }

        this.commentList.add(replyVO);
        return this.commentList;
    }
}
