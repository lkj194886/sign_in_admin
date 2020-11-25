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
import java.util.*;


@SpringBootTest
class SignInApplicationTests {
//    void addWaterMark(String)
    @Test
    void test1() throws IOException {
        String code = "15695958932";
        String a = address+"/invitation/邀请页.png";
        String b = address+"/newinvitation/"+code+".jpg";
        String content = "邀请码:111123";
        Font font = new Font("宋体", Font.PLAIN, 25);
        File file = new File(a);
        Image imgsrc = ImageIO.read(file);
        int width = imgsrc.getWidth(null);
        int height = imgsrc.getHeight(null);
        BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(imgsrc,0,0,width,height,null);
        g.setColor(Color.WHITE);
        g.setFont(font);
        int x = (width-getWatermarkLength(content,g)) /2;
        int y = height-290;
        System.out.println(getWatermarkLength(content,g));
        g.drawString(content,x,y);
        g.dispose();
        FileOutputStream outputStream = new FileOutputStream(b);
        ImageIO.write(bufferedImage,"jpg",outputStream);
        System.out.println("添加水印完成");
        outputStream.flush();
        outputStream.close();
    }

    public int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }

    @Value("${file.address}")
    public String address;
    @Test
     void test2(){
//        System.out.println("address = " +);
    }
}
