package com.sign_in.code.service;

import com.sign_in.code.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Classname UploadService
 * @Description TODO
 * @Date 2020/12/06 22:52
 * @Created by wgg
 */

public interface UploadService {
    Result<Map<String,Object>> upload(MultipartFile file,String path);
}
