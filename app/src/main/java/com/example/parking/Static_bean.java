package com.example.parking;

public class Static_bean {

    /**
     * TODO 登录，返回个人信息
     * @param phone 账号
     * @param password 密码
     * @param edition 版本号
     */
    public final static String login = "https://parking.yilufa.net:18443/park/usersub/login";

    /**
     * TODO 车牌照片上传接口，返回车牌
     * @param file 文件
     */
    public final static String photoToOss = "https://parking.yilufa.net:18443/park/upload/photoToOss";

    /**
     * TODO 查询全部车位接口
     * @param token
     */
    public final static String selectSubPlace = "https://parking.yilufa.net:18443/point/order/parkListToFirstPage";

    /**
     * TODO 查询可以车位接口
     * @param token
     */
    public final static String selectOrderSubPlace = "https://parking.yilufa.net:18443/park/parkplacesub/selectOrderSubPlace";

    /**
     * TODO 订单保存
     * @param token
     */
    public final static String orderAdd = "http://192.168.1.188:18442/point/order/add";

    /**
     * TODO 订单查询接口
     * @param token
     */
    public final static String orderlist = "https://parking.yilufa.net:18443/point/order/orderlist";

    /**
     * TODO 订单详情
     * @param token
     * @param id 订单id
     * @param camum 车牌号
     */
    public final static String getLeavePageOrder = "https://parking.yilufa.net:18443/point/order/getLeavePageOrder";

    /**
     * TODO 订单收费
     * @param token
     * @param id Integer 订单id
     * @param orderprice Double订单总价
     * @param subid Stirng 车位id
     * @param subname Stirng 车位名称
     * @param outimage String 出场识别照片URL
     */
    public final static String payPointOrderToPoint = "https://parking.yilufa.net:18443/point/order/payPointOrderToPoint";

    /**
     * TODO 逃费
     * @param token
     * @param id Integer 订单id
     * @param carnum 车牌号
     * @param subid Stirng 车位id
     * @param subname Stirng 车位名称
     */
    public final static String escape_add = "https://parking.yilufa.net:18443/point/escape/add";

    /**
     * TODO 一键出场
     * @param token
     */
    public final static String allCharge = "https://parking.yilufa.net:18443/point/order/allCharge";

    /**
     * TODO 个人页面初始化
     * @param token
     */
    public final static String getMinePage = "https://parking.yilufa.net:18443/point/order/getMinePage";

    /**
     * TODO 修改密码
     * @param token
     * @param chkpwd
     */
    public final static String changePwds = "https://parking.yilufa.net:18443/park/usersub/changePwds";


    /**
     * TODO 剩余车位和预约车位数量
     * @param token
     */
    public final static String firstPageRecord = "https://parking.yilufa.net:18443/point/order/firstPageRecord";

}
