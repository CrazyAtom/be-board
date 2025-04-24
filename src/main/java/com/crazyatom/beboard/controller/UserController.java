package com.crazyatom.beboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crazyatom.beboard.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "회원 관리", description = "회원가입 및 로그인 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	/**
	 * 회원가입
	 * @param username 사용자 이름
	 * @param password 비밀번호
	 * @return 성공 메시지
	 */
	@Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
	@PostMapping("/signup")
	public ResponseEntity<String> signup(
		@Parameter(description = "사용자 이름")
		@RequestParam String username,
		@Parameter(description = "비밀번호")
		@RequestParam String password) {

		userService.signUp(username, password);
		return ResponseEntity.ok("User registered successfully");
	}

	/**
	 * 로그인
	 * @param username 사용자 이름
	 * @param password 비밀번호
	 * @return JWT 토큰
	 */
	@Operation(summary = "로그인", description = "사용자가 로그인합니다.")
	@PostMapping("/login")
	public ResponseEntity<String> login(
		@Parameter(description = "사용자 이름")
		@RequestParam String username,
		@Parameter(description = "비밀번호")
		@RequestParam String password) {

		String token = userService.login(username, password);
		return ResponseEntity.ok(token);
	}
}
