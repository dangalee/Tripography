package com.ssafy.attraction.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "AttractionDto (관광지 정보)", description = "관광지 정보를 가진   Domain Class")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttractionDto {

	@ApiModelProperty(value = "관광지 아이디")
	private int content_id;
	@ApiModelProperty(value = "관광지 타입 아이디")
	private int content_type_id;
	@ApiModelProperty(value = "관광지 이름")
	private String title;
	@ApiModelProperty(value = "관광지 주소")
	private String addr1;
	@ApiModelProperty(value = "관광지 세부 주소")
	private String addr2;
//	@ApiModelProperty(value = "zipcode")
//	private String zipcode;
//	@ApiModelProperty(value = "전화번호")
//	private String tel;
	@ApiModelProperty(value = "이미지 1")
	private String first_image;
//	@ApiModelProperty(value = "이미지 2")
//	private String first_image2;
//	@ApiModelProperty(value = "readcount")
//	private int readcount;
	@ApiModelProperty(value = "시도 코드")
	private int sido_code;
	@ApiModelProperty(value = "구군 코드")
	private int gugun_code;
	@ApiModelProperty(value = "위도")
	private double latitude;
	@ApiModelProperty(value = "경도")
	private double longitude;
//	@ApiModelProperty(value = "mlevel")
//	private String mlevel;
	@ApiModelProperty(value = "좋아요 수")
	private int favor_cnt;
}
