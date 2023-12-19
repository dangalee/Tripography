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
@ApiModel(value = "SidoDto (시도 정보)", description = "시도 정보를 가진   Domain Class")
public class SidoDto {
	private int sido_code;
	private String sido_name;
}
