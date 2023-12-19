package com.ssafy.attraction.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "GugunDto (구군 정보)", description = "구군 정보를 가진   Domain Class")
public class GugunDto {
	private int gugun_code;
	private String gugun_name;
	private int sido_code;
}
