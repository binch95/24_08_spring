package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ReactionPointRepository;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.reactionPoint;

@Service
public class ReactionPointService {

	@Autowired
	private ReactionPointRepository reactionPointRepository;

	public ReactionPointService(ReactionPointRepository reactionPointRepository) {
		this.reactionPointRepository = reactionPointRepository;
	}

	public int userCanReaction(int loginedMemberId, String relTypeCode, int relId) {
		
		// 로그인 안했어
		if (loginedMemberId == 0) {
			return -2;
		}

		return reactionPointRepository.getSumReactionPoint(loginedMemberId, relTypeCode, relId);
	}



	public ResultData increaseLikeCount(int upAnddown, int id, int loginedMemberId, String relTypeCode, String upDown) {
		
		int haslike = reactionPointRepository.getGoodReactionPoint(loginedMemberId, relTypeCode, id, upAnddown );
	
		
		if(haslike == -3) {
			reactionPointRepository.creatReactionPoint(upAnddown, id, loginedMemberId, relTypeCode);
			return ResultData.from("S-1", "해당 게시글에 리액션 생성", "id", reactionPointRepository.getArticleSumReactionPoint(upAnddown, id));
		}
		
		reactionPointRepository.cancelReactionPoint(upAnddown, id, loginedMemberId);
		return ResultData.from("F-1", "해당 게시글 리액션 취소", "id", reactionPointRepository.getArticleSumReactionPoint(upAnddown, id));
	}

	public Object getSumReactionPoint(int loginedMemberId, String relTypeCode, int relId) {
		return reactionPointRepository.getSumReactionPoint(loginedMemberId, relTypeCode, relId);
	}

	public void updateArticlelike() {
		reactionPointRepository.updateArticlelike();
	}
	
	public int getArticleSumReactionPoint(int upAnddown, int id) {
		return reactionPointRepository.getArticleSumReactionPoint(upAnddown, id);
	}

}



