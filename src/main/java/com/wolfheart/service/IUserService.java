package com.wolfheart.service;

import com.wolfheart.beans.Conversation;
import com.wolfheart.beans.User;

import java.util.Date;
import java.util.List;

/**
 * @author
 *  用户业务服务接口
 */

public interface IUserService {

    /**
     *  用户注册，添加用户
     * @param user  用户信息
     */
    void addUser(User user);


    /**
     *  用户登录，密码校对
     * @param user 用户
     * @return  返回改用户名密码相同的用户
     */
    User selectUserByNamePassword(User user);

    /**
     *  修改登录状态为登录
     * @param user  用户
     * @param loginState    登录状态
     */
    void updateUserState(User user, int loginState);

    /**
     *  查询用户名是否被占用
     * @param username  用户名
     * @return  返回改用户名的用户
     */
    User checkUsername(String username);

    /**
     *  用户发送聊天信息
     * @param conversation  聊天记录
     */
    void saveConversation(Conversation conversation);

    /**
     *  刷新页面会话内容
     * @param loginTime 用户登录时间
     * @return  返回用户登录时间之后的聊天记录
     */
    List<Conversation> listConversationByLoginTime(Date loginTime);

    /**
     *  统计在线登录的用户数量
     * @return  返回在线人数
     */
    int countUsersOnline();


    /**
     *  查找所有在线用户
     * @return  返回在线用户的集合
     */
    List<User> listUsersOnline();

}
