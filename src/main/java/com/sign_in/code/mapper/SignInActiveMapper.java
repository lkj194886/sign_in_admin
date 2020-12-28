package com.sign_in.code.mapper;

import com.sign_in.code.entity.SignInActive;
import com.sign_in.code.entity.vo.ActiveVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname SignInActiveMapper
 * @Description TODO
 * @Date 2020/11/29 18:44
 * @Created by wgg
 */
public interface SignInActiveMapper {
   int addActive(SignInActive signInActive);
   List<SignInActive> getActiveList(@Param("uid") Long uid);
   ActiveVo getActiveReward(@Param("uid")Long uid);
}
