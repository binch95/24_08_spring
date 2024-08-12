package com.example.demo.repository;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Member;

@Mapper
public interface MemberRepository {

	public void signMember(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);

	@Select("SELECT LAST_INSERT_ID();")
	public int getlastInsertId();
	
	public Member getMemberById(int id);
	
	
	@Select("""
			SELECT * 
			FROM `member` 
			WHERE loginId = #{loginId}
			""")
	public Member getMemberByLoginId(String loginId);
	
	
	public Member getMemberByNameAndEmail(String name, String email);
}