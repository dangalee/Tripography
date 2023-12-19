package com.ssafy.favorite.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.favorite.model.FavoriteDto;

@Mapper
public interface FavoriteDao {
	public List<Integer>selectFavoriteAll(String userId);
	public int insertFavorite(FavoriteDto favoriteDto);
	public int deleteFavorite(FavoriteDto favoriteDto);
	public Map<String,Object> isLike(String userId, int contentId);
}
