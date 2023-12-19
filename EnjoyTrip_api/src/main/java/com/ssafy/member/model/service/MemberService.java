package com.ssafy.member.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.member.model.MemberDto;

public interface MemberService {

	int insert(MemberDto memberDto) throws Exception;

	int update(MemberDto memberDto) throws Exception;

	int delete(String memberId) throws Exception;
	
	MemberDto select(String memberId) throws Exception;
	
	List<MemberDto> selectAll() throws Exception;
	
	MemberDto login(Map<String, String> map) throws Exception;

	int saveRefreshToken(String userId, String refreshToken) throws Exception;
	
	int removeRefreshToken(String userId) throws Exception;
	
	String getRefreshToken(String userId) throws Exception;
}
