package com.crazyatom.beboard.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostRequestDto {
	private String title;
	private String content;
}
