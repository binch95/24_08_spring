package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.MemberRepository;
import com.example.demo.vo.Member;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	
	}


	public int signMember(String loginId, String loginPw, String name) {
		memberRepository.signMember(loginId, loginPw, name);
		int id = memberRepository.getlastInsertId();
		return id;
	}
	
	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}




}