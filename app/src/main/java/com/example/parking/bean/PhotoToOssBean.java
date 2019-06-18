package com.example.parking.bean;


public class PhotoToOssBean {

    private String imgurl;
    private String carmun;

    public String getImgurl() {
        return imgurl;
    }
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
    public String getCarmun() {
        return carmun;
    }
    public void setCarmun(String carmun) {
        this.carmun = carmun;
    }

    @Override
    public String toString() {
        return "PhotoToOssBean{" +
                "imgurl='" + imgurl + '\'' +
                ", carmun='" + carmun + '\'' +
                '}';
    }
}
