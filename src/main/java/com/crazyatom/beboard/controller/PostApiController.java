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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "게시글 관리", description = "게시글 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostApiController {

	private final PostService postService;

	private String getCurrentUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

	/**
	 * 게시글 전체 목록 조회
	 * @return ResponseEntity<List < PostResponseDto>>
	 */
	@Operation(summary = "게시글 전체 목록 조회", description = "게시글 전체 목록을 조회합니다.")
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
	@Operation(summary = "게시글 단건 조회", description = "게시글 ID로 게시글을 조회합니다.")
	@GetMapping("/{id}")
	public ResponseEntity<PostResponseDto> getPost(
		@Parameter(description = "게시글 ID")
		@PathVariable Long id) {

		PostResponseDto post = postService.getPost(id);
		return ResponseEntity.ok(post);
	}

	/**
	 * 게시글 생성
	 * @param postRequestDto 게시글 생성 요청 DTO
	 * @return ResponseEntity<Long> 생성된 게시글 ID
	 */
	@Operation(summary = "게시글 생성", description = "새로운 게시글을 생성합니다.")
	@PostMapping
	public ResponseEntity<Long> createPost(
		@Parameter(description = "게시글 생성 정보")
		@RequestBody PostRequestDto postRequestDto) {

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
	@Operation(summary = "게시글 수정", description = "게시글 ID로 게시글을 수정합니다.")
	@PutMapping("/{id}")
	public ResponseEntity<Void> updatePost(
		@Parameter(description = "게시글 ID")
		@PathVariable Long id,
		@Parameter(description = "게시글 수정 정보")
		@RequestBody PostRequestDto dto) {

		String username = getCurrentUsername();
		postService.updatePost(id, dto, username);
		return ResponseEntity.ok().build();
	}

	/**
	 * 게시글 삭제
	 * @param id 게시글 ID
	 */
	@Operation(summary = "게시글 삭제", description = "게시글 ID로 게시글을 삭제합니다.")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deletePost(
		@Parameter(description = "게시글 ID")
		@PathVariable Long id) {

		String username = getCurrentUsername();
		postService.deletePost(id, username);
		return ResponseEntity.ok().build();
	}
}
