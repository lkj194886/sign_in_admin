package com.sign_in.code;

import com.sign_in.code.entity.SignInUser;
import com.sign_in.code.entity.SignWithdrawal;
import com.sign_in.code.mapper.SignVipMapper;
import com.sign_in.code.mapper.UserMapper;
import com.sign_in.code.mapper.WithdrawalMapper;
import com.sign_in.code.service.SignInUserService;
import com.sign_in.code.service.impl.SignInUserImpl;
import com.sign_in.code.util.DateUtil;
import com.sign_in.code.util.Md5Util;
import com.sign_in.code.util.Result;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;


@SpringBootTest
class SignInApplicationTests {
    //    void addWaterMark(String)

    void test2() {
        BigDecimal a = new BigDecimal(0.1);
        BigDecimal b = new BigDecimal(0.3);
        if (a.compareTo(b)==-1){
            System.out.println("小于");
        }

    }
}
