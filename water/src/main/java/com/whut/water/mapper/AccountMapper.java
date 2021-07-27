package com.whut.water.mapper;

import com.whut.water.entities.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AccountMapper {
    /**
     * 用户登录：根据条件查询对应账号信息
     * @param userName 用户名
     * @return 账号信息
     */
    Account login(@Param("userName") String userName);
    int changePwd(@Param("id")Integer id,@Param("nowPwd")String nowPwd);
}