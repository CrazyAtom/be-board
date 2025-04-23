package com.crazyatom.beboard.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final String SECRET_KEY = "myboardsecretkeymyboardsecretkey123";
	private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

	private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

	/**
	 * Generates a JWT token for the given username.
	 *
	 * @param username the username to include in the token
	 * @return the generated JWT token
	 */
	public String generateToken(String username) {
		return Jwts.builder()
			.setSubject(username)
			.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();
	}

	/**
	 * Extracts the username from the given JWT token.
	 *
	 * @param token the JWT token
	 * @return the username extracted from the token
	 */
	public String getUsername(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
	}

	/**
	 * Validates the given JWT token.
	 *
	 * @param token the JWT token to validate
	 * @return true if the token is valid, false otherwise
	 */
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}

