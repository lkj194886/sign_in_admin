package com.sign_in.code.controller;

import com.sign_in.code.service.CityService;
import com.sign_in.code.util.ReadJsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @Classname CityController
 * @Description TODO
 * @Date 2020/10/10 15:35
 * @Created by wgg
 */
@RequestMapping("/city")
@Controller
@Api(value = "CityController | 天气接口")
public class CityController {

    @Autowired
    CityService cityService;

    @RequestMapping("/getCity")
    @ResponseBody
    @ApiOperation(value = "天气a", notes = "获取天气")
    @CrossOrigin
    String getCity(String city) throws Exception {
        String cid = ReadJsonUtil.getCityCode(city);
        return cityService.getCity(cid);
    }
}
