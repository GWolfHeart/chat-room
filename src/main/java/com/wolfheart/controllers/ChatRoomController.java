package com.wolfheart.controllers;

import com.wolfheart.beans.Conversation;
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
import java.util.List;
import java.util.Map;

/**
 * @author WolfHeart
 *  聊天室主界面处理器
 */
@Controller
@RequestMapping("/jsp/user")
public class ChatRoomController {

    @Autowired
    @Qualifier("userService")
    private IUserService service;

    public void setService(IUserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/sendMessage.do",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> sendMessage(String message, HttpServletRequest request){

        String username = (String) request.getSession().getAttribute("user");
        Date time = new Date();
        service.saveConversation(new Conversation(time,username,message));

        HashMap<String,String> map = new HashMap<String, String>(16);
        map.put("message",message);
        map.put("user", username);
        return map;

    }

    @RequestMapping(value = "/refreshConversation.do",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<Conversation>> refreshConversation(HttpServletRequest request){

        HashMap<String, List<Conversation>> map = new HashMap<String, List<Conversation>>(16);

        Date loginTime = (Date) request.getSession().getAttribute("loginTime");
        List<Conversation> conversations = service.listConversationByLoginTime(loginTime);
        map.put("conversations",conversations);

        return map;

    }

    @RequestMapping(value = "/logout.do",method = RequestMethod.GET)
    @ResponseBody
    public String doLogout(HttpServletRequest request){

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("user");
        service.updateUserState(new User(username,"password"),0);
        session.invalidate();

        return "ok";

    }

    @RequestMapping(value = "/getUser.do",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> findUser(HttpServletRequest request) {

        Object user = request.getSession().getAttribute("user");
        Map<String, String> map = new HashMap<String, String>(8);

        if (user != null) {
            String username = (String) user;
            map.put("success","true");
            map.put("user",username);
        } else {
            map.put("success","false");
        }
        return map;

    }

    @RequestMapping(value = "/countUsers.do",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> countUsersOnline() {

        HashMap<String, String> map = new HashMap<String, String>(8);

        int count = service.countUsersOnline();
        System.out.println(count);

        return map;

    }

    @RequestMapping(value = "/queryUsersOnline.do",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> queryUsersOnline() {

        HashMap<String, Object> map = new HashMap<String, Object>(8);

        List<User> users = service.listUsersOnline();
        map.put("users", users);
        map.put("usersNum", users.size());

        return map;

    }




}
