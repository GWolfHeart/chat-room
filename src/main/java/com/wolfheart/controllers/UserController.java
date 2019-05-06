package com.wolfheart.controllers;

import com.wolfheart.beans.User;
import com.wolfheart.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 用户业务处理
 * *注册
 */

@Controller
@RequestMapping("/jsp")
public class UserController {

    @Autowired
    @Qualifier("userService")
    private IUserService service;

    public void setService(IUserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/register.do",method = RequestMethod.POST)
    @ResponseBody
    public String doRegister( String username, String password) {

        User user = new User(username, password);
        service.addUser(user);
        return "ok";

    }

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Integer> doLogin(String username, String password, HttpServletRequest request) {

        Map<String, Integer> map = new HashMap<String, Integer>(8);
        User user = service.selectUserByNamePassword(new User(username, password));
        if (user != null){
            service.updateUserState(user,1);
            HttpSession session = request.getSession();
            Date loginTime = new Date();
            session.setAttribute("user",user.getUsername());
            session.setAttribute("loginTime",loginTime);
            map.put("success",1);
        }else {
            map.put("success",0);
        }
        return map;
    }

    @RequestMapping(value = "/usernameCheck.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Integer> checkUsername(String username) {


        User user = service.checkUsername(username);
        Map<String, Integer> map = new HashMap<String, Integer>(8);
        if (user != null){
            map.put("success",0);
        }else {
            map.put("success",1);
        }
        return map;

    }

}