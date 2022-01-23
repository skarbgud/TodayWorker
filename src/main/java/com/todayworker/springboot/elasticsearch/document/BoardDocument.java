package com.todayworker.springboot.elasticsearch.document;

import com.todayworker.springboot.domain.board.BoardVO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "board_t") // 기존 구현 방식과의 충돌을 방지하기 위해 IndexName을 다르게 mapping
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BoardDocument {

    @Id // javax.persistence.Id가 아닌 org.springframework.data.annotation.Id 입니다.
    private String boardId;
    @Field(type= FieldType.Text)
    private String bno;
    @Field(type= FieldType.Text)
    private	String categoryName;
    @Field(type= FieldType.Text)
    private String title;
    @Field(type= FieldType.Text)
    private String content;
    @Field(type= FieldType.Integer)
    private int cnt;
    @Field(type= FieldType.Text)
    private String user;
    @Field(type= FieldType.Text)
    private String regDate;

    public static BoardDocument from(BoardVO vo) {
        return new BoardDocument("board"+ vo.getBno(),
                vo.getBno(),
                vo.getCategoriName(),
                vo.getTitle(),
                vo.getContent(),
                vo.getCnt(),
                vo.getUser(),
                vo.getRegDate()
                );
    }
}
