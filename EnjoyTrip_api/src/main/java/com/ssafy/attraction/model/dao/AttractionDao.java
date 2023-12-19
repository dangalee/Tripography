package com.ssafy.attraction.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.ContentDto;
import com.ssafy.attraction.model.GugunDto;
import com.ssafy.attraction.model.SidoDto;

@Mapper
public interface AttractionDao {
	List<AttractionDto> searchAttraction(int sido_code, int gugun_code, int content_type_id, String title);
	List<SidoDto> getSidoList();
	List<GugunDto> getGugunList(int sidoCode);
	List<ContentDto> getContentTypeList();
	AttractionDto getAttraction(int contentId);
	List<AttractionDto> getBestAttractions();
}
