package com.crazyatom.beboard.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crazyatom.beboard.dto.CommentRequestDto;
import com.crazyatom.beboard.dto.CommentResponseDto;
import com.crazyatom.beboard.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "댓글", description = "댓글 API")
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

	private final CommentService commentService;

	private String getCurrentUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

	/**
	 * 특정 게시글의 댓글 조회
	 *
	 * @param postId 게시글 ID
	 * @return 댓글 목록
	 */
	@GetMapping("/{postId}")
	public ResponseEntity<List<CommentResponseDto>> getComments(
		@Parameter(description = "게시글 ID")
		@PathVariable Long postId) {

		List<CommentResponseDto> comments = commentService.getComments(postId);
		return ResponseEntity.ok(comments);
	}

	/**
	 * 댓글 작성
	 *
	 * @param postId 게시글 ID
	 * @param dto    댓글 작성 정보
	 * @return 작성된 댓글 ID
	 */
	@Operation(summary = "댓글 작성", description = "댓글을 작성합니다.")
	@PostMapping("/{postId}")
	public ResponseEntity<Long> createComment(
		@Parameter(description = "게시글 ID")
		@PathVariable Long postId,
		@Parameter(description = "댓글 작성 정보")
		@RequestBody CommentRequestDto dto) {

		String username = getCurrentUsername();
		return ResponseEntity.ok(commentService.createComment(postId, dto, username));
	}

	/**
	 * 댓글 수정
	 *
	 * @param commentId 댓글 ID
	 * @param dto       수정 정보
	 */
	@Operation(summary = "댓글 수정", description = "댓글을 수정합니다.")
	@PutMapping("/{commentId}")
	public ResponseEntity<Void> updateComment(
		@Parameter(description = "댓글 ID")
		@PathVariable Long commentId,
		@Parameter(description = "댓글 수정 정보")
		@RequestBody CommentRequestDto dto) {

		String username = getCurrentUsername();
		commentService.updateComment(commentId, dto, username);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다.")
	@DeleteMapping("/{commentId}")
	public ResponseEntity<Void> deleteComment(
		@Parameter(description = "댓글 ID")
		@PathVariable Long commentId) {

		String username = getCurrentUsername();
		commentService.deleteComment(commentId, username);
		return ResponseEntity.ok().build();
	}
}
