package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.vo.Article;

import lombok.Data;
@Controller
@Data
public class UsrArticleController {


	int idcount;
	List<Article> articles;
	
	public UsrArticleController() {
		idcount = 0;
		articles = new ArrayList<>();
		
		for(int i = 1; i <=10; i++) {
			Article article = new Article(i,"test" + i,"test"+i);
			articles.add(article);
		}
	}

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doadd(String title, String body){
		int id = articles.size() +1;
		Article article = new Article(id,title,body);
		articles.add(article);
		idcount++;
		return article;
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String dodelete(int id){
		for (Article article:articles){
			if(article.getId() == id) {
				articles.remove(article);
				return id + "번 글이 삭제되었습니다.";
			}
		}
		return id + "번 글없음";
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public Object domodify(int id, String title, String body){
		
		for (Article article:articles){
			if(article.getId() == id) {
				article.setTitle(title);
				article.setBody(body);
				return id + "번 글이 수정되었습니다." + getArticle();
			}
		}

		return id + "번 글없음";
	}
	

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public List<Article> getArticle() {
		return articles;
	}
	

}
