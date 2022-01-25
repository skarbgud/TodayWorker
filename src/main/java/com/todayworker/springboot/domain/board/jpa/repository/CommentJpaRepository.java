package com.todayworker.springboot.domain.board.jpa.repository;

import com.todayworker.springboot.domain.board.jpa.entity.CommentEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {

    Optional<CommentEntity> findCommentEntityByRno(String rno);

    void deleteCommentEntityByRno(String rno);
}
