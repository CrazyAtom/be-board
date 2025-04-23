package com.crazyatom.beboard.entity;

import java.time.LocalDateTime;

import com.crazyatom.beboard.dto.PostRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long id;

	@Column(nullable = false)
	private String title;

	@Lob
	@Column(nullable = false)
	private String content;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User author;

	private LocalDateTime createdAt = LocalDateTime.now();

	public static Post createPost(User user, PostRequestDto dto) {
		Post post = new Post();
		post.author = user;
		post.title = dto.getTitle();
		post.content = dto.getContent();
		return post;
	}

	public void update(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
