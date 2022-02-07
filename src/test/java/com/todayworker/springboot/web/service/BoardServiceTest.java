package com.todayworker.springboot.web.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.todayworker.springboot.domain.board.es.document.BoardDocument;
import com.todayworker.springboot.domain.board.es.repository.BoardElasticSearchRepository;
import com.todayworker.springboot.domain.board.jpa.entity.BoardEntity;
import com.todayworker.springboot.domain.board.jpa.entity.CommentEntity;
import com.todayworker.springboot.domain.board.jpa.repository.BoardJpaRepository;
import com.todayworker.springboot.domain.board.jpa.repository.CommentJpaRepository;
import com.todayworker.springboot.domain.board.vo.BoardVO;
import com.todayworker.springboot.domain.board.vo.ReplyVO;
import com.todayworker.springboot.domain.common.dto.PageableRequest;
import com.todayworker.springboot.elasticsearch.helper.ElasticSearchExtension;
import com.todayworker.springboot.utils.DateUtils;
import com.todayworker.springboot.utils.UuidUtils;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(ElasticSearchExtension.class)
@DirtiesContext
@TestInstance(Lifecycle.PER_METHOD)
public class BoardServiceTest {

    @Value("${todayworker.elasticsearch.index.board}")
    private String boardIndexName;

    @Autowired
    BoardService boardService;

    @Autowired
    BoardJpaRepository boardJpaRepository;

    @Autowired
    CommentJpaRepository commentJpaRepository;

    @Autowired
    BoardElasticSearchRepository boardElasticSearchRepository;

    @PersistenceContext
    EntityManager em;

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

    private static final ReplyVO testReply = new ReplyVO(
        1L,
        testBoard.getBno(),
        UuidUtils.generateNoDashUUID(),
        "댓굴",
        "user111",
        DateUtils.getDatetimeString(),
        0L,
        null
    );

    @BeforeEach
    public void setUp() {
        this.em.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
        this.em.createNativeQuery("TRUNCATE TABLE comments RESTART IDENTITY;").executeUpdate();
        this.em.createNativeQuery("ALTER TABLE comments ALTER COLUMN comment_id RESTART WITH 1;")
            .executeUpdate();
        this.em.createNativeQuery("TRUNCATE TABLE boards RESTART IDENTITY;").executeUpdate();
        this.em.createNativeQuery("ALTER TABLE boards ALTER COLUMN id RESTART WITH 1;")
            .executeUpdate();
        this.em.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }

    @Test
    @DisplayName("Board 등록이 정상적으로 되어야 한다.")
    @Transactional
    public void insertBoardTest() {

        assertDoesNotThrow(() -> boardService.insertBoard(testBoard));
        boardJpaRepository.findBoardEntityByBno(testBoard.getBno())
            .ifPresent((boardEntity -> {
                System.out.println("BoardID : " + boardEntity.getId());
                assertEquals(testBoard.getBno(), boardEntity.getBno());
                assertEquals(testBoard.getTitle(), boardEntity.getTitle());
                String esId = "board" + boardEntity.getBno();
                BoardDocument boardDocument = boardElasticSearchRepository.findById(esId).get();

                // ElasticSearch와 동기화 되었는지 확인.
                assertEquals(boardEntity.getBno(), boardDocument.getBno());
                System.out.println(boardDocument.getTitle());
            }));

    }

    @Test
    @DisplayName("페이징 처리된 게시글 리스트가 정상적으로 조회 되어야 한다.")
    @Transactional
    public void getBoardList() {
        List<BoardEntity> boardEntities = boardJpaRepository.saveAll(generateBoardList());
        boardEntities.size();

        PageableRequest pageableRequest = new PageableRequest();
        pageableRequest.setPageSize(10); // 사이즈 10
        pageableRequest.setFromIndex(0); // 시작위치 0
        List<BoardVO> boardList = boardService.getBoardList(pageableRequest);
        assertEquals(10, boardList.size());

        AtomicInteger index = new AtomicInteger(1000); // 1000부터 시작
        boardList.forEach((it) -> {
            // 역순 정렬이므로 가장 높은 값부터 내려가야함.
            assertEquals(index.getAndDecrement(), it.getBoardId());
            System.out.println(it.getBoardId() + " | " + it.getRegDate());
        });

    }

    @Test
    @DisplayName("게시글 단건 조회는 성공해야 하고, 조회수 카운트도 증가가 되어야 한다.")
    @Transactional
    public void findOneBoard_success() {
        BoardEntity savedBoard = boardJpaRepository.save(BoardEntity.fromBoardVO(testBoard));
        BoardVO searchedBoardVO = savedBoard.convertToBoardVO();
        assertDoesNotThrow(() -> boardService.getBoard(searchedBoardVO));
        BoardEntity retrievedBoard = boardJpaRepository.findById(savedBoard.getId()).get();

        assertEquals(testBoard.getTitle(), retrievedBoard.getTitle());
        assertEquals(1L, retrievedBoard.getCnt());

        // TODO : ElasticSearch와 동기화 되었는지 확인코드 작성 필요
    }

    @Test
    @DisplayName("게시글 단건 조회는 성공해야 하고, 대댓글 까지 즉시 조회가 되어야 한다.")
    @Transactional
    public void findOneBoard2_success() {
        BoardEntity savedBoard = boardJpaRepository.save(BoardEntity.fromBoardVO(testBoard));
        CommentEntity rootComment = CommentEntity.fromReplyVO(
            new ReplyVO(
                null,
                testBoard.getBno(),
                UuidUtils.generateNoDashUUID(),
                "댓굴",
                "user111",
                DateUtils.getDatetimeString(),
                0L,
                null
            ),
            savedBoard
        );

        CommentEntity savedRootComment = commentJpaRepository.save(rootComment);

        CommentEntity nestedComment = CommentEntity.fromReplyVO(
            new ReplyVO(
                null,
                testBoard.getBno(),
                UuidUtils.generateNoDashUUID(),
                "댓굴",
                "user111",
                DateUtils.getDatetimeString(),
                savedRootComment.getCommentId(),
                null
            ),
            savedBoard
        );

        CommentEntity savedNestedComment = commentJpaRepository.save(nestedComment);

        BoardVO searchedBoardVO = savedBoard.convertToBoardVO();

        BoardVO boardResult = boardService.getBoard(searchedBoardVO);

        assertAll(
            () -> assertEquals(testBoard.getTitle(), boardResult.getTitle()),
            () -> assertEquals(1L, boardResult.getCnt()),
            () -> assertEquals(savedRootComment.getCommentId(),
                boardResult.getCommentList().get(0).getCommentId()),
            () -> assertEquals(savedNestedComment.getCommentId(),
                boardResult.getCommentList().get(0).getNestedReplies().get(0).getCommentId())
        );
        ;

        // TODO : ElasticSearch와 동기화 되었는지 확인코드 작성 필요
    }

    @Test
    @DisplayName("게시글 단건 조회 수 만큼 조회 카운트도 증가가 되어야 한다.")
    @Transactional
    public void findOneBoardSeveralTries_success() {
        BoardEntity savedBoard = boardJpaRepository.save(BoardEntity.fromBoardVO(testBoard));
        BoardVO searchedBoardVO = savedBoard.convertToBoardVO();

        IntStream.range(1, 10).forEach((it) -> {
            System.out.println("====> current Read count : " + it);
            BoardVO searchedVO = boardService.getBoard(searchedBoardVO);
            assertEquals(it, searchedVO.getCnt()); // 조회한 횟 수 만큼 cnt도 증가가 되어 있어야 한다.
        });
    }

    @Test
    @DisplayName("게시글 수정이 정상적으로 되어야 한다.")
    @Transactional
    public void updateBoard() {
        BoardEntity savedBoard = boardJpaRepository.save(BoardEntity.fromBoardVO(testBoard));
        BoardVO updatedBoard = savedBoard.convertToBoardVO();
        updatedBoard.setTitle("updated!!!");
        assertDoesNotThrow(() -> boardService.updateBoard(updatedBoard));
        BoardEntity retrievedBoard = boardJpaRepository.findById(savedBoard.getId()).get();

        assertNotEquals(testBoard.getTitle(), retrievedBoard.getTitle());
        assertEquals(updatedBoard.getTitle(), retrievedBoard.getTitle());

        // TODO : ElasticSearch와 동기화 되었는지 확인코드 작성 필요
    }

    @Test
    @DisplayName("게시글 수정이 정상적으로 되어야 한다. - 댓글 추가")
    @Transactional
    public void updateBoardForNewComment() {
        BoardEntity savedBoard = boardJpaRepository.save(BoardEntity.fromBoardVO(testBoard));
        BoardVO updatedBoard = savedBoard.convertToBoardVO();
        updatedBoard.saveNewComment(testReply);
        assertDoesNotThrow(() -> boardService.updateBoard(updatedBoard));
        BoardEntity retrievedBoard = boardJpaRepository.findById(savedBoard.getId()).get();
        assertEquals(testReply.getRno(),
            retrievedBoard.getCommentEntities().stream().findFirst().get().getRno());

        // TODO : ElasticSearch와 동기화 되었는지 확인코드 작성 필요
    }

    @Test
    @DisplayName("게시글 삭제가 정상적으로 되어야 한다.")
    @Transactional
    public void deleteBoard() {
        BoardEntity savedBoard = boardJpaRepository.save(BoardEntity.fromBoardVO(testBoard));
        BoardVO deleteBoard = savedBoard.convertToBoardVO();
        assertDoesNotThrow(() -> assertTrue(boardService.deleteBoard(deleteBoard)));
        assertFalse(boardJpaRepository.findById(deleteBoard.getBoardId()).isPresent());
        assertFalse(
            // ElasticSearch와 동기화 되었는지 확인.
            boardElasticSearchRepository.findById(
                    BoardDocument.from(deleteBoard, boardIndexName).getBoardId())
                .isPresent());
    }

    private List<BoardEntity> generateBoardList() {
        AtomicInteger index = new AtomicInteger(0);
        return Stream.generate(() -> BoardEntity.fromBoardVO(new BoardVO(
            null,
            UuidUtils.generateNoDashUUID(),
            "Category" + index.incrementAndGet(),
            "title" + index.get(),
            "content" + index.get(),
            0L,
            "user" + index.get(),
            DateUtils.getDatetimeString(
                Date.from(LocalDateTime
                    .of(index.get() + 1, 1, 1, 1, 1, 1).toInstant(
                        ZoneOffset.UTC))),
            null,
            null,
            null
        ))).limit(1_000).collect(Collectors.toList());
    }
}
