package com.ssafy.attraction.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "AttractionDto (관광지 정보)", description = "관광지 정보를 가진   Domain Class")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentDto {
	private int content_type_id;
	private String content_type_name;
}
