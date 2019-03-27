package com.nju.tourSystem.PO;

public class UserInfo {
    private String userid;
    private String columnid;
    private String value;

    public UserInfo() {
    }

    public String getUserid() {
        return userid;
    }

    public String getColumnid() {
        return columnid;
    }

    public void setColumnid(String columnid) {
        this.columnid = columnid;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
