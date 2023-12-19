package com.ssafy.board.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
public class TopBoardDto {

    @ApiModelProperty(value = "글번호")
    private int articleNo;
    @ApiModelProperty(value = "작성자 이름")
    private String userName;
    @ApiModelProperty(value = "글제목")
    private String subject;
    @ApiModelProperty(value = "댓글수")
    private int comments;
}
