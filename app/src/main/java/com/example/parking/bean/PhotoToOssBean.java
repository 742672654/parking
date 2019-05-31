package com.example.parking.bean;


public class PhotoToOssBean {

    private int code;
    private String  message; // "SUCCESS"
    private photoToOssData data;


    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public photoToOssData getData() {
        return data;
    }
    public void setData(photoToOssData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "photoToOssBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class photoToOssData{

        private String carmun;
        private String imgurl;

        public String getCarmun() {
            return carmun;
        }
        public void setCarmun(String carmun) {
            this.carmun = carmun;
        }
        public String getImgurl() {
            return imgurl;
        }
        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        @Override
        public String toString() {
            return "photoToOssData{" +
                    "carmun='" + carmun + '\'' +
                    ", imgurl='" + imgurl + '\'' +
                    '}';
        }
    }

}
