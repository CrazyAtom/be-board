package com.crazyatom.beboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostViewController {

	@GetMapping
	public String postListPage() {
		return "post/list";
	}

	@GetMapping("/new")
	public String postCreatePage() {
		return "post/form";
	}
}
