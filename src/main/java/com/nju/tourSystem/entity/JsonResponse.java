package com.nju.tourSystem.entity;

public class JsonResponse {
    private String status = null;
    private Object data = null;

    public JsonResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
