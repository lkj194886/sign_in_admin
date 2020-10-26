package com.sign_in.code;

import com.sign_in.code.entity.SignInUser;
import com.sign_in.code.mapper.SignVipMapper;
import com.sign_in.code.mapper.UserMapper;
import com.sign_in.code.service.impl.SignInUserImpl;
import com.sign_in.code.util.Md5Util;
import com.sign_in.code.util.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


@SpringBootTest
class SignInApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    SignVipMapper signVipMapper;
    @Autowired
    SignInUserImpl signInUser;

    @Test
    void contextLoads() {
//        for (int i = 0; i < 100; i++) {
            int flag = new Random().nextInt(9999);
            if (flag<1000){
                flag += 1000;
            }
            System.out.println(flag);
//        }

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
