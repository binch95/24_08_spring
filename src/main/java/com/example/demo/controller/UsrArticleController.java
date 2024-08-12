package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;
@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(int id) {

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다.", id));
		}
		
		

		return ResultData.from("S-1", Ut.f("%d번 게시글 입니다", id), article);
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(int id, String title, String body) {

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다.", id));
		}

		articleService.modifyArticle(id, title, body);

		return ResultData.from("S-1", Ut.f("%d번 게시글이 수정되었습니다. 입니다", id), article);
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id) {
		Article article = articleService.getArticleById(id);
		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다.", id));
		}
		articleService.deleteArticle(id);
		return ResultData.from("S-1", Ut.f("%d번 게시글을 삭제합니다.", id), article);
	}
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(String title, String body) {
		
		if(Ut.isEmpty(title)) {
			return ResultData.from("F-1", Ut.f("title을 입력해주세요"));
		}
		if(Ut.isEmpty(body)) {
			return ResultData.from("F-2", Ut.f("body를 입력해주세요"));
		}
		
		int id = articleService.writeArticle(title, body);
		
		Article article = articleService.getArticleById(id);
		
		return ResultData.from("S-1", Ut.f("%d번 게시글 생성완료", id), article);
	}
	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData getArticles() {
		return ResultData.from("S-1", Ut.f("%d개 게시글 전체를 불러옵니다.", articleService.getArticles().size()), articleService.getArticles());

	}
}
