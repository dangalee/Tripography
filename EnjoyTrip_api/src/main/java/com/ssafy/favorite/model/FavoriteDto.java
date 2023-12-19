package com.ssafy.favorite.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FavoriteDto {
	private int favoriteId;
	private int contentId;
	private String userId;	
}
