package com.wolfheart.dao;

import com.wolfheart.beans.Conversation;
import com.wolfheart.beans.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * @author
 *  用户CURD
 */
public interface IUserDao {

    /**
     * 向数据库中添加user
     * @param user  用户
     */
    @Insert("insert into user (username,password) values(#{username},#{password})")
    void insertUser(User user);

    /**
     *  修改用户状态
     * @param loginState
     * @param user
     */
    @Update("update user set logining = #{loginState} where username = #{user.username}")
    void updateUserState(@Param("user") User user,@Param("loginState") int loginState);

    /**
     *  用户名密码验证
     * @param user
     * @return
     */
    @Select("select id,username,password from user where username=#{username} and password=#{password}")
    User selectUser(User user);

    /**
     *  用户名是否重复
     * @param username
     * @return
     */
    @Select("select id,username,password from user where username=#{username}")
    User selectUserByUsername(String username);

    /**
     *  存储用户发送的聊天记录
     * @param conversation
     */
    @Insert("insert into conversation (time,username,message) values(#{time},#{username},#{message})")
    void insertConversation(Conversation conversation);


    /**
     *  查询登录之后的聊天记录
     * @param loginTime
     * @return
     */
    @Select("select id,time,username,message from conversation where time > #{loginTime}")
    List<Conversation> selectConversationByTime(Date loginTime);

    /**
     *  统计在线人数
     * @return 在线人数
     */
    @Select("select count(logining) from user where logining = 1")
    int countUsersOnline();

    /**
     *  从数据库中找所有logining字段为1的用户
     * @return 在线用户
     */
    @Select("select id,username,password,logining from user where logining = 1")
    List<User> listUsersOnline();
}
