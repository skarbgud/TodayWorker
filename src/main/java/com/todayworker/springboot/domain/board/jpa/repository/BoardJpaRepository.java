package com.todayworker.springboot.domain.board.jpa.repository;

import com.todayworker.springboot.domain.board.jpa.entity.BoardEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardJpaRepository extends JpaRepository<BoardEntity, Long> {

    Page<BoardEntity> findBoardEntityByOrderByRegDateDesc(Pageable pageable);

    Optional<BoardEntity> findBoardEntityByBno(String bno);

    void deleteBoardEntityByBno(String bno);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE BoardEntity b SET b.cnt = b.cnt +1 WHERE b.bno = :bno")
    int updateBoardEntityCounterForVisit(@Param("bno") String bno);
}
