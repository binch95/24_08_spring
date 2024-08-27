package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.Comment;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

@Mapper
public interface CommentRepository {

	
	@Select("""
			SELECT AC.*, MB.nickname AS nickname
			FROM article_comment AS AC
			iNNER JOIN `member` AS MB
			ON AC.memberId = MB.id
			AND articleId = #{id}
				""")
	public List<Comment> getForPrintComment(int id);
	
	
	@Select("""
			SELECT A.id , IFNULL(COUNT(AC.articleId),0) AS viewNumber
			FROM article_comment AS AC
			RIGHT JOIN article AS A
			ON A.id = AC.articleId
			GROUP BY A.id
				""")
	public List<Comment> getViewNumberForComment();

	@Insert("""
			INSERT INTO article_comment
			SET regDate = NOW(),
			updateDate = NOW(),
			memberId = #{loginedMemberId},
			articleId = #{id},
			`body` = #{commentInput}
			""")
	public void addComment(int id, int loginedMemberId, String commentInput);


	@Delete("""
			DELETE FROM article_comment
			WHERE id = #{id}
			""")
	public void removeComment(int id);
	
}