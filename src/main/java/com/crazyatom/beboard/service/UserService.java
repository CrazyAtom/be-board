package com.crazyatom.beboard.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crazyatom.beboard.dto.UserSignupRequestDto;
import com.crazyatom.beboard.entity.User;
import com.crazyatom.beboard.jwt.JwtUtil;
import com.crazyatom.beboard.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	/**
	 * 회원가입
	 * @param dto 사용자 정보
	 */
	@Transactional
	public void signUp(UserSignupRequestDto dto) {
		if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
			throw new RuntimeException("Username already exists");
		}

		userRepository.save(
			User.createUser(dto.getUsername(), passwordEncoder.encode(dto.getPassword()))
		);
	}

	/**
	 * 로그인
	 * @param username 사용자 이름
	 * @param password 비밀번호
	 * @return JWT 토큰
	 */
	public String login(String username, String password) {
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new RuntimeException("User not found"));

		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new RuntimeException("Invalid password");
		}

		return jwtUtil.generateToken(username);
	}
}
