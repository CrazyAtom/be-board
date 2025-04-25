package com.crazyatom.beboard.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginRequestDto {

	@NotBlank
	private String username;

	@NotBlank
	private String password;
}
