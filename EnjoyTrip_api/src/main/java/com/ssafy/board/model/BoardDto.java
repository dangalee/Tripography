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
@ApiModel(value = "BoardDto : 게시글정보", description = "게시글의 상세 정보를 나타낸다.")
public class BoardDto {

	@ApiModelProperty(value = "글번호")
	private int articleNo;
	@ApiModelProperty(value = "작성자 아이디")
	private String userId;
	@ApiModelProperty(value = "작성자 이름")
	private String userName;
	@ApiModelProperty(value = "글제목")
	private String subject;
	@ApiModelProperty(value = "글내용")
	private String content;
	@ApiModelProperty(value = "조회수")
	private int hit;
	@ApiModelProperty(value = "작성일")
	private String registerTime;

	@Override
	public String toString() {
		return "BoardDto [articleNo=" + articleNo + ", userId=" + userId + ", userName=" + userName + ", subject="
				+ subject + ", content=" + content + ", hit=" + hit + ", registerTime=" + registerTime + ", fileInfos="
				+ "]";
	}

}
