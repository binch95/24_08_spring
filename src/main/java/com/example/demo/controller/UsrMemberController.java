package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.vo.Member;
@Controller
public class UsrMemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/signMember")
	@ResponseBody
	public Member signMember(String loginId, String loginPw, String name) {

		int id = memberService.signMember(loginId, loginPw, name);
		Member member = memberService.getMemberById(id);
		
		return member;
	}

}
