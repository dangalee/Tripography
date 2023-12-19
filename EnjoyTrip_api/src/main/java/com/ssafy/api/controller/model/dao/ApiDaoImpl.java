package com.ssafy.api.controller.model.dao;

import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Repository
public class ApiDaoImpl implements ApiDao {
    public String searchPosts(String keyword) {
        String clientId = "hhlwTtZ6rzv_cDFZsEr3";
        String clientSecret = "owHZUbcoCI";
        int display = 10; // 검색결과갯수. 최대100개

        //반환 값
        StringBuilder sb = new StringBuilder();
        sb.append("");
        try {

            String text = URLEncoder.encode(keyword, "utf-8");
            String apiURL = "https://openapi.naver.com/v1/search/blog.json?query=" + text + "&display=" + display + "&";

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            sb = new StringBuilder();

            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            con.disconnect();

        } catch (Exception e) {
            System.out.println(e);
        }
        return sb.toString();
    }
}




