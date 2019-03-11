package com.tl.pojo;

import java.util.Date;

public class Product {
    private String pid;

    private String ptitle;

    private String pimage;

    private Integer pcount;

    private Double price;

    private String psummary;

    private String pdesc;

    private String uid;

    private Date ctime;

    private Date mtime;

    public Product(String pid, String ptitle, String pimage,Integer pcount, Double price, String psummary, String pdesc, String uid, Date ctime, Date mtime) {
        this.pid = pid;
        this.ptitle = ptitle;
        this.pimage = pimage;
        this.pcount = pcount;
        this.price = price;
        this.psummary = psummary;
        this.pdesc = pdesc;
        this.uid = uid;
        this.ctime = ctime;
        this.mtime = mtime;

    }

    public Product() {
        super();
    }

    public String getPid() {
        return pid;
    }

    public int getPcount() {
        return pcount;
    }

    public void setPcount(Integer pcount) {
        this.pcount = pcount;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle == null ? null : ptitle.trim();
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage == null ? null : pimage.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPsummary() {
        return psummary;
    }

    public void setPsummary(String psummary) {
        this.psummary = psummary == null ? null : psummary.trim();
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc == null ? null : pdesc.trim();
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
