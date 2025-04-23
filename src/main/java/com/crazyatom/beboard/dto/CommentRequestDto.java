package com.crazyatom.beboard.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentRequestDto {
	private String content;
}
