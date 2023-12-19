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
@ApiModel(value = "BoardDto : top 10 게시글정보", description = "top 10 게시글을 메인페이지에 보여준다.")
public class TopBoardListDto {
    private List<TopBoardDto> articles;
    private int currentPage;
    private int totalPageCount;
}
