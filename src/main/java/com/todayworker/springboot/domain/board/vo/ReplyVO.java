package com.todayworker.springboot.domain.board.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ApiModel(description = "댓글과 관련된 요청 및 응답정보 처리 Dto(Data Transfer Object) 클래스")
public class ReplyVO {

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

    @ApiModelProperty(name = "이건 무슨 필드 일까요?", dataType = "Boolean")
    private Boolean isRecomment;

}