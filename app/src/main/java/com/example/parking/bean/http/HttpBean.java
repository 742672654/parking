package com.example.parking.bean.http;

public class HttpBean {

    private int code;
    private String data;
    private String message;


    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }


    @Override
    public String toString() {
        return "HttpBean{" +
                "code=" + code +
                ", data='" + data + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
