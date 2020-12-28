package com.sign_in.code.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;

import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Component;


import java.util.Random;

/**
 * @Classname AliYunUtil
 * @Description 阿里云短信工具类
 * @Date 2020/10/20 13:56
 * @Created by wgg
 */
@Component
public class AliYunUtil {
    private final String AccessKey_ID="LTAI4FiKXjaHh9Wk1yQjNseY";
    private final String AccessKey_Secret="iEOskFoHsw4Je6BI9lcrfnsa1Mm91j";
    public int aliYunCode(String phone){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", AccessKey_ID,AccessKey_Secret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "趣味app");
        request.putQueryParameter("TemplateCode", "SMS_205877255");
        int code = RandomCode();
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
//            if (response.getData())
            JSONObject jsonObject = JSON.parseObject(response.getData());
            if (jsonObject.getString("Message").equals("OK")){
                return code;
            }
            return 0;
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int RandomCode(){
        int flag = new Random().nextInt(9999);
        if (flag<1000){
            flag += 1000;
        }
        return flag;
    }
}
