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
public interface ArticleRepository {

	@Insert("""
			INSERT INTO article
			SET regDate = NOW(),
			updateDate = NOW(),
			memberId = #{memberId},
			boardId = #{boardId},
			title = #{title},
			`body` = #{body}
			""")
	public void writeArticle(int memberId, String title, String body, String boardId);

	@Delete("""
			DELETE FROM article
			WHERE id = #{id}
			""")
	public void deleteArticle(int id);

	@Update("""
			UPDATE article
			SET updateDate = NOW(),
			title = #{title},
			`body` = #{body}
			WHERE id = #{id}
			""")
	public void modifyArticle(int id, String title, String body);

	@Select("""
			SELECT A.* , M.nickname AS extra__writer,
			IFNULL(SUM(RP.point),0) AS extra__sumReactionPoint
			FROM article AS A
			INNER JOIN `member` AS M
			ON A.memberId = M.id
			LEFT JOIN reactionPoint AS RP
			ON A.id = RP.relId AND RP.relTypeCode = 'article'
			WHERE A.id = #{id}
			""")
	public Article getForPrintArticle(int id);

	@Select("""
			SELECT *
			FROM article
			WHERE id = #{id}
				""")
	public Article getArticleById(int id);

	@Select("""
			<script>
				SELECT A.* , M.nickname AS extra__writer,
				IFNULL(SUM(RP.point),0) AS extra__sumReactionPoint
				FROM article AS A
				INNER JOIN `member` AS M
				ON A.memberId = M.id
				LEFT JOIN reactionPoint AS RP
				ON A.id = RP.relId AND RP.relTypeCode = 'article'
				WHERE 1
				<if test="boardId != 0">
					AND boardId = #{boardId}
				</if>
				GROUP BY A.id
				ORDER BY A.id DESC
				<if test="limitFrom >= 0">
					LIMIT #{limitFrom}, #{limitTake}
				</if>
				</script>
			""")
	public List<Article> getForPrintArticles(int boardId, int limitFrom, int limitTake);

	@Select("""
			SELECT A.* , M.nickname AS extra__writer
			FROM article AS A
			INNER JOIN `member` AS M
			ON A.memberId = M.id
			ORDER BY A.id DESC
			""")
	public List<Article> getArticles();

	@Select("SELECT LAST_INSERT_ID();")
	public int getLastInsertId();

	@Select("""
			<script>
				SELECT COUNT(*) AS cnt
				FROM article
				WHERE 1
				<if test="boardId != 0">
					AND boardId = #{boardId}
				</if>
				ORDER BY id DESC;
			</script>
			""")
	public int getArticleCount(int boardId);
	
	

	@Select("""
			<script>
				SELECT A.* , M.nickname AS extra__writer,
				IFNULL(SUM(RP.point),0) AS extra__sumReactionPoint
				FROM article AS A
				INNER JOIN `member` AS M
				ON A.memberId = M.id
				LEFT JOIN reactionPoint AS RP
				ON A.id = RP.relId AND RP.relTypeCode = 'article'
				WHERE 1
				AND ${searchSelect} like CONCAT('%',#{searchKeyword},'%')
				<if test="boardId != 0">
					AND boardId = #{boardId}
				</if>
				GROUP BY A.id
				ORDER BY A.id DESC
				<if test="limitFrom >= 0">
					LIMIT #{limitFrom}, #{limitTake}
				</if>
			</script>
			""")
	public List<Article> getSearchForPrintArticles(int boardId, int limitFrom, int limitTake, String searchKeyword, String searchSelect);


	@Update("""
			UPDATE article
			SET hitCount = hitCount + 1
			WHERE id = #{id}
			""")
	public int increaseHitCount(int id);


	@Select("""
			SELECT hitCount
			FROM article
			WHERE id = #{id}
				""")
	public Object getArticleHitCount(int id);

	
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
	
}