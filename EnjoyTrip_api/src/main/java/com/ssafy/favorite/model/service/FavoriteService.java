package com.ssafy.favorite.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.favorite.model.FavoriteDto;

public interface FavoriteService {
	public List<Integer> selectFavoriteAll(String userId) throws Exception;
	public int insertFavorite(FavoriteDto favoriteDto) throws Exception;
	public int deleteFavorite(FavoriteDto favoriteDto) throws Exception;
	public Map<String, Object> isLike(String userId, int contentId) throws Exception;
}
