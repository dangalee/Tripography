package com.ssafy.board.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "BoardListDto : 게시글 목록", description = "게시글 목록입니다.")
public class BoardListDto {

	private List<BoardDto> articles;
	private int currentPage;
	private int totalPageCount;


}
