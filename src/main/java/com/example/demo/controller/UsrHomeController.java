package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.ArticleService;
import com.example.demo.vo.Article;

@Controller
public class UsrHomeController {
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/usr/home/main")
	public String showMain(Model model) {
		List<Article> articles = articleService.selectRecentPosts();
		model.addAttribute("articles", articles);
		return "/usr/home/main";
	}
	
	@RequestMapping("/usr/article/write")
	public String showWrite() {
		return "/usr/article/write";
	}
	
	@RequestMapping("/usr/article/modify")
	public String showModify() {

		return "/usr/article/modify";
	}
	
	@RequestMapping("/usr/member/login")
	public String showLogin() {
		return "/usr/member/login";
	}
	
	@RequestMapping("/usr/member/sign")
	public String showsign() {
		return "/usr/member/sign";
	}

	
	@RequestMapping("/")
	public String showRoot() {
		return "redirect:/usr/home/main";
	}
	
}