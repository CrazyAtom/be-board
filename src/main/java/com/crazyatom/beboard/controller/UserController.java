package com.crazyatom.beboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crazyatom.beboard.service.UserService;

import lombok.RequiredArgsConstructor;

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
	@PostMapping("/signup")
	public ResponseEntity<String> signup(
		@RequestParam String username,
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
	@PostMapping("/login")
	public ResponseEntity<String> login(
		@RequestParam String username,
		@RequestParam String password) {

		String token = userService.login(username, password);
		return ResponseEntity.ok(token);
	}
}
