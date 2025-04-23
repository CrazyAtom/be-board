package com.crazyatom.beboard.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentResponseDto {
	private Long id;
	private String content;
	private String usernaem;
	private LocalDateTime createdAt;
}
