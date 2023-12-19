package com.ssafy.attraction.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.ContentDto;
import com.ssafy.attraction.model.GugunDto;
import com.ssafy.attraction.model.SidoDto;
import com.ssafy.attraction.model.service.AttractionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/attraction")
@CrossOrigin("*")
@Api(tags = {"어트랙션  API V1"})
@Slf4j
public class AttractionController {

	private AttractionService attractionService;
	
	@Autowired
	public AttractionController(AttractionService attractionService) {
		super();
		this.attractionService = attractionService;
	}

	@ApiOperation(value = "관광지 찾기", notes = "관광지 찾기")
	@PostMapping("/search")
	public ResponseEntity<List<AttractionDto>> searchAttraction(@RequestBody Map<String, Object> map){
		try {
			int sido_code = (int)map.get("sido_code");
			int gugun_code = (int)map.get("gugun_code");
			int content_type_id = (int)map.get("content_type_id");
			String title = (String)map.get("title");
			log.error("{}, {}, {}, {}",sido_code, gugun_code, content_type_id, title);
			List<AttractionDto> list = attractionService.searchAttraction(sido_code, gugun_code, content_type_id, title);
			return new ResponseEntity<List<AttractionDto>>(list,HttpStatus.OK);
		}catch(Exception e) { 	
			return null;
		}
	}
	
	@ApiOperation(value = "시도 목록 불러오기", notes = "시도 목록 불러오기")
	@GetMapping("/sido")
	public ResponseEntity<List<SidoDto>> getSidoList(){
		try {
			List<SidoDto> list = attractionService.getSidoList();
			return new ResponseEntity<List<SidoDto>>(list,HttpStatus.OK);
		}catch(Exception e) { 	
			return null;
		}
	}

	@ApiOperation(value = "시구군 목록 불러오기", notes = "시도 코드에 맞는 시구군 목록 불러오기")
	@GetMapping("/gugun/{sidoCode}")
	public ResponseEntity<List<GugunDto>> getGugunList(@PathVariable int sidoCode){
		try {
			log.debug("sidoCode : {}",sidoCode);
			List<GugunDto> list = attractionService.getGugunList(sidoCode);
			return new ResponseEntity<List<GugunDto>>(list,HttpStatus.OK);
		}catch(Exception e) { 	
			return null;
		}
	}
	
	@ApiOperation(value = "콘텐츠 목록 불러오기", notes = "콘텐츠 목록 불러오기")
	@GetMapping("/content")
	public ResponseEntity<List<ContentDto>> getContentTypeList(){
		try {
			List<ContentDto> list = attractionService.getContentTypeList();
			return new ResponseEntity<List<ContentDto>>(list,HttpStatus.OK);
		}catch(Exception e) { 	
			return null;
		}
	}
	
	@ApiOperation(value = "관광지 불러오기", notes = "관광지 불러오기")
	@GetMapping("/detail/{contentId}")
	public ResponseEntity<AttractionDto> getAttraction(@PathVariable int contentId){
		try {
			AttractionDto attractionDto = attractionService.getAttraction(contentId);
			return new ResponseEntity<AttractionDto>(attractionDto,HttpStatus.OK);
		}catch(Exception e) { 	
			return null;
		}
	}
	
	@ApiOperation(value = "배스트 관광지 불러오기", notes = "베스트 관광지 불러오기")
	@GetMapping("/best")
	public ResponseEntity<List<AttractionDto>> getBestAttractions(){
		try {
			List<AttractionDto> list = attractionService.getBestAttractions();
			return new ResponseEntity<List<AttractionDto>>(list,HttpStatus.OK);
		}catch(Exception e) { 	
			return null;
		}
	}
}
