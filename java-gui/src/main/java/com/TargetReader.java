package com;
import java.io.*;
import java.util.*;

public class TargetReader {
    public static final HashMap<String, String> readTargetCSVFile(String filePath) {
        File csv = new File(filePath);
        BufferedReader br = null;
        String line = "";

        HashMap<String, String> result = new HashMap<>();

        try{
            br = new BufferedReader(new FileReader(csv));
            while((line = br.readLine()) != null){
                String[] lineArr = line.split(",");
                result.put(lineArr[0], lineArr[1]);
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }

        return result;
    }
}
