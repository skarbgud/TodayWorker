package com.todayworker.springboot.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.todayworker.springboot.domain.board.exception.BoardErrorCode;
import com.todayworker.springboot.domain.board.exception.BoardException;
import com.todayworker.springboot.domain.board.vo.BoardVO;
import com.todayworker.springboot.domain.board.vo.ReplyVO;
import com.todayworker.springboot.domain.common.dto.PageableRequest;
import com.todayworker.springboot.elasticsearch.helper.ElasticSearchExtension;
import com.todayworker.springboot.utils.DateUtils;
import com.todayworker.springboot.utils.UuidUtils;
import com.todayworker.springboot.web.service.BoardService;
import com.todayworker.springboot.web.service.CommentService;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith({MockitoExtension.class, ElasticSearchExtension.class})
@Import(HttpEncodingAutoConfiguration.class)
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardService boardService;

    @MockBean
    private CommentService commentService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("게시글 정상 등록 RestAPI")
    public void registerBoardSuccess() throws Exception {
        when(boardService.insertBoard(any()))
            .thenReturn(
                new BoardVO(
                    1L,
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
                )
            );

        BoardVO testBoard = new BoardVO(
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

        mockMvc.perform(
                post("/board/insert-board.do")
                    .content(objectMapper.writeValueAsBytes(testBoard))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @DisplayName("게시글 다건 조회(페이징)")
    public void getBoardListPaging() throws Exception {

        PageableRequest pageableRequest = new PageableRequest();
        pageableRequest.setPageSize(10);
        pageableRequest.setFromIndex(0);

        when(boardService.getBoardList(any()))
            .thenReturn(
                Arrays.asList(
                    new BoardVO(
                        1L,
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
                    ),
                    new BoardVO(
                        2L,
                        UuidUtils.generateNoDashUUID(),
                        "Category2",
                        "title2",
                        "content",
                        0L,
                        "user2",
                        DateUtils.getDatetimeString(),
                        null,
                        null,
                        null
                    ),
                    new BoardVO(
                        3L,
                        UuidUtils.generateNoDashUUID(),
                        "Category3",
                        "title3",
                        "content",
                        0L,
                        "user3",
                        DateUtils.getDatetimeString(),
                        null,
                        null,
                        null
                    )
                )
            );

        mockMvc.perform(
                post("/board/get-board-list.do")
                    .content(objectMapper.writeValueAsBytes(pageableRequest))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("게시글 단건 조회 성공")
    public void getBoardSuccess() throws Exception {

        BoardVO testBoard = new BoardVO(
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

        testBoard.setBoardId(1L);

        when(boardService.getBoard(any()))
            .thenReturn(
                new BoardVO(
                    1L,
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
                )
            );

        mockMvc.perform(
                post("/board/get-board-detail.do")
                    .content(objectMapper.writeValueAsBytes(testBoard))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("게시글 단건 조회시 댓글도 함께 조회 성공")
    public void getBoardwithCommentsSuccess() throws Exception {

        BoardVO testBoard = new BoardVO(
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

        testBoard.setBoardId(1L);

        when(boardService.getBoard(any()))
            .thenReturn(
                new BoardVO(
                    1L,
                    testBoard.getBno(),
                    "Category1",
                    "title1",
                    "content",
                    0L,
                    "user1",
                    DateUtils.getDatetimeString(),
                    null,
                    null,
                    Arrays.asList(
                        new ReplyVO(
                            1L,
                            testBoard.getBno(),
                            UuidUtils.generateNoDashUUID(),
                            "content",
                            "user22",
                            DateUtils.getDatetimeString(),
                            0L,
                            false,
                            null
                        ),
                        new ReplyVO(
                            2L,
                            testBoard.getBno(),
                            UuidUtils.generateNoDashUUID(),
                            "content111",
                            "user22",
                            DateUtils.getDatetimeString(),
                            0L,
                            false,
                            null
                        ),
                        new ReplyVO(
                            3L,
                            testBoard.getBno(),
                            UuidUtils.generateNoDashUUID(),
                            "content222",
                            "user22",
                            DateUtils.getDatetimeString(),
                            0L,
                            false,
                            null
                        )
                    )
                )
            );

        mockMvc.perform(
                post("/board/get-board-detail.do")
                    .content(objectMapper.writeValueAsBytes(testBoard))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("게시글 수정 성공")
    public void updateBoardSuccess() throws Exception {

        BoardVO testBoard = new BoardVO(
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

        testBoard.setBoardId(1L);

        when(boardService.updateBoard(any()))
            .thenReturn(
                new BoardVO(
                    1L,
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
                )
            );

        mockMvc.perform(
                post("/board/update-board.do")
                    .content(objectMapper.writeValueAsBytes(testBoard))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @DisplayName("게시글 수정 실패")
    public void updateBoardFail() throws Exception {

        BoardVO testBoard = new BoardVO(
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

        when(boardService.updateBoard(any()))
            .thenThrow(new BoardException(
                BoardErrorCode.of(HttpStatus.NOT_FOUND, BoardErrorCode.NON_EXIST_BOARD,
                    "[bno : " + testBoard.getBno() + "]")));

        mockMvc.perform(
                post("/board/update-board.do")
                    .content(objectMapper.writeValueAsBytes(testBoard))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("게시글 삭제 성공")
    public void deleteBoardSuccess() throws Exception {

        BoardVO testBoard = new BoardVO(
            1L,
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

        when(boardService.deleteBoard(any()))
            .thenReturn(
                true
            );

        mockMvc.perform(
                post("/board/delete-board.do")
                    .content(objectMapper.writeValueAsBytes(testBoard))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk());

    }


    @Test
    @DisplayName("댓글 등록 성공")
    public void registerCommentSuccess() throws Exception {

        ReplyVO testReplyVo = new ReplyVO(
            1L,
            UuidUtils.generateNoDashUUID(),
            UuidUtils.generateNoDashUUID(),
            "content1",
            "user1",
            DateUtils.getDatetimeString(),
            0L,
            false,
            null
        );

        when(commentService.registerReply(any()))
            .thenReturn(
                true
            );

        mockMvc.perform(
                post("/board/regist-reply.do")
                    .content(objectMapper.writeValueAsBytes(testReplyVo))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("댓글 수정 성공")
    public void modifyCommentSuccess() throws Exception {

        ReplyVO testReplyVo = new ReplyVO(
            1L,
            UuidUtils.generateNoDashUUID(),
            UuidUtils.generateNoDashUUID(),
            "content1",
            "user1",
            DateUtils.getDatetimeString(),
            0L,
            false,
            null
        );

        when(commentService.updateReply(any()))
            .thenReturn(
                true
            );

        mockMvc.perform(
                post("/board/update-reply.do")
                    .content(objectMapper.writeValueAsBytes(testReplyVo))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("댓글 삭제 성공")
    public void deleteCommentSuccess() throws Exception {

        ReplyVO testReplyVo = new ReplyVO(
            1L,
            UuidUtils.generateNoDashUUID(),
            UuidUtils.generateNoDashUUID(),
            "content1",
            "user1",
            DateUtils.getDatetimeString(),
            0L,
            false,
            null
        );

        when(commentService.deleteReply(any()))
            .thenReturn(
                true
            );

        mockMvc.perform(
                post("/board/delete-reply.do")
                    .content(objectMapper.writeValueAsBytes(testReplyVo))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
