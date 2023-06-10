package com;

import java.util.*;
import com.mongodb.config.MongoConfig;

public class ParserMain {
    public static void title() {
        System.out.println("============================================");
        System.out.println("팀원: 김준환, 조성윤, 안재혁, 정현준");
        System.out.println("프로젝트: 포렌식 수사를 위한 DarkWeb 데이터 수집");
        System.out.println("============================================");
    }

    public void run(String filePath) {
        MongoConfig mongo = new MongoConfig();

        Crawler target = new Crawler("127.0.0.1", 9150, SearchEngine.category, mongo);

        title();

        int i = 0;
        Thread[] threadTable = new Thread[5];

        HashMap<String, String> engineDict = TargetReader.readTargetCSVFile(filePath);

        System.out.println(filePath);

        for(Map.Entry<String, String> entry : engineDict.entrySet()) {
            threadTable[i] = new Thread(new CrawlerThread(target, entry.getKey(), entry.getValue()));
            threadTable[i].start();
            i++;
        }

        for(int j = 0; j < threadTable.length; j++){
            try {
                threadTable[j].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

