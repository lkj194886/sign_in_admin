package com.sign_in.code.controller;

import com.sign_in.code.entity.SignInConfig;
import com.sign_in.code.service.SignInConfigService;
import com.sign_in.code.util.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Classname SignInConfigController
 * @Description TODO
 * @Date 2020/11/13 22:19
 * @Created by wgg
 */
@RequestMapping("/config")
@RestController
public class SignInConfigController {
    @Autowired
    SignInConfigService signInConfigService;

    //获取首页,邀请,公告图片
    @RequestMapping("/getBackImg")
    Result<Map<String,Object>> getBackImg(){
        return signInConfigService.getBackImg();
    }

    @RequestMapping("/getAdminConfig")
    Result<Map<String,Object>> getAdminConfig(){
        return signInConfigService.getAdminConfig();
    }

    @RequestMapping("/getIndexBack")
    Result<Map<String,Object>> getIndexBack(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){
        return signInConfigService.getIndexBack(pageNum);
    }

    @RequestMapping("/modify")
    Result<Map<String,Object>> modifyConfig(
          @RequestParam("configSignInProps") Double configSignInProps,
          @RequestParam("noticeBackImg") String noticeBackImg,
          @RequestParam("invitationBackImg") String invitationBackImg,
          @RequestParam("download") String download,
          @RequestParam("invitation") String invitation,
          @RequestParam("download_url")  String download_url,
          @RequestParam("configActiveSignIn") Double configActiveSignIn,
          @RequestParam("configActiveWelfare") Double configActiveWelfare,
          @RequestParam("configWithdrawalRate") Double configWithdrawalRate,
          @RequestParam("configVideoCount") int configVideoCount,
          @RequestParam("configAddVideoCount") int configAddVideoCount
    ){
        SignInConfig signInConfig =new SignInConfig();
        signInConfig.setConfigSignInProps(configSignInProps);
        signInConfig.setNoticeBackImg(noticeBackImg);
        signInConfig.setInvitationBackImg(invitationBackImg);
        signInConfig.setDownload(download);
        signInConfig.setDownload_url(download_url);
        signInConfig.setInvitation(invitation);
        signInConfig.setConfigActiveSignIn(configActiveSignIn);
        signInConfig.setConfigActiveWelfare(configActiveWelfare);
        signInConfig.setConfigWithdrawalRate(configWithdrawalRate);
        signInConfig.setConfigVideoCount(configVideoCount);
        signInConfig.setConfigAddVideoCount(configAddVideoCount);
        System.out.println("signInConfig.toString() = " + signInConfig.toString());
        if (signInConfigService.modifyConfig(signInConfig)>0){
            return new Result<>(200,"处理成功",null);
        }
        return new Result<>(500,"处理失败",null);
    }

    @RequestMapping("/modifyIndexBack")
    Result<Map<String,Object>> modifyIndexBack(@RequestParam("cid") Long cid, @RequestParam("indexBack") String indexBack){
        return signInConfigService.modifyIndexBack(cid, indexBack);
    }
}
