package com.crazyatom.beboard.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crazyatom.beboard.dto.CommentRequestDto;
import com.crazyatom.beboard.dto.CommentResponseDto;
import com.crazyatom.beboard.entity.Comment;
import com.crazyatom.beboard.entity.Post;
import com.crazyatom.beboard.entity.User;
import com.crazyatom.beboard.repository.CommentRepository;
import com.crazyatom.beboard.repository.PostRepository;
import com.crazyatom.beboard.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

	private final CommentRepository commentRepository;
	private final PostRepository postRepository;
	private final UserRepository userRepository;

	public List<CommentResponseDto> getComments(Long postId) {
		return commentRepository.findByPostIdOrderByCreatedAtAsc(postId)
			.stream()
			.map(comment -> CommentResponseDto.builder()
				.id(comment.getId())
				.content(comment.getContent())
				.usernaem(comment.getUser().getUsername())
				.createdAt(comment.getCreatedAt())
				.build()).toList();
	}

	/**
	 * 댓글 생성
	 * @param postId 댓글을 달 게시글 ID
	 * @param dto 댓글 내용
	 * @param username 작성자 username
	 * @return 생성된 댓글 ID
	 */
	public Long createComment(Long postId, CommentRequestDto dto, String username) {
		Post post = postRepository.findById(postId).orElseThrow();
		User user = userRepository.findByUsername(username).orElseThrow();

		Comment comment = Comment.createComment(dto.getContent(), user, post);

		return commentRepository.save(comment).getId();
	}

	/**
	 * 댓글 수정
	 * @param id 댓글 ID
	 * @param dto 수정할 내용
	 * @param username 작성자 username
	 */
	public void updateComment(Long id, CommentRequestDto dto, String username) {
		Comment comment = commentRepository.findById(id).orElseThrow();

		if (!comment.getUser().getUsername().equals(username)) {
			throw new IllegalArgumentException("수정 권한이 없습니다.");
		}

		comment.update(dto.getContent());
	}

	/**
	 * 댓글 삭제
	 * @param id 댓글 ID
	 * @param username 작성자 username
	 */
	public void deleteComment(Long id, String username) {
		Comment comment = commentRepository.findById(id).orElseThrow();

		if (!comment.getUser().getUsername().equals(username)) {
			throw new IllegalArgumentException("삭제 권한이 없습니다.");
		}

		commentRepository.delete(comment);
	}
}
