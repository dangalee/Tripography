package com.ssafy.board.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CommentDto : 댓글정보", description = "댓글의 상세 정보를 나타낸다.")
public class CommentDto {
    @ApiModelProperty(value = "글번호")
    private int articleNo;
    @ApiModelProperty(value = "댓글내용")
    private String content;
    @ApiModelProperty(value = "작성일")
    private String registerTime;
    @ApiModelProperty(value = "작성자 아이디")
    private String userId;
    @ApiModelProperty(value = "댓글번호")
    private int commentNo;
}
