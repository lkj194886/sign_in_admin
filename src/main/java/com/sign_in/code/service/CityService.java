package com.sign_in.code.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Classname CityService
 * @Description TODO
 * @Date 2020/10/10 16:25
 * @Created by wgg
 */

public interface CityService {
    String getCity(String cid) throws IOException;
}
