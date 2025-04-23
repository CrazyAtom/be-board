package com.crazyatom.beboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PostRequestDto {
	private String title;
	private String content;
}
