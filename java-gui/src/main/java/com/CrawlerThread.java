package com;

import java.util.Vector;
import com.mongodb.config.MongoConfig;

public class CrawlerThread implements Runnable{
    Crawler _target;
    String _siteName;
    String _engineUrl;

    public CrawlerThread(Crawler target, String siteName, String engineUrl){
        this._target = target;
        this._siteName = siteName;
        this._engineUrl = engineUrl;
    }

    @Override
    public void run(){
        // 현재 실행중인 스레드의 이름 출력
        String threadName = Thread.currentThread().getName();
        System.out.println("[>] CurrentThread: "+ threadName);

        _target.run(this._siteName, this._engineUrl);
    }
}
