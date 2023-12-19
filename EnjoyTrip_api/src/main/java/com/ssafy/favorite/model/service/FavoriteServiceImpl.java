package com.ssafy.favorite.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.favorite.model.FavoriteDto;
import com.ssafy.favorite.model.dao.FavoriteDao;

@Service
public class FavoriteServiceImpl implements FavoriteService {

	FavoriteDao favoriteDao;
	
	@Autowired
	public FavoriteServiceImpl(FavoriteDao favoriteDao) {
		super();
		this.favoriteDao = favoriteDao;
	}

	@Override
	public List<Integer> selectFavoriteAll(String userId) {
		return favoriteDao.selectFavoriteAll(userId);
	}

	@Override
	public int insertFavorite(FavoriteDto favoriteDto) {
		return favoriteDao.insertFavorite(favoriteDto);
	}

	@Override
	public int deleteFavorite(FavoriteDto favoriteDto) {
		return favoriteDao.deleteFavorite(favoriteDto);
	}

	@Override
	public Map<String,Object> isLike(String userId, int contentId) throws Exception {
		return favoriteDao.isLike(userId, contentId);
	}
	

}
