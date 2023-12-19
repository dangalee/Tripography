package com.ssafy.api.controller.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "블로그 정보", description = "블로그 리스트 ")
public class apiDto {
    private String title;
    private String description;
    private URL url;
    private Date date;
}
