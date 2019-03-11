package com.tl.pojo;

import java.util.Date;

public class User {
    private String uid;

    private String username;

    private String password;

    private Integer type;

    private String nickname;

    private Date ctime;

    private Date mtime;

    public User(String uid, String username, String password, Integer type, String nickname, Date ctime, Date mtime) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.type = type;
        this.nickname = nickname;
        this.ctime = ctime;
        this.mtime = mtime;
    }

    public User() {
        super();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }
}