package com.mongodb.config;
import com.google.gson.Gson;
import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import com.schema.InfoSchema;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.model.Accumulators;
import org.bson.conversions.Bson;
import org.bson.json.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MongoConfig {
    private MongoCollection<Document> collection = null;
    public MongoConfig() {
        String url = "mongodb://localhost:27017";

        MongoClient client = MongoClients.create(url);

        MongoDatabase db = client.getDatabase("DarkWeb");

        this.collection = db.getCollection("CrawlingData");
    }

    public int insertData(InfoSchema data) {
        try {
            Document doc = new Document();
//            doc.append("_id", test.getId());
            doc.append("category", data.getCategory());
            doc.append("siteName", data.getSiteName());
            doc.append("darkWebUrl", data.getDarkWebUrl());
            doc.append("laguage", data.getLang());
            doc.append("state", data.getState());
            doc.append("engineUrl", data.getEngine());
            doc.append("searchAt", data.getSearchAt());

            InsertOneResult result = this.collection.insertOne(doc);
            System.out.println("결과: " + result.getInsertedId());

            if (result.getInsertedId() != null){
                return 1;
            }

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String categoryCount() {

        try {
            AggregateIterable<Document> dbData = this.collection.aggregate(
                    Arrays.asList(
                            new Document("$group", new Document("_id", "$category").append("count", new Document("$sum", 1)))
                    )
            );

            MongoCursor<Document> cursor = dbData.iterator();
            ArrayList<String> result = new ArrayList<>();

            while(cursor.hasNext()){
                Document document = cursor.next();
                String jsonResult = document.toJson();

                result.add(jsonResult);
            }

            Gson gson = new Gson();
            String jsonPlace = gson.toJson(result);

            return jsonPlace;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //    전체 리스트 조회하는 코드 json형식으로 return.
    public String selectListData() {
        try {
            // 전체 조회하기, 정렬을 _id 오름차순
            FindIterable<Document> docs = this.collection.find();
            MongoCursor<Document> cursor = docs.iterator();

            ArrayList<String> result = new ArrayList<>();

            while(cursor.hasNext()){
                Document document = cursor.next();
                String jsonResult = document.toJson();

                result.add(jsonResult);
            }

            Gson gson = new Gson();
            String jsonPlace = gson.toJson(result);

            return jsonPlace;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList saveListData() {
        try {
            // 전체 조회하기, 정렬을 _id 오름차순
            FindIterable<Document> docs = this.collection.find();
            MongoCursor<Document> cursor = docs.iterator();

            ArrayList<String> result = new ArrayList<>();

            while(cursor.hasNext()){
                Document document = cursor.next();
//                String jsonResult = document.toJson();

                result.add(document.toJson() + "\n");
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args){
        MongoConfig mongo = new MongoConfig();

//        mongo.selectListData();
        mongo.categoryCount();
    }
}
