package com.whut.water.service;

import com.whut.water.entities.Account;
import com.whut.water.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Objects;


@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public boolean login(String userName, String userPwd) {
        //使用md5加密
        String md5Pwd = DigestUtils.md5DigestAsHex(userPwd.getBytes());
        if(log.isDebugEnabled()){
            log.debug(md5Pwd);
        }
        Account account = accountMapper.login(userName);
        // 条件成立：表示数据库中没有对应的用户名，登录失败
        if(null == account) {
            return false;
        }
        // 比较密码是否一致
        // 条件成立：参数的用户名密码和数据库用户名密码一致登录成功
        if(Objects.equals(md5Pwd,account.getUserPwd())){
            return true;
        }
        return false;
    }

    @Transactional(rollbackFor = {Exception.class,Error.class})
    @Override
    public boolean changePwd(String userName, String nowPwd) {
        String oldMd5 = DigestUtils.md5DigestAsHex(nowPwd.getBytes());
        Account login = accountMapper.login(userName);
        if(login!=null){
            int i = accountMapper.changePwd(login.getId(), oldMd5);
            if(i>0){
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }
}
