package com.mongodb;
import com.mongodb.config.MongoConfig;
import com.schema.InfoSchema;

import java.util.ArrayList;

public class MongodbApplication {
    public static void main(String[] args) {
        MongoConfig obj = new MongoConfig();
        InfoSchema test = new InfoSchema();

//        test.setId("2");
        test.setCategory("bitcoin");
        test.setSiteName("testURL1");
        test.setLang("en");
        test.setState(200);
        test.setEngine("http://e27slbec2ykiyo26gfuovaehuzsydffbit5nlxid53kigw3pvz6uosqd.onion/?q=2");
        test.setSearchAt("2022-11-30-00:00:02");

        int ret = obj.insertData(test);

        if (ret == 1) {
            System.out.println("추가성공");
        } else {
            System.out.println("추가실패");
        }
//      한개만 조회
//        TestDB test1 = obj.selectOneData(test);
//        if (test1 != null) {
//            System.out.println(test1.toString());
//        }
//        ArrayList<InfoSchema> list = obj.selectListData();

//        for (InfoSchema tmp : list) {
//            System.out.println("목록 조회" + tmp.toString());
//        }
    }
}
