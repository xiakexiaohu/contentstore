package com.tl.pojo;

import java.util.Date;

public class OrderIterm {
    private String oid;

    private Integer count;

    private String uid;

    private String pid;

    private Double price;

    private Date ctime;

    private Date mtime;

    public OrderIterm(String oid, Integer count, String uid, String pid, Double price, Date ctime, Date mtime) {
        this.oid = oid;
        this.count = count;
        this.uid = uid;
        this.pid = pid;
        this.price = price;
        this.ctime = ctime;
        this.mtime = mtime;
    }

    public OrderIterm() {
        super();
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid == null ? null : oid.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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