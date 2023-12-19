package com.ssafy.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.member.model.MemberDto;
import com.ssafy.member.model.service.MemberService;
import com.ssafy.util.JWTUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/member")
@CrossOrigin("*")
@Api(tags = {"멤버  API V1"})
@Slf4j
public class MemberController{
	
	private MemberService memberService;
	private JWTUtil jwtUtil;

	@Autowired
	public MemberController(MemberService memberService, JWTUtil jwtUtil) {
		super();
		this.memberService = memberService;
		this.jwtUtil = jwtUtil;
	}
	
//	로그인
	@ApiOperation(value = "로그인", notes = "회원 로그인")
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> map){
		log.debug("Tried Login Info : {}", map.toString());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			MemberDto memberDto = memberService.login(map);
			if(memberDto != null) {
				String accessToken = jwtUtil.createAccessToken(memberDto.getUserId());
				String refreshToken = jwtUtil.createRefreshToken(memberDto.getUserId());
				log.debug("access token : {}", accessToken);
				log.debug("refresh token : {}", refreshToken);
				
//				발급받은 refresh token을 DB에 저장.
				memberService.saveRefreshToken(memberDto.getUserId(), refreshToken);
				
//				JSON으로 token 전달.
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				
				status = HttpStatus.CREATED;
			}else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
				status = HttpStatus.UNAUTHORIZED;
			}
		} catch (Exception e) {
			log.error("로그인 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		} 
		return new ResponseEntity<Map<String, Object>>(resultMap,status);
	}
	
//	로그아웃
	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.")
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> removeToken(@PathVariable String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.removeRefreshToken(userId);
			resultMap.put("message", "로그아웃 되었습니다.");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
//	Access Token 재발급
	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		log.debug("token : {}, memberDto : {}", token, memberDto);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(memberDto.getUserId()))) {
				String accessToken = jwtUtil.createAccessToken(memberDto.getUserId());
				log.debug("token : {}", accessToken);
				log.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			log.debug("리프레쉬토큰도 사용불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
//	회원 등록
	@ApiOperation(value = "회원 정보 등록", notes = "회원 한명에 대한 정보 등록")
	@PostMapping("/regist")
	public ResponseEntity<?> memberRegist(@RequestBody MemberDto memberDto){
		log.debug("regist Member : {}", memberDto);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.insert(memberDto);
			resultMap.put("message", "회원가입 완료!");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("회원가입 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
//	회원 수정
	@ApiOperation(value = "회원 정보 수정", notes = "회원 한명에 대한 정보 수정")
	@PostMapping("/update")
	public ResponseEntity<?> memberUpdate(@RequestBody MemberDto memberDto){
		log.debug("update Member : {}", memberDto);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.update(memberDto);
			resultMap.put("message", "회원수정 완료!");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("회원수정 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
//	회원 삭제
	@ApiOperation(value = "회원 정보 삭제", notes = "회원 한명에 대한 정보 삭제")
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> memberDelete(@PathVariable String userId){
		log.debug("delete Member : {}", userId);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.delete(userId);
			resultMap.put("message", "회원탈퇴 완료!");
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("회원탈퇴 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
//	회원 조회
	@ApiOperation(value = "회원 정보 조회", notes = "회원 한명에 대한 정보 조회")
	@GetMapping("/search/{memberId}")
	public ResponseEntity<?> memberSearch(@PathVariable String memberId, HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if(jwtUtil.checkToken(request.getHeader("Authorization"))) {
			try {
				MemberDto memberDto = memberService.select(memberId);
				resultMap.put("userInfo", memberDto);
				status = HttpStatus.OK;
			} catch (Exception e) {
				log.error("회원정보조회 에러 발생 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}else {
			log.error("사용 불가 토큰");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 모든 회원 조회
//	@ApiOperation(value = "전체 회원 조회", notes = "모든 회원에 대한 정보 조회")
//	@GetMapping("/searchAll")
//	public ResponseEntity<?> memberAllSearch(){
//		try {
//			List<MemberDto> list = memberService.selectAll();
//			return new ResponseEntity<List<MemberDto>>(list,HttpStatus.OK);
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
	
}
