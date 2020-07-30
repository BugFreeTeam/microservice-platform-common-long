package com.anjuxing.platform.common.message;

import java.io.Serializable;
import java.util.Date;

public class CommandMessage implements Serializable {

    private String id;  //消息ID
    private String userid;  //用户ID
    private String flag;  //指令
    private Date sendTime; //发送时间
    private Date receiveTime;  //接收时间
    private Integer status;  //状态，主机是否接收成功，0失败，1成功
    private String password; //开门密码

    public CommandMessage() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
