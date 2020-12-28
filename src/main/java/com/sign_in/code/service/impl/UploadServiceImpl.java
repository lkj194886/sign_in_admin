package com.sign_in.code.service.impl;

import com.sign_in.code.controller.AdvertisingController;
import com.sign_in.code.service.UploadService;
import com.sign_in.code.util.Result;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname UploadServiceImpl
 * @Description TODO
 * @Date 2020/12/06 22:53
 * @Created by wgg
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Value("${file.address}")
    public String address;

    private static final Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);

    @Override
    public Result<Map<String, Object>> upload(MultipartFile file,String path) {
        if (file.isEmpty()) {
            return new Result<>(500, "文件为空", null);
        }
        String fileName = file.getOriginalFilename();
        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
        String newPath = address+path + fileName;
        File dest = new File( newPath);
        if (dest.exists()) {
            return new Result<>(500, "文件已存在", null);
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(newPath);
//            IOUtils.copy(file.getInputStream(), fileOutputStream);
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
}
