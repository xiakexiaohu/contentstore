package com.tl.pojo;

import java.util.Date;

public class Cart {
    private String cid;

    private Double price;

    private Integer count;

    private String pid;

    private String uid;

    private Date ctime;

    private Date mtime;

    public Cart(String cid, Double price, Integer count, String pid, String uid, Date ctime, Date mtime) {
        this.cid = cid;
        this.price = price;
        this.count = count;
        this.pid = pid;
        this.uid = uid;
        this.ctime = ctime;
        this.mtime = mtime;
    }

    public Cart() {
        super();
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
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