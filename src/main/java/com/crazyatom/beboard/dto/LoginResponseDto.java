package com.crazyatom.beboard.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginResponseDto {
	private String accessToken;
	private String tokenType = "Bearer";
	private String username;
	private String role;
}
