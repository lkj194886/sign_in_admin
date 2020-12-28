package com.sign_in.code.controller;

import com.sign_in.code.entity.SignInAdvErTiSing;
import com.sign_in.code.service.AdvertisingService;
import com.sign_in.code.util.Result;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
    Result<Map<String, Object>> getAdvertising(@RequestParam("phone") String phone) throws InterruptedException {
        return advertisingService.getAdvertising(phone);
    }

    //获取广告集合
    @RequestMapping("/getAdvertisingList")
    Result<Map<String, Object>> getAdvertisingList(@RequestParam("title") String title, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        return advertisingService.getAdvertisingList(title, pageNum);
    }

    @Value("${file.address}")
    public String address;
    private static final Logger logger = LoggerFactory.getLogger(AdvertisingController.class);

    //修改广告
    @RequestMapping("/upload")
    Result<Map<String, Object>> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new Result<>(500, "文件为空", null);
        }
        String fileName = file.getOriginalFilename();
        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        String path = address + fileName;
        File dest = new File(path);
        if (dest.exists()) {
            return new Result<>(500, "文件已存在", null);
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            IOUtils.copy(file.getInputStream(), fileOutputStream);
        } catch (IOException e) {
            return new Result<>(500, "处理失败", null);
        } finally {
            try {
                fileOutputStream.close();

            } catch (IOException e) {
                logger.error("", e);
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("fileName", fileName);
        return new Result<>(200, "处理成功", map);
    }

    @RequestMapping("/modifyAdvertising")
    Result<Map<String, Object>> modifyAdvertising(
            @RequestParam("advertisingId") Long advertisingId,
            @RequestParam("advertisingTitle") String advertisingTitle,
            @RequestParam("advertisingContent") String advertisingContent,
            @RequestParam("advertisingSrc") String advertisingSrc,
            @RequestParam("advertisingHref") String advertisingHref,
            @RequestParam("advertisingStatus") Long advertisingStatus
    ) {
        SignInAdvErTiSing signInAdvErTiSing = new SignInAdvErTiSing();
        signInAdvErTiSing.setAdvertisingId(advertisingId);
        signInAdvErTiSing.setAdvertisingTitle(advertisingTitle);
        signInAdvErTiSing.setAdvertisingContent(advertisingContent);
        signInAdvErTiSing.setAdvertisingSrc(advertisingSrc);
        signInAdvErTiSing.setAdvertisingHref(advertisingHref);
        signInAdvErTiSing.setAdvertisingStatus(advertisingStatus);
        if (advertisingService.modifyAdvErTiSing(signInAdvErTiSing) > 0) {
            return new Result<>(200, "处理成功", null);
        }
        return new Result<>(500, "处理失败", null);
    }

    //添加
    @RequestMapping("/addAdvErTiSing")
    Result<Map<String, Object>> addAdvErTiSing(@RequestParam("advertisingTitle") String advertisingTitle,
                                               @RequestParam("advertisingContent") String advertisingContent,
                                               @RequestParam("advertisingSrc") String advertisingSrc,
                                               @RequestParam("advertisingHref") String advertisingHref,
                                               @RequestParam("advertisingStatus") Long advertisingStatus) {
        SignInAdvErTiSing signInAdvErTiSing = new SignInAdvErTiSing();
//        signInAdvErTiSing.setAdvertisingId(advertisingId);
        signInAdvErTiSing.setAdvertisingTitle(advertisingTitle);
        signInAdvErTiSing.setAdvertisingContent(advertisingContent);
        signInAdvErTiSing.setAdvertisingSrc(advertisingSrc);
        signInAdvErTiSing.setAdvertisingHref(advertisingHref);
        signInAdvErTiSing.setAdvertisingStatus(advertisingStatus);
        if (advertisingService.addAdvErTiSing(signInAdvErTiSing) > 0) {
            return new Result<>(200, "处理成功", null);
        }
        return new Result<>(500, "处理失败", null);
    }
}
