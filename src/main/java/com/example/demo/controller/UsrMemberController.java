package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;
@Controller
public class UsrMemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/signMember")
	@ResponseBody
	public Object signMember(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		if(Ut.isEmptyOrNull(loginId)) {
			return ResultData.from("F-1", Ut.f("loginId를 입력해주세요"));
		}
		if(Ut.isEmptyOrNull(loginPw)) {
			return ResultData.from("F-1", Ut.f("loginPw를 입력해주세요"));
		}
		if(Ut.isEmptyOrNull(name)) {
			return ResultData.from("F-1", Ut.f("name을 입력해주세요"));
		}
		if(Ut.isEmptyOrNull(nickname)) {
			return ResultData.from("F-1", Ut.f("nickname을 입력해주세요"));
		}
		if(Ut.isEmptyOrNull(cellphoneNum)) {
			return ResultData.from("F-1", Ut.f("cellphoneNum를 입력해주세요"));
		}
		if(Ut.isEmptyOrNull(email)) {
			return ResultData.from("F-1", Ut.f("email를 입력해주세요"));
		}
		
		
		int id = memberService.signMember(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		if (id == -1) {	
			return Ut.f("이미 사용중인 아이디(%s)입니다.", loginId);
		}
		if (id == -2) {
			return Ut.f("이미 사용중인 이름(%s)과 이메일(%s)", name, email);
		}
		Member member = memberService.getMemberById(id);
		
		return ResultData.from("S-1", Ut.f("%s 계정 생성완료", loginId), member);
		
	}

}
