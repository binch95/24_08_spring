package com.example.demo.controller;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrMemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/signMember")
	@ResponseBody
	public Object signMember(HttpSession httpSession, String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		if(httpSession.getAttribute("loginedMember") != null) {
			return ResultData.from("F-A", Ut.f("이미 로그인 중"));
		}
		
		if(Ut.isEmptyOrNull(loginId)) {
			return ResultData.from("F-1", Ut.f("loginId를 입력해주세요"));
		}
		if(Ut.isEmptyOrNull(loginPw)) {
			return ResultData.from("F-2", Ut.f("loginPw를 입력해주세요"));
		}
		if(Ut.isEmptyOrNull(name)) {
			return ResultData.from("F-3", Ut.f("name을 입력해주세요"));
		}
		if(Ut.isEmptyOrNull(nickname)) {
			return ResultData.from("F-4", Ut.f("nickname을 입력해주세요"));
		}
		if(Ut.isEmptyOrNull(cellphoneNum)) {
			return ResultData.from("F-5", Ut.f("cellphoneNum를 입력해주세요"));
		}
		if(Ut.isEmptyOrNull(email)) {
			return ResultData.from("F-6", Ut.f("email를 입력해주세요"));
		}
		
		
		int id = memberService.signMember(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		if (id == -1) {	
			return Ut.f("이미 사용중인 아이디(%s)입니다.", loginId);
		}
		if (id == -2) {
			return Ut.f("이미 사용중인 이름(%s)과 이메일(%s)", name, email);
		}
		Member member = memberService.getMemberById(id);
		
		return "<script>location.replace('../member/login');</script>";
		
	}
	
	@RequestMapping("/usr/member/doLogin")
	public Object doLogin(Model model, HttpSession httpSession, String loginId, String loginPw) {
		
		if(httpSession.getAttribute("loginedMember") != null) {
			return ResultData.from("F-A", Ut.f("이미 로그인 중"));
		}
		
		if(Ut.isEmpty(loginId)) {
			return ResultData.from("F-1", Ut.f("LoginId 입력  X"));
		}
		if(Ut.isEmpty(loginPw)) {
			return ResultData.from("F-2", Ut.f("loginPw 입력  X"));
		}
		
		
		Member member = memberService.getMemberByLoginId(loginId);
		if (member == null) {
			return ResultData.from("F-3", Ut.f("%s는 없는 아이디 입니다.", loginId));
		}
		if(member.getLoginPw().equals(loginPw) == false) {
			return ResultData.from("F-4", Ut.f("비밀번호가 일치하지 않습니다."));
		}
		
		httpSession.setAttribute("loginedMember", member);
		model.addAttribute("loginedMember", member);
		 
		return "/usr/home/main";
	}
	
	
	@RequestMapping("/usr/member/doLogout")
	public Object doLogout(HttpSession httpSession) {
		Member member = (Member) httpSession.getAttribute("loginedMember");
		
		if (member == null) {
			return ResultData.from("F-1", Ut.f("로그인중이 아닙니다."));
		}
		
		httpSession.removeAttribute("loginedMember");
		return "/usr/home/main";
	}
	
	
	

}
