package com.wolfheart.beans;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author WolfHeart
 *  聊天记录
 */
public class Conversation {

    private Integer id;
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date time;
    private String username;
    private String message;

    public Conversation() {
    }

    public Conversation(Date time, String username, String message) {
        this.time = time;
        this.username = username;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id=" + id +
                ", time=" + time +
                ", username='" + username + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
