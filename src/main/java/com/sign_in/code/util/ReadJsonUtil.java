package com.sign_in.code.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname ReadJsonUtil
 * @Description TODO
 * @Date 2020/10/10 17:41
 * @Created by wgg
 */
@Component
public class ReadJsonUtil {
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCityCode(String city){
        String path = ReadJsonUtil.class.getClassLoader().getResource("sojson_com_city.json").getPath();
//        System.out.println("path = " + path);
        String s = ReadJsonUtil.readJsonFile(path);
        JSONArray jsonArray = JSON.parseArray(s);

        Map<String, Object> map = new HashMap<String, Object>();
        for (int i =0;i<jsonArray.size();i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            map.put((String) jsonObject.get("name"),jsonObject.get("city_code"));
        }
        return (String) map.get(city);
    }
}
