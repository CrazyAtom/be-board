package com.crazyatom.beboard.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crazyatom.beboard.dto.PostRequestDto;
import com.crazyatom.beboard.dto.PostResponseDto;
import com.crazyatom.beboard.entity.Post;
import com.crazyatom.beboard.entity.User;
import com.crazyatom.beboard.repository.PostRepository;
import com.crazyatom.beboard.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

	private final PostRepository postRepository;
	private final UserRepository userRepository;

	public Long createPost(PostRequestDto dto, String userName) {
		User user = userRepository.findByUsername(userName)
			.orElseThrow(() -> new RuntimeException("User not found"));

		Post post = Post.createPost(user, dto);

		return postRepository.save(post).getId();
	}

	public List<PostResponseDto> getAllPosts() {
		return postRepository.findAll().stream()
			.map(post -> PostResponseDto.builder()
				.id(post.getId())
				.title(post.getTitle())
				.content(post.getContent())
				.authorName(post.getAuthor().getUsername())
				.createdAt(post.getCreatedAt())
				.build()).toList();
	}

	public PostResponseDto getPost(Long id) {
		Post post = postRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Post not found"));

		return PostResponseDto.builder()
			.id(post.getId())
			.title(post.getTitle())
			.content(post.getContent())
			.authorName(post.getAuthor().getUsername())
			.createdAt(post.getCreatedAt())
			.build();
	}

	public void deletePost(Long id, String userName) {
		Post post = postRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Post not found"));

		if (!post.getAuthor().getUsername().equals(userName)) {
			throw new RuntimeException("삭제 권한이 없습니다.");
		}

		postRepository.delete(post);
	}

	public void updatePost(Long id, PostRequestDto dto, String userName) {
		Post post = postRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Post not found"));

		if (!post.getAuthor().getUsername().equals(userName)) {
			throw new RuntimeException("수정 권한이 없습니다.");
		}

		post.update(dto.getTitle(), dto.getContent());

		postRepository.save(post);
	}
}
