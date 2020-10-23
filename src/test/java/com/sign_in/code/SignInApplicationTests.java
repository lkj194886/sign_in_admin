package com.sign_in.code;

import com.sign_in.code.util.Md5Util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;


@SpringBootTest
class SignInApplicationTests {



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

}
