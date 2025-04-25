package com.crazyatom.beboard.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crazyatom.beboard.dto.LoginRequestDto;
import com.crazyatom.beboard.dto.LoginResponseDto;
import com.crazyatom.beboard.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "회원 관리", description = "회원가입 및 로그인 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	/**
	 * 회원가입
	 * @param dto 사용자 정보
	 * @return 성공 메시지
	 */
	@Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
	@PostMapping("/signup")
	public ResponseEntity<String> signup(
		@Parameter(description = "사용자 정보")
		@RequestBody @Valid LoginRequestDto dto) {

		userService.signUp(dto);
		return ResponseEntity.ok("User registered successfully");
	}

	/**
	 * 로그인
	 * @param dto 사용자 정보
	 * @return JWT 토큰
	 */
	@Operation(summary = "로그인", description = "사용자가 로그인합니다.")
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> login(
		@Parameter(description = "사용자 정보")
		@RequestBody @Valid LoginRequestDto dto) {

		LoginResponseDto response = userService.login(dto.getUsername(), dto.getPassword());

		return ResponseEntity.ok()
			.header(HttpHeaders.AUTHORIZATION, response.getTokenType() + " " + response.getAccessToken())
			.body(response);
	}
}
