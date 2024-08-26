package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ReactionPointRepository {

	@Select("""
			SELECT IFNULL(SUM(RP.point),-3)
			FROM reactionPoint as RP
			WHERE RP.relTypeCode = #{relTypeCode}
			AND RP.relId = #{relId}
			AND RP.memberId = #{loginedMemberId};
			""")
	public int getSumReactionPoint(int loginedMemberId, String relTypeCode, int relId);
	
	@Select("""
			SELECT IFNULL(SUM(`point`),-3)
			FROM reactionPoint
			WHERE relTypeCode = #{relTypeCode}
			AND relId = #{relId}
			AND memberId = #{loginedMemberId}
			AND `point` = #{upAnddown};
			""")
	public int getGoodReactionPoint(int loginedMemberId, String relTypeCode, int relId, int upAnddown);

	@Insert("""
			INSERT INTO reactionPoint
			SET regDate = NOW(),
			updateDate = NOW(),
			memberId = #{loginedMemberId},
			relTypeCode = 'article',
			relId = #{id},
			`point` = #{upAnddown};
			""")
	public void creatReactionPoint(int upAnddown, int id, int loginedMemberId, String relTypeCode);

	@Update("""
	UPDATE article AS A
	INNER JOIN (
	    SELECT RP.relTypeCode, Rp.relId,
	    SUM(IF(RP.point > 0,RP.point,0)) AS goodReactionPoint,
	    SUM(IF(RP.point < 0,RP.point * -1,0)) AS badReactionPoint
	    FROM reactionPoint AS RP
	    GROUP BY RP.relTypeCode,Rp.relId
	) AS RP_SUM
	ON A.id = RP_SUM.relId
	SET A.goodReactionPoint = RP_SUM.goodReactionPoint,
	A.badReactionPoint = RP_SUM.badReactionPoint;
			""")
	public void updateArticlelike();

	
	@Select("""
			SELECT IFNULL(SUM(`point`),0)
			FROM reactionPoint
			WHERE relId = #{id}
			AND `point` = #{upAnddown};
			""")
	public int getArticleSumReactionPoint(int upAnddown, int id);

	
	@Delete("""
			DELETE FROM reactionPoint
			WHERE memberId = #{loginedMemberId}
			AND relTypeCode = 'article'
			AND relId = #{id}
			AND `point` = #{upAnddown};
			""")
	public void cancelReactionPoint(int upAnddown, int id, int loginedMemberId);

}
