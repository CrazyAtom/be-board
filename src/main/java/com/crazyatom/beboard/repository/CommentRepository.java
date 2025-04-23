package com.crazyatom.beboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crazyatom.beboard.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByPostIdOrderByCreatedAtAsc(Long postId);
}
