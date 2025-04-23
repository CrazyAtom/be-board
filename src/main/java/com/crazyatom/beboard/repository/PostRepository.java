package com.crazyatom.beboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crazyatom.beboard.entity.Post;
import com.crazyatom.beboard.entity.User;

public interface PostRepository extends JpaRepository<Post, Long> {
	List<Post> findByAuthor(User author);
}
