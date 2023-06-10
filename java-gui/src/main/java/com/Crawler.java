package com;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.awt.*;
import javax.swing.*;

import com.mongodb.config.MongoConfig;
import com.schema.InfoSchema;

class Crawler{
    private Proxy proxy;
    private String[] _category;

    private MongoConfig _db;

    public Crawler(String hostName, int port, String[] category, MongoConfig db){
        this.proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(hostName, port)); //프록시 설정
        this._category = category;
        this._db = db;
    }

    /* 카테고리를 순회하면서 HTML의 a태그에 있는 siteName을 가져오는 메소드 */
    public boolean getHtmlATags(String searchUrl, String siteName, String word) { // 나중에 db 완성하면 vector 제거하기
        String url = searchUrl;
         //각 키워드 사이트개수를 파악하기 위한 딕셔너리

        Document doc = null;
        try {
            doc = Jsoup.connect(url+word).proxy(proxy).timeout(300000).get(); //timeout해도 SocketTimeoutException에러 (해결방안찾기)
        } catch (IOException e) {
            System.out.println("[>] Jsoup connect IO 에러 발생");
        } catch (Exception e) {
            System.out.println("[>] " + e);
            System.out.println("[-] URL: " + (url + word));
        }

        Elements aTags = doc.select("a");
        int counter = 0; //해당 카테고리의 사이트 개수

        for(Element e : aTags){ //a태그값을 하나씩 가져온다
            String str = e.attr("href"); //href속성값을 가져온다
            if(str.contains(".onion/")) { //.onion/이 포함된것만 저장
                counter++;
                int idx = str.indexOf("http://"); //사이트만 추출(불확실)
                String link = str.substring(idx); //슬라이싱

                // DB 저장
                InfoSchema data = new InfoSchema(word, siteName, link, "en", 200, searchUrl);
                int ret = this._db.insertData(data); //db컬렉션에 값추가 null은 사이트에 들어가야 확인가능

                if(ret == 1){
                    System.out.println("[>] DB insert Done");
                } else {
                    System.out.println("[!] DB insert failed");
                }
            }
        }
         // 사실 이것도 db에 같이 집어 넣으면 댐
        return true;
    }

    public void run(String siteName, String engineUrl) {
        for(String word : _category) {
            getHtmlATags(engineUrl, siteName, word);
        }
    }
}