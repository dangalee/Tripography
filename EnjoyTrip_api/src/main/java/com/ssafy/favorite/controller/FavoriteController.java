package com.ssafy.favorite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.favorite.model.FavoriteDto;
import com.ssafy.favorite.model.service.FavoriteService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/favorite")
@Slf4j
public class FavoriteController {

	FavoriteService favoriteService;
	
	@Autowired
	public FavoriteController(FavoriteService favoriteService) {
		super();
		this.favoriteService = favoriteService;
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> showFavoriteList(@PathVariable("userId") String userId){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			log.error("userID : {}",userId);
			List<Integer> list = favoriteService.selectFavoriteAll(userId);
			resultMap.put("favoritelist", list);
			status = HttpStatus.OK;
		}catch(Exception e) {
			log.error("좋아요 조회 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap,status);
	}
	
	@GetMapping("/{userId}/{contentId}")
	public ResponseEntity<?> isLike(@PathVariable String userId, @PathVariable int contentId){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			log.error("userID : {}, contentId : {}",userId,contentId);
			Map<String, Object> map = favoriteService.isLike(userId, contentId);
			if(map != null) {
				resultMap.put("isLike", true);
				status = HttpStatus.OK;
			}else {
				resultMap.put("isLike", false);
				status = HttpStatus.OK;
			}
		}catch(Exception e) {
			log.error("좋아요 조회 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap,status);
	}
	
	@PostMapping("/check")
	public ResponseEntity<?> checkFavorite(@RequestBody FavoriteDto favoriteDto){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			log.error(favoriteDto.toString());
			favoriteService.insertFavorite(favoriteDto);
			resultMap.put("message", "좋아요 리스트에 추가되었습니다!");
			status = HttpStatus.OK;
		}catch(Exception e) {
			log.error("좋아요 처리 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap,status);
		
	}
	
	@DeleteMapping("/uncheck")
	public ResponseEntity<?> uncheckFavorite(@RequestBody FavoriteDto favoriteDto){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			log.error(favoriteDto.toString());
			favoriteService.deleteFavorite(favoriteDto);
			resultMap.put("message", "좋아요가 해제되었습니다!");
			status = HttpStatus.OK;
		}catch(Exception e) {
			log.error("좋아요 해제 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap,status);
		
	}
}
