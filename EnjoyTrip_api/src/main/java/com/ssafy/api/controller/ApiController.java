package com.ssafy.api.controller;

import com.ssafy.api.controller.model.apiDto;
import com.ssafy.api.controller.model.service.ApiService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@Api(tags = {"네이버 데이터랩  API V1"})
@Slf4j
public class ApiController {
    private ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService){
        super();
        this.apiService = apiService;
    }
//해당하는 네이버 블로그 글들 불러오기
    @ApiOperation(value = "블로그 글 목록", notes = "해당하는 블로그 글들의 정보를 반환한다.", response = List.class)
    @ApiResponses({ @ApiResponse(code = 200, message = "회원목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
            @ApiResponse(code = 500, message = "서버에러!!") })
    @GetMapping("/{keyword}")
    public ResponseEntity<?> searchPosts(
            @PathVariable("keyword") @ApiParam(value = "블로그 글 목록을 얻기위한 부가정보.", required = true) String keyword) {
        System.out.println("Keyword: " + keyword);

        try{
            String result = apiService.searchPosts(keyword);

            JSONParser jsonParser = new JSONParser();
            Object obj = jsonParser.parse(result);
            JSONObject jsonObj = (JSONObject) obj;
            JSONArray arr = (JSONArray) jsonObj.get("items");
            List<apiDto> apiDtoList = new ArrayList<>();
            for (Object elem : arr){
                JSONObject jObj = (JSONObject) elem;
                String title = (String) jObj.get("title");
                URL link = new URL((String) jObj.get("link"));
                String description = (String) jObj.get("description");
                System.out.println(title + " " + link + " " + description);

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                Date date = dateFormat.parse((String) jObj.get("postdate"));
                apiDtoList.add(new apiDto(title, description, link, date));
            }
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
            return ResponseEntity.ok().headers(header).body(apiDtoList);
        } catch (Exception e) {
            return exceptionHandling(e);
        }

    }

    private ResponseEntity<?> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
