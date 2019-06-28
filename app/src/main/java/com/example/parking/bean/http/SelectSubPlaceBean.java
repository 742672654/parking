package com.example.parking.bean.http;

import java.io.Serializable;
import java.util.List;

public class SelectSubPlaceBean implements Serializable{


    private Integer code;
    private String message;
    private List<SelectSubPlaceData> data;


    public Integer getCode() { return code; }
    public void setCode(Integer code) { this.code = code; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public List<SelectSubPlaceData> getData() { return data; }
    public void setData(List<SelectSubPlaceData> data) { this.data = data; }

    @Override
    public String toString() {
        return "SelectSubPlaceBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }


    public static class SelectSubPlaceData implements Serializable {

        private String id;  //车位id
        private int havecar;// 2,   /2是有车，1是没车
        private String code;// "测试6",  编号
        private long parktime;// 1561460260000, 入场时间
        private Integer preprice;// 8,  //预支付金额
        private String carnum;//"渝M5434N"

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public int getHavecar() { return havecar; }
        public void setHavecar(int havecar) { this.havecar = havecar; }
        public String getCode() { return code; }
        public void setCode(String code) {this.code = code; }
        public long getParktime() { return parktime; }
        public void setParktime(long parktime) { this.parktime = parktime; }
        public Integer getPreprice() { return preprice; }
        public void setPreprice(Integer preprice) {
            this.preprice = preprice;
        }
        public String getCarnum() { return carnum; }
        public void setCarnum(String carnum) {
            this.carnum = carnum;
        }

        @Override
        public String toString() {
            return "SelectSubPlaceData{" +
                    "id=" + id +
                    "havecar=" + havecar +
                    ", code='" + code + '\'' +
                    ", parktime=" + parktime +
                    ", preprice=" + preprice +
                    ", carnum='" + carnum + '\'' +
                    '}';
        }
    }

}

