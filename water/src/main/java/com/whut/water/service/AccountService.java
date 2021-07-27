package com.whut.water.service;


import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    boolean login(String userName,String userPwd);
    boolean changePwd(String userName,String nowPwd);
}
