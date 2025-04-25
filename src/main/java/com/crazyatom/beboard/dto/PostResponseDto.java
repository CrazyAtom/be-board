package com.crazyatom.beboard.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostResponseDto {
	private Long id;
	private String title;
	private String content;
	private String authorName;
	private LocalDateTime createdAt;
}
