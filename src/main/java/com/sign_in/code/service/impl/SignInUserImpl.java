package com.sign_in.code.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.sign_in.code.config.FilePathConfig;
import com.sign_in.code.entity.SignInUser;
import com.sign_in.code.mapper.SignVipMapper;
import com.sign_in.code.mapper.UserMapper;
import com.sign_in.code.service.SignInUserService;
import com.sign_in.code.util.RedisUtil;
import com.sign_in.code.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname SignInUserImpl
 * @Description TODO
 * @Date 2020/10/20 15:53
 * @Created by wgg
 */
@Service
public class SignInUserImpl implements SignInUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    SignVipMapper signVipMapper;

    /**
     * 登陆接口
     *
     * @param phone     电话号码
     * @param phoneCode 验证码
     * @return 返回用户信息
     */
    @Override
    public Result<Map<String, Object>> getUser(String phone, String phoneCode) {
        //判断是否存在该手机号的验证码
        if (redisUtil.get(phone) == null) {
            return new Result<>(404, "验证码已失效,请重新获取", null);
        } else {
            //判断验证码是否一致
            if (phoneCode.equals(redisUtil.get(phone))) {
                //验证成功,删除redis中的验证码
                redisUtil.remove(phone);
                //获取登陆用户信息
                SignInUser signInUser = userMapper.getUser(phone);
                //获取邀请人邀请码
                SignInUser userInvitationId = userMapper.getUserId(signInUser.getUserInvitationId());
                //将信息存入map中返回前端app
                Map<String, Object> map = new HashMap<>();
                map.put("user", signInUser);
                map.put("userInvitation", userInvitationId.getUserInvitationCode());
                String userJson = JSONObject.toJSONString(map);
                //将数据库信息存入redis当中,并设置三十分钟过期时间
                redisUtil.set(phone, userJson, 1800);
                return new Result<>(200, "处理成功", map);
            }
            //验证码错误返回信息
            return new Result<>(500, "验证码错误", null);
        }

    }


    /**
     * 查询邀请人id
     *
     * @param phone
     * @return
     */
    @Override
    public long getUserInvitationId(String phone) {
        return userMapper.getUserInvitationId(phone);
    }


    /**
     * 邀请进度
     *
     * @param invitationCodePhone
     * @return
     */
    @Override
    public Result<Map<String, Object>> getInvitationProgress(String invitationCodePhone) {
        long invitationId = getUserInvitationId(invitationCodePhone);
        Map<String, Object> maps = new HashMap<>();
        List<SignInUser> invitationProgress = userMapper.getInvitationProgress(invitationId);
        maps.put("invitationList", invitationProgress);
        if (invitationProgress != null) {
            return new Result<>(200, "处理成功", maps);
        }
        return new Result<>(500, "错误请求", null);
    }

    /**
     * 绑定微信
     *
     * @param uid          用户ID
     * @param weiXinNumber 用户微信号
     * @param weiXinName   用户微信姓名
     * @return
     */
    @Override
    public Result<Map<String, Object>> boundWeiXin(Long uid, String weiXin, String weiXinName) {
        //判断参数是否为空
        if ((weiXin != null && weiXin != "") && (weiXinName != null && weiXinName != "")) {
            //不为空调用方法
            int bound = userMapper.boundWeiXin(uid, weiXin, weiXinName);
            //判断数据库是否修改成功
            if (bound > 0) {
                return new Result<>(200, "处理成功", null);
            }
        } else {
            //为空返回错误
            return new Result<>(500, "微信账号和姓名不能为空", null);
        }
        //错误返回
        return new Result<>(500, "处理失败,\n请联系管理员!", null);
    }

    /**
     * 绑定支付宝
     *
     * @param uid            用户id
     * @param zhiFuBaoNumber 用户支付宝账号
     * @param zhiFuBaoName   支付宝姓名
     * @return
     */
    @Override
    public Result<Map<String, Object>> boundZhiFuBao(Long uid, String zhiFUBao, String zhiFUBaoName) {
        if ((zhiFUBao != null && zhiFUBao != "") && (zhiFUBaoName != null && zhiFUBaoName != "")) {
            int bound = userMapper.boundWeiXin(uid, zhiFUBao, zhiFUBaoName);
            if (bound > 0) {
                return new Result<>(200, "处理成功", null);
            }
        } else {
            return new Result<>(500, "支付宝账号和姓名不能为空", null);
        }
        return new Result<>(500, "处理失败,\n请联系管理员!", null);
    }

    /**
     * 签到道具累加
     *
     * @param uid  用户ID
     * @param qiBi 增加的道具数量
     * @return
     */
    @Override
    public int cumulativeProps(Long uid, BigDecimal qiBi) {
        SignInUser signInUser = new SignInUser();
        signInUser = userMapper.getUserId(uid);
        BigDecimal qiBiCount = signInUser.getUserQiBi();
        qiBiCount = qiBiCount.add(qiBi);
        return userMapper.cumulativeProps(uid, qiBiCount);
    }

    /**
     * 七币兑换
     *
     * @param userPhone 用户手机号
     * @param qiBi      兑换数量
     * @return
     */
    @Override
    public Result<Map<String, Object>> qiBiWithdrawal(String userPhone, BigDecimal qiBi) {
        if (redisUtil.get(userPhone) != null) {
            SignInUser signInUser = userMapper.getUser(userPhone);
            if (signInUser.getUserQiBi().compareTo(qiBi) == -1) {
                return new Result<>(500, "七币不足", null);
            }
            //获取七币兑换率
            Double vipChangeRate = signVipMapper.getExchangeRate(signInUser.getUserVipId()) / 100;
            //兑换所得金额
            BigDecimal balance = qiBi.subtract(qiBi.multiply(BigDecimal.valueOf(vipChangeRate)));
            //将兑换所得与账户余额相加
            balance = signInUser.getUserRemainingSum().add(balance);
            //减少用户户七币
            qiBi = signInUser.getUserQiBi().subtract(qiBi);
            if (userMapper.qiBiWithdrawal(userPhone, balance, qiBi) > 0) {
                return new Result<>(400, "处理成功", null);
            }
        } else {
            return new Result<>(401, "登陆已过期,\n请重新登陆!", null);
        }
        return new Result<>(500, "处理失败,\n请联系管理员", null);
    }

    @Override
    public Result<Map<String, Object>> getInvitationImg(String code) throws IOException {
        String img= "";
        String newimgsrc = "";
        String content = code;
        Font font = new Font("宋体", Font.PLAIN, 25);
        File file = new File(img);
        Image imgsrc = ImageIO.read(file);
        int width = imgsrc.getWidth(null);
        int height = imgsrc.getHeight(null);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(imgsrc, 0, 0, width, height, null);
        g.setColor(Color.WHITE);
        g.setFont(font);
        int x = (width - getWatermarkLength(content, g)) / 2;
        int y = height - 290;
        System.out.println(getWatermarkLength(content, g));
        g.drawString(content, x, y);
        g.dispose();
        FileOutputStream outputStream = new FileOutputStream(newimgsrc);
        boolean reslut = ImageIO.write(bufferedImage, "jpg", outputStream);
        outputStream.flush();
        outputStream.close();
        Map<String,Object> map =new HashMap<>();
        if (reslut){
            map.put("imgsrc",newimgsrc);
            return new Result<>(200,"处理成功",map);
        }
        return new Result<>(500,"处理失败",null);
    }

    public int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }

}
