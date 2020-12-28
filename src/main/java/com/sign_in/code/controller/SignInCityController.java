package com.sign_in.code.controller;

import com.sign_in.code.service.CityService;
import com.sign_in.code.util.ReadJsonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Classname CityController
 * @Description TODO
 * @Date 2020/10/10 15:35
 * @Created by wgg
 */
@RequestMapping("/city")
@Controller
@RestController
public class SignInCityController {

    @Autowired
    CityService cityService;

    @RequestMapping("/getCity")
    String getCity(String city) throws Exception {
        String cid = ReadJsonUtil.getCityCode(city);
        return cityService.getCity(cid);
    }

}
