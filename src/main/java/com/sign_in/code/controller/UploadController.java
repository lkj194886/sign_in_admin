package com.sign_in.code.controller;

import com.sign_in.code.service.UploadService;
import com.sign_in.code.util.Result;
import javafx.beans.binding.ObjectExpression;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
 * @Classname UploadController
 * @Description TODO
 * @Date 2020/12/06 22:39
 * @Created by wgg
 */
@RequestMapping("/upload")
@RestController
public class UploadController {

    @Autowired
    UploadService upload;


    @RequestMapping("/yq")
    Result<Map<String, Object>> yq(@RequestParam("file") MultipartFile file) {
        return upload.upload(file, "invitation/");
    }

    @RequestMapping("/dw")
    Result<Map<String, Object>> dw(@RequestParam("file") MultipartFile file) {
        return upload.upload(file, "download/");
    }

    @RequestMapping("/gongao")
    Result<Map<String, Object>> gongao(@RequestParam("file") MultipartFile file) {
        return upload.upload(file, "notice/");
    }

    @RequestMapping("/index")
    Result<Map<String, Object>> index(@RequestParam("file") MultipartFile file){
        return upload.upload(file,"index/");
    }
}
