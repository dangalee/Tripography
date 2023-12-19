package com.ssafy.member.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.member.model.MemberDto;

@Mapper
public interface MemberDao {

	int insert(MemberDto memberDto);

	int update(MemberDto memberDto);

	int delete(String memberId);
	
	MemberDto select(String memberId);
	
	List<MemberDto> selectAll();
	
	MemberDto login(Map<String, String> map);
	
	int saveRefreshToken(String userId, String refreshToken);
	
	int removeRefreshToken(String userId);
	
	String getRefreshToken(String userId);
}
