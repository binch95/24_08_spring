package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.vo.Comment;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.CommentRepository;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}


	public List<Comment> getForPrintComment(int id) {

		return commentRepository.getForPrintComment(id); 
	}

	public List<Comment> getViewNumberForComment() {
		return commentRepository.getViewNumberForComment();
	}

	public void addComment(int id, int loginedMemberId, String commentInput) {
		commentRepository.addComment(id, loginedMemberId,commentInput);	
	}


	public void removeComment(int id) {
		commentRepository.removeComment(id);
	}



}
