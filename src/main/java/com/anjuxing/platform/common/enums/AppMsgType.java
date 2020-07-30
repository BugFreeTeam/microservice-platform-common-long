package com.anjuxing.platform.common.enums;

/**
 * Created by xiaop on 2017/7/3.
 */
public enum  AppMsgType {
    receiveNewAuditMsg("001"),
    applying("002"),
    approved("003"),
    disapproved("004"),
    otherDeviceLogin("005");

    private String title;

    private AppMsgType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static AppMsgType titleOf(String title) {
        for (AppMsgType type : AppMsgType.values()) {
            if (type.title.equals(title)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Arbitrator status:'" + title + "' not exists!");
    }
}
