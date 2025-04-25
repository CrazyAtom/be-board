package com.crazyatom.beboard.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserSignupRequestDto {

	@NotBlank
	private String username;

	@NotBlank
	private String password;
}
