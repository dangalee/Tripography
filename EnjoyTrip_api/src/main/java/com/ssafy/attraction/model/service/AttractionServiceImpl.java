package com.ssafy.attraction.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.ContentDto;
import com.ssafy.attraction.model.GugunDto;
import com.ssafy.attraction.model.SidoDto;
import com.ssafy.attraction.model.dao.AttractionDao;

@Service
public class AttractionServiceImpl implements AttractionService{

	private AttractionDao attractionDao;
	
	@Autowired
	public AttractionServiceImpl(AttractionDao attractionDao) {
		super();
		this.attractionDao = attractionDao;
	}

	@Override
	public List<AttractionDto> searchAttraction(int sido_code, int gugun_code, int content_type_id, String title) throws Exception{
		return attractionDao.searchAttraction(sido_code, gugun_code, content_type_id, title);
	}

	@Override
	public List<SidoDto> getSidoList() throws Exception {
		return attractionDao.getSidoList();
	}

	@Override
	public List<GugunDto> getGugunList(int sidoCode) throws Exception {
		return attractionDao.getGugunList(sidoCode);
	}

	@Override
	public List<ContentDto> getContentTypeList() throws Exception {
		return attractionDao.getContentTypeList();
	}

	@Override
	public AttractionDto getAttraction(int contentId) throws Exception {
		return attractionDao.getAttraction(contentId);
	}

	@Override
	public List<AttractionDto> getBestAttractions() throws Exception {
		return attractionDao.getBestAttractions();
	}
	
}
