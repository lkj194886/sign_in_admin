package com.sign_in.code.controller;

import com.sign_in.code.service.AdvertisingService;
import com.sign_in.code.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Classname AdvertisingController
 * @Description TODO
 * @Date 2020/11/10 23:50
 * @Created by wgg
 */
@RequestMapping("/Advertising")
@RestController
public class AdvertisingController {

    @Autowired
    AdvertisingService advertisingService;
    @RequestMapping("/getAdvertising")
    Result<Map<String,Object>> getAdvertising(@RequestParam("phone") String phone) throws InterruptedException {
        return advertisingService.getAdvertising(phone);
    }
}
