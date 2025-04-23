package com.crazyatom.beboard.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crazyatom.beboard.dto.PostRequestDto;
import com.crazyatom.beboard.dto.PostResponseDto;
import com.crazyatom.beboard.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

	private final PostService postService;

	private String getCurrentUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

	/**
	 * 게시글 전체 목록 조회
	 * @return ResponseEntity<List < PostResponseDto>>
	 */
	@GetMapping
	public ResponseEntity<List<PostResponseDto>> getAllPosts() {
		List<PostResponseDto> posts = postService.getAllPosts();
		return ResponseEntity.ok(posts);
	}

	/**
	 * 게시글 단건 조회
	 * @param id 게시글 ID
	 * @return ResponseEntity<PostResponseDto>
	 */
	@GetMapping("/{id}")
	public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
		PostResponseDto post = postService.getPost(id);
		return ResponseEntity.ok(post);
	}

	/**
	 * 게시글 생성
	 * @param postRequestDto 게시글 생성 요청 DTO
	 * @return ResponseEntity<Long> 생성된 게시글 ID
	 */
	@PostMapping
	public ResponseEntity<Long> createPost(@RequestBody PostRequestDto postRequestDto) {
		String username = getCurrentUsername();
		Long id = postService.createPost(postRequestDto, username);
		return ResponseEntity.status(HttpStatus.CREATED).body(id);
	}

	/**
	 * 게시글 수정
	 * @param id 게시글 ID
	 * @param dto 게시글 수정 요청 DTO
	 * @return ResponseEntity<Long> 수정된 게시글 ID
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Long> updatePost(@PathVariable Long id, @RequestBody PostRequestDto dto) {
		String username = getCurrentUsername();
		postService.updatePost(id, dto, username);
		return ResponseEntity.ok().build();
	}

	/**
	 * 게시글 삭제
	 * @param id 게시글 ID
	 */
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePost(@PathVariable Long id) {
		String username = getCurrentUsername();
		postService.deletePost(id, username);
	}
}
