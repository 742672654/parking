package com.example.parking.bean;




public class UserBean {


    private String phone;
    private String password;

    private String avatarUrl;
    private String nickName;
    private String token;

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

}
