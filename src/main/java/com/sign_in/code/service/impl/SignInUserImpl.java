package com.sign_in.code.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sign_in.code.entity.SignInConfig;
import com.sign_in.code.entity.SignInUser;
import com.sign_in.code.entity.SignTable;
import com.sign_in.code.entity.vo.ActiveVo;
import com.sign_in.code.mapper.*;
import com.sign_in.code.service.SignInUserService;
import com.sign_in.code.util.DateUtil;
import com.sign_in.code.util.RedisUtil;
import com.sign_in.code.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

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

    @Autowired
    SignInMapper signInMapper;
    @Autowired
    DateUtil dateUtil;

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
                //将信息存入map中返回前端app
                Map<String, Object> map = new HashMap<>();
                map.put("signExist", existSign(signInUser.getUserId()));
                map.put("user", signInUser);
                //获取邀请人邀请码
                if (signInUser.getUserInvitationId() != null) {
                    SignInUser userInvitationId = userMapper.getUserId(signInUser.getUserInvitationId());
                    map.put("userInvitation", userInvitationId.getUserInvitationCode());
                }
                SignTable signTable = signInMapper.getSignIn(signInUser.getUserId());
                String indexBack = "";

                if (signTable == null) {
                    indexBack = signInConfigMapper.getIndexBackImg(1l);
                } else {
                    if (signTable.getSignInDaysCount() == 0) {
                        indexBack = signInConfigMapper.getIndexBackImg(1l);
                    } else {
                        indexBack = signInConfigMapper.getIndexBackImg(signTable.getSignInDaysCount());
                    }

                }
                Double withdrawalRate = signInConfigMapper.getWithdrawalRate();
                Double videoRate = signInConfigMapper.getSignInConfig().getVideoRate();
                map.put("withdrawalRate", withdrawalRate);
                map.put("videoRate", videoRate);
                map.put("indexBack", indexBack);
                map.put("videCount", signInConfigMapper.getSignInConfig().getConfigVideoCount());
                int date = new Long(dateUtil.getExcess()).intValue();
                String videoCount = String.valueOf(signInConfigMapper.getSignInConfig().getConfigVideoCount());
                if (redisUtil.get(phone + "videoCount") == null) {
                    redisUtil.set(phone + "videoCount", videoCount, date);
                }
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

    @Autowired
    SignInActiveMapper signInActiveMapper;

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
        List<ActiveVo> list = new ArrayList<>();
        for (SignInUser progress : invitationProgress) {
//            uid.add(progress.getUserId());
//            map.put(progress.getUserId(),signInActiveMapper.getActiveReward(progress.getUserId()));
//            System.out.println("progress.getUserId() = " + progress.getUserId());
            ActiveVo activeVo = new ActiveVo();
            activeVo = signInActiveMapper.getActiveReward(progress.getUserId());
            if (activeVo == null) {
                ActiveVo activeVo1 = new ActiveVo();
                activeVo1.setUid(progress.getUserId());
                activeVo1.setActiveReward(BigDecimal.valueOf(0));
                list.add(activeVo1);
            }
            if (activeVo != null) {
                list.add(activeVo);
            }
        }
        System.out.println("list.toString() = " + list.toString());
        for (int i = 0; i < list.size(); i++) {
            System.out.println("list.get(i) = " + list.get(i));
            invitationProgress.get(i).setActiveVo(list.get(i));

        }
        maps.put("actives", null);
        if (invitationProgress != null) {
            return new Result<>(200, "处理成功", maps);
        }
        return new Result<>(500, "错误请求", null);
    }

    //查询今日是否已签到
    public boolean existSign(Long uid) {
        SignTable signTable = signInMapper.getSignIn(uid);
        if (signTable != null) {
            //获取签到时间
            Date date = signTable.getSignInDate();
            //格式化时间
            Map<String, Object> dateMap = dateUtil.getDateString(date);
            Map<String, Object> newdateMap = dateUtil.getTheCurrentDate();
            if (dateMap.get("year").equals(newdateMap.get("year")) && dateMap.get("month").equals(newdateMap.get("month")) && dateMap.get("date").equals(newdateMap.get("date"))) {
                return false;
            }
        } else {
            return true;
        }
        System.out.println();
//        return true;
        return true;
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
                Map<String, Object> map = new HashMap<>();
                map.put("signExist", existSign(uid));
                SignInUser signInUser = userMapper.getUserId(uid);
                SignInUser userInvitationId = userMapper.getUserId(signInUser.getUserInvitationId());
                map.put("user", signInUser);
                SignTable signTable = signInMapper.getSignIn(signInUser.getUserId());
                String indexBack = "";
                if (signTable.getSignInDaysCount() == 0 || signTable == null) {
                    indexBack = signInConfigMapper.getIndexBackImg(1l);
                } else {
                    indexBack = signInConfigMapper.getIndexBackImg(signTable.getSignInDaysCount());
                }
                Double withdrawalRate = signInConfigMapper.getWithdrawalRate();
                Double videoRate = signInConfigMapper.getSignInConfig().getVideoRate();
                map.put("withdrawalRate", withdrawalRate);
                map.put("videoRate", videoRate);
                map.put("indexBack", indexBack);
//                map.put("videCount", signInConfigMapper.getSignInConfig().getConfigVideoCount());
                if (userInvitationId != null) {
                    map.put("userInvitation", userInvitationId.getUserInvitationCode());
                }

                return new Result<>(200, "处理成功", map);
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
            int bound = userMapper.boundZhiFuBao(uid, zhiFUBao, zhiFUBaoName);
            if (bound > 0) {
                Map<String, Object> map = new HashMap<>();
                map.put("signExist", existSign(uid));
                SignInUser signInUser = userMapper.getUserId(uid);
                SignInUser userInvitationId = userMapper.getUserId(signInUser.getUserInvitationId());
                map.put("user", signInUser);
                map.put("userInvitation", userInvitationId.getUserInvitationCode());
                SignTable signTable = signInMapper.getSignIn(signInUser.getUserId());
                String indexBack = "";
                if (signTable.getSignInDaysCount() == 0 || signTable == null) {
                    indexBack = signInConfigMapper.getIndexBackImg(1l);
                } else {
                    indexBack = signInConfigMapper.getIndexBackImg(signTable.getSignInDaysCount());
                }
                Double withdrawalRate = signInConfigMapper.getWithdrawalRate();
                Double videoRate = signInConfigMapper.getSignInConfig().getVideoRate();
                map.put("withdrawalRate", withdrawalRate);
                map.put("videoRate", videoRate);
                map.put("indexBack", indexBack);
//                map.put("videCount", redisUtil.get(signInUser.getUserName() + "videoCount"));
                return new Result<>(200, "处理成功", map);
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
    public Result<Map<String, Object>> qiBiWithdrawal(String userPhone, BigDecimal qiBi) throws InterruptedException {
        Thread.sleep(2000);
        if (redisUtil.get(userPhone) != null) {
            SignInUser signInUser = userMapper.getUser(userPhone);
            if (qiBi == null) {
                return new Result<>(500, "兑换道具不能为空", null);
            }
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

    @Value("${file.address}")
    public String address;

    @Override
    public Result<Map<String, Object>> getInvitationImg(String code) throws IOException {
        String phonecode = code;
        SignInConfig signInConfig = signInConfigMapper.getSignInConfig();
        SignInUser signInUser = userMapper.getUser(code);

        String a = address + signInConfig.getInvitation();
        String b = address + "/newinvitation/" + code + ".jpg";
        String content = "邀请码:" + signInUser.getUserInvitationCode();
        Font font = new Font("宋体", Font.PLAIN, 25);
        File file = new File(a);
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
        FileOutputStream outputStream = new FileOutputStream(b);
        boolean reslut = ImageIO.write(bufferedImage, "jpg", outputStream);
        System.out.println("添加水印完成");
        outputStream.flush();
        outputStream.close();
        Map<String, Object> map = new HashMap<>();
        if (reslut) {
            map.put("imgsrc", "/newinvitation/" + code + ".jpg");
            return new Result<>(200, "处理成功", map);
        }
        return new Result<>(500, "处理失败", null);
    }

    //注册时生成邀请码
    @Override
    public String generateRandomStr() {
        boolean exist = true;
        String rtnStr = "";
        while (exist) {
            String generateSource = "23456789abcdefghgklmnpqrstuvwxyz";//去掉1和i ，0和o
            for (int i = 0; i < 7; i++) {
                //循环随机获得当次字符，并移走选出的字符
                String nowStr = String.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
                rtnStr += nowStr;
                generateSource = generateSource.replaceAll(nowStr, "");
            }
            rtnStr.toUpperCase();
            if (userMapper.existCode(rtnStr.toUpperCase()) == null) {
                exist = false;
            }
        }
        return rtnStr.toUpperCase();
    }

    @Autowired
    SignInConfigMapper signInConfigMapper;

    @Override
    public Result<Map<String, Object>> addUser(String phone, String yqmCode, String phoneCode) {
        if (redisUtil.get(phone) != null) {
            if (redisUtil.get(phone).equals(phoneCode)) {
                redisUtil.remove(phone);
                if (userMapper.getUser(phone) != null) {
                    return new Result<>(500, "该手机号已注册", null);
                }
                SignInUser signInUser = new SignInUser();
                signInUser.setUserName(phone);
                signInUser.setUserVipId(1l);
                signInUser.setUserRemainingSum(BigDecimal.valueOf(100));
                signInUser.setUserQiBi(BigDecimal.valueOf(0));
                if (yqmCode.isEmpty()) {
                    Long yqUid = userMapper.getUid(yqmCode);
                    signInUser.setUserInvitationId(yqUid);
                }
                signInUser.setUserInvitationCode(generateRandomStr());
                Map<String, Object> map = new HashMap<>();
                if (userMapper.addUser(signInUser) > 0) {
                    SignInConfig signInConfig = signInConfigMapper.getSignInConfig();
                    if (signInConfig != null) {
                        String download_url = signInConfig.getDownload_url();
                        map.put("download_url", download_url);
                    }
                    if (yqmCode.isEmpty()) {
                        long userid = userMapper.getUid(yqmCode);
                        String userPhone = userMapper.getUserId(userid).getUserName();
                        System.out.println("userPhone = " + userPhone);
                        int count = 0;
                        if (redisUtil.get(userPhone + "videoCount") == null) {
                            count = signInConfig.getConfigVideoCount();
                        } else {
                            count = Integer.parseInt(redisUtil.get(userPhone + "videoCount"));
                        }
                        int videoCount = signInConfigMapper.getSignInConfig().getConfigAddVideoCount();
                        int date = new Long(dateUtil.getExcess()).intValue();
                        redisUtil.set(userPhone + "videoCount", String.valueOf(count + videoCount), date);
                    }
                    return new Result<>(200, "处理成功", map);
                }
            } else {
                return new Result<>(500, "验证码不正确", null);
            }
        } else {
            return new Result<>(403, "验证码已失效,请重新获取", null);
        }
        return new Result<>(500, "处理失败", null);
    }

    @Override
    public Result<Map<String, Object>> getVideoCount(String phone) {
        String videoCount = redisUtil.get(phone + "videoCount");
        Map<String, Object> map = new HashMap<>();
        map.put("videoCount", videoCount);
        return new Result<>(200, "处理成功", map);
    }

    @Override
    public Result<Map<String, Object>> addVideoCount(String phone) {
        if (redisUtil.get(phone + "videoCount") != null) {
            int count = Integer.parseInt(redisUtil.get(phone + "videoCount"));
            int videoCount = signInConfigMapper.getSignInConfig().getConfigAddVideoCount();
            int date = new Long(dateUtil.getExcess()).intValue();
            redisUtil.set(phone + "videoCount", String.valueOf(count + videoCount), date);
            Map<String, Object> map = new HashMap<>();
            map.put("videoCount", redisUtil.get(phone + "videoCount"));
            return new Result<>(200, "处理成功", map);
        }
        return new Result<>(500, "处理失败", null);
    }


    @Override
    public Result<Map<String, Object>> lessenVideoCount(String phone, int videoCount) {
        if (redisUtil.get(phone + "videoCount") != null) {
            String videoCounta = redisUtil.get(phone + "videoCount");
            System.out.println("videoCounta = " + videoCounta);
            int videoCountb = Integer.parseInt(videoCounta);
            System.out.println("videoCountb = " + videoCountb);
            String a = String.valueOf(videoCountb - videoCount);
            int date = new Long(dateUtil.getExcess()).intValue();
            String videoCountc = String.valueOf(a);
            redisUtil.set(phone + "videoCount", videoCountc, date);
            Map<String, Object> map = new HashMap<>();
            map.put("videoCount", videoCountc);
            return new Result<>(200, "处理成功", map);
        }
        return new Result<>(500, "处理失败", null);
    }

    @Override
    public Result<Map<String, Object>> getUserList(Integer pageNum, String userAdminName, String conditions) {
        if (redisUtil.get(userAdminName) != null) {
            redisUtil.set(userAdminName, userAdminName, 3000);
            Map<String, Object> map = new HashMap<>();
            PageHelper.startPage(pageNum, 5);
            List<SignInUser> userList = userMapper.getUserList(conditions);
            if (userList != null) {
                PageInfo<SignInUser> pageInfo = new PageInfo<SignInUser>(userList);
                map.put("pageInfo", pageInfo);
                return new Result<>(200, "处理成功", map);
            }
        } else {
            return new Result<>(403, "请重新登陆", null);
        }
        return new Result<>(500, "处理失败", null);
    }

    @Override
    public Result<Map<String, Object>> updateUser(SignInUser signInUser) {
//        if ()
        int count = userMapper.UpdateUser(signInUser);
        if (count > 0) {
            return new Result<>(200, "处理成功", null);
        }
        return new Result<>(500, "处理失败", null);
    }

    @Override
    public Result<Map<String, Object>> addSum(Long uid) {
        BigDecimal d = BigDecimal.valueOf(signInConfigMapper.getSignInConfig().getVideoRate());
        if (cumulativeProps(uid, d) > 0) {
            return new Result<>(200, "处理成功", null);
        }
        return new Result<>(500, "处理失败", null);
    }

    public int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }

}
