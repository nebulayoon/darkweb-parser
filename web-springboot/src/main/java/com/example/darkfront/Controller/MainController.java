package com.example.darkfront.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.darkfront.util.mongodb.config.MongoConfig;

import java.util.ArrayList;

@Controller
public class MainController {
//    GET: url/getDataList -> 전체 데이터 리스트를 가져오는 API. RETURN: json형식.
    @ResponseBody
    @GetMapping("/getDataList")
    public String getDataList() {
        MongoConfig mongo = new MongoConfig();

        return mongo.selectListData();
    }

    //    GET: url/getCategoryCount -> category별로 각각의 개수를 가져오는 API. RETURN: json형식.
    @ResponseBody
    @GetMapping("/getCategoryCount")
    public String getCategoryCount() {
        MongoConfig mongo = new MongoConfig();

        return mongo.categoryCount();
    }

    @ResponseBody
    @GetMapping("/getSiteNameCount")
    public String getSiteNameCount() {
        MongoConfig mongo = new MongoConfig();

        return mongo.siteNameCount();
    }
}