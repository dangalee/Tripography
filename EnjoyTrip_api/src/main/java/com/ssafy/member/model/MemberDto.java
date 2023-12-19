package com.ssafy.member.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "MemberDto (회원정보)", description = "아이디, 비번 등 회원 정보를 가진   Domain Class")
public class MemberDto {

	@ApiModelProperty(value = "회원 아이디")
	private String userId;
	@ApiModelProperty(value = "회원 비밀번호")
	private String userPw;
	@ApiModelProperty(value = "회원 이름")
	private String userName;
	@ApiModelProperty(value = "회원 이메일")
	private String userEmail;
	@ApiModelProperty(value = "회원 등급")
	private String userGrade;
	@ApiModelProperty(value = "회원 가입 날짜")
	private String joinDate;

}
