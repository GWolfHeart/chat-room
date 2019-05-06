package com.wolfheart.beans;

/**
 * @author WolfHeart
 *  用户类
 *      *用户id
 *      *用户名
 *      *密码
 *
 */

public class User {

    private Integer id;
    private String username;
    private String password;
    private int logining;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLogining() {
        return logining;
    }

    public void setLogining(int logining) {
        this.logining = logining;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}