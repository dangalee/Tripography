package com.ssafy.attraction.model.service;

import java.util.List;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.ContentDto;
import com.ssafy.attraction.model.GugunDto;
import com.ssafy.attraction.model.SidoDto;

public interface AttractionService {
	List<AttractionDto> searchAttraction(int sido_code, int gugun_code, int content_type_id, String title) throws Exception;
	List<SidoDto> getSidoList() throws Exception;
	List<GugunDto> getGugunList(int sidoCode) throws Exception;
	List<ContentDto> getContentTypeList() throws Exception;
	AttractionDto getAttraction(int contentId) throws Exception;
	List<AttractionDto>getBestAttractions() throws Exception;
}
