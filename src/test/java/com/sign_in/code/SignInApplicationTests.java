package com.sign_in.code;

import com.sign_in.code.entity.SignInUser;
import com.sign_in.code.mapper.SignVipMapper;
import com.sign_in.code.mapper.UserMapper;
import com.sign_in.code.service.SignInUserService;
import com.sign_in.code.service.impl.SignInUserImpl;
import com.sign_in.code.util.DateUtil;
import com.sign_in.code.util.Md5Util;
import com.sign_in.code.util.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


@SpringBootTest
class SignInApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    SignVipMapper signVipMapper;
    @Autowired
    SignInUserImpl signInUser;
    @Autowired
    DateUtil dateUtil;

    @Autowired
    SignInUserService signInUserService;
    @Test
    void contextLoads() {
        signInUserService.qiBiWithdrawal("15695958932", BigDecimal.valueOf(5));
    }

    @Test
    void test(){
        Map<String,Object> joinMap = new HashMap<String,Object>();
        joinMap.put("userId",1);
        joinMap.put("userPartnerId",1);
        System.out.println("joinMap = " + joinMap);
        int i = signVipMapper.joinAPartner(joinMap);
        System.out.println("i = " + i);
    }

    @Test
    void test1(){
        Result<Map<String, Object>> invitationProgress = signInUser.getInvitationProgress( "15396300728");
        System.out.println("invitationProgress = " + invitationProgress);
    }
}
