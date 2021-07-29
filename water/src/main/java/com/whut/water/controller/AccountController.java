package com.whut.water.controller;

import com.whut.water.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String userName, String userPwd, Model model, HttpSession session) {
        boolean loginResult = accountService.login(userName, userPwd);
        // 条件成立：登录成功，跳转到主页面
        if(loginResult){
            session.setAttribute("userName",userName);
            model.addAttribute("successMassage","登录成功");
            return "waterMainMenu";
        } else {
            model.addAttribute("warningMassage","登录失败");
            model.addAttribute("loginFail","用户名或者密码错误");
            return "index";
        }
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(String userName, Model model, HttpSession session) {
            session.setAttribute("userName","");
            model.addAttribute("successMassage","登出成功");
            return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/preChangePwd",method = RequestMethod.POST)
    public boolean preChangePwd(String oldPwd ,HttpSession session) {
        String userName = (String) session.getAttribute("userName");
        boolean login = accountService.login(userName, oldPwd);
        if(login){
            return true;
        }else {
            return false;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/changePwd",method = RequestMethod.POST)
    public boolean changePwd(String oldPwd, String nowPwd,HttpSession session) {
        String userName = (String) session.getAttribute("userName");
        boolean login = accountService.login(userName, oldPwd);
        if(login){
            boolean b = accountService.changePwd(userName, nowPwd);
            if(!b){
                return false;
            }
            return true;
        }else {
            return false;
        }
    }
}
