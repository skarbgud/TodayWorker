package com.todayworker.springboot.domain.board.jpa.repository;

import com.todayworker.springboot.domain.board.jpa.entity.BoardEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJpaRepository extends JpaRepository<BoardEntity, Long> {
    Page<BoardEntity> findBoardEntityByOrderByRegDateDesc(Pageable pageable);
    Optional<BoardEntity> findBoardEntityByBno(String bno);
    void deleteBoardEntityByBno(String bno);
}
