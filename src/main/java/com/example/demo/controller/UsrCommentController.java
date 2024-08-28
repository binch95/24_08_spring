package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.service.CommentService;
import com.example.demo.util.Ut;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsrCommentController {
	@Autowired
	private Rq rq;

	@Autowired
	private CommentService commentService;

	@RequestMapping("/usr/comment/docomment")
	@ResponseBody
	public String docomment(HttpServletRequest req, int id, String commentInput) {
		String referer = req.getHeader("Referer");
		commentService.addComment(id,rq.getLoginedMemberId(),commentInput);
		
		return Ut.jsReplace("S-1", "댓글작성완료", referer);
	}
	
	@RequestMapping("/usr/comment/dodelete")
	@ResponseBody
	public String dodelete(HttpServletRequest req, int id) {
		String referer = req.getHeader("Referer");
		commentService.removeComment(id);
		
		return Ut.jsReplace("S-1", "댓글삭제완료", referer);
	}
	
	@RequestMapping("/usr/comment/doUpdateArticleComment")
	@ResponseBody
	public ResultData doUpdateArticleComment(HttpServletRequest req, int id, int commentId,String commentBody) {
		ResultData sussece = ResultData.from("S-1", "해당 댓글수정 완료", "Comment", commentBody);
		
		commentService.updateComment(id,commentId,commentBody,rq.getLoginedMemberId());
		
		return ResultData.newData(sussece, "updateComment", commentBody);
	}
	
	
	
}
