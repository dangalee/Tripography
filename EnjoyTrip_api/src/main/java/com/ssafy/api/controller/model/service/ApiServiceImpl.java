package com.ssafy.api.controller.model.service;

import com.ssafy.api.controller.model.dao.ApiDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiServiceImpl implements ApiService{

    private ApiDao apiDao;

    @Autowired
    public ApiServiceImpl(ApiDao apiDao) {
        super();
        this.apiDao = apiDao;
    }
    @Override
    public String searchPosts(String keyword) {
        return apiDao.searchPosts(keyword);
    }
}
