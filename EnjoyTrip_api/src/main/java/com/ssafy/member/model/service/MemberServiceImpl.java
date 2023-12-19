package com.ssafy.member.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.dao.MemberDao;

@Service
public class MemberServiceImpl implements MemberService{

	private MemberDao memberDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public MemberServiceImpl(MemberDao memberDao) {
		super();
		this.memberDao = memberDao;
	}
	
	@Override
	public int insert(MemberDto memberDto) throws Exception {
		String encPass = passwordEncoder.encode(memberDto.getUserPw());
		memberDto.setUserPw(encPass);
		return memberDao.insert(memberDto);
	}

	@Override
	public int update(MemberDto memberDto) throws Exception {
		return memberDao.update(memberDto);
	}

	@Override
	public int delete(String userId) throws Exception {
		return memberDao.delete(userId);
	}

	@Override
	public MemberDto select(String userId) throws Exception {
		return memberDao.select(userId);
	}

	@Override
	public List<MemberDto> selectAll() throws Exception {
		return memberDao.selectAll();
	}

	@Override
	public MemberDto login(Map<String, String> map) throws Exception {
		MemberDto memberDto = memberDao.login(map);
		if(memberDto != null) {
			passwordEncoder.matches(map.get("userPw"), memberDto.getUserPw());
			return memberDto;
		}
		else return null;
	}

	@Override
	public int saveRefreshToken(String userId, String refreshToken) throws Exception {
		return memberDao.saveRefreshToken(userId, refreshToken);
	}

	@Override
	public int removeRefreshToken(String userId) throws Exception {
		return memberDao.removeRefreshToken(userId);
	}

	@Override
	public String getRefreshToken(String userId) throws Exception {
		return memberDao.getRefreshToken(userId);
	}

}
