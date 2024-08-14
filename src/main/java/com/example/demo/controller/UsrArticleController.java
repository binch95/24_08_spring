package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;
@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleService;

	@RequestMapping("/usr/article/getArticle")
	public Object getArticle(Model model, HttpSession httpSession, int id) {

		Article article = articleService.getArticleById(id);


		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다.", id));
		}
		
		httpSession.setAttribute("article", article);
		model.addAttribute("article", article);


		return "usr/article/detail";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public Object doModify(HttpSession httpSession, int id, String title, String body) {
		Member member = (Member) httpSession.getAttribute("loginedMember");
		
		if (member == null) {
			return ResultData.from("F-A", Ut.f("로그인하고 써!"));
		}

		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없습니다.", id));
		}
		
		ResultData userCanModifyRd = articleService.userCanModify(member.getId() , article);
		
		if(article.getMemberId() != member.getId()) {
			return ResultData.from("F-2", Ut.f("%d번 게시글을 수정할 권한이 없습니다.", id));
		}

		articleService.modifyArticle(id, title, body);
		httpSession.removeAttribute("article");
		return Ut.jsReplace(userCanModifyRd.getResultCode(), userCanModifyRd.getMsg(), "../article/list");
	}

	@RequestMapping("/usr/article/doDelete")
	public String doDelete(HttpSession httpSession, int id) {
		Member member = (Member) httpSession.getAttribute("loginedMember");
		
		if (member == null) {
			return Ut.jsReplace("F-A", "로그인 후 이용하세요", "../member/login");
		}
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return Ut.jsHistoryBack("F-1", Ut.f("%d번 게시글은 없습니다", id));
		}
		
		if(article.getMemberId() != member.getId()) {
			return Ut.jsHistoryBack("F-1", Ut.f("%d번 게시글을 삭제할 권한이 없습니다", id));
		}
		
		articleService.deleteArticle(id);
		
		return Ut.jsReplace("S-1", Ut.f("%d번 게시글을 삭제하였습니다.", id), "../article/list");
	}
	@RequestMapping("/usr/article/doWrite")
	public Object doWrite(HttpSession httpSession, String title, String body) {
		
		Member member = (Member) httpSession.getAttribute("loginedMember");
		
		if (member == null) {
			return ResultData.from("F-1", Ut.f("로그인하고 써!"));
		}
		
		if(Ut.isEmpty(title)) {
			return ResultData.from("F-1", Ut.f("title을 입력해주세요"));
		}
		if(Ut.isEmpty(body)) {
			return ResultData.from("F-2", Ut.f("body를 입력해주세요"));
		}
		
		int id = articleService.writeArticle(member.getId(), title, body);
		
		Article article = articleService.getArticleById(id);
		
		return Ut.jsReplace("S-1", Ut.f("%d번 게시글을 작성하였습니다.", id), "../article/list");
	}
	
	@RequestMapping("/usr/article/list")
	public String showList(Model model) {
		List<Article> articles = articleService.getArticles();
		model.addAttribute("articles", articles);
		
		return "usr/article/list";

	}
}
