package com.nju.tourSystem.entity;

public class Participant {
    private int id;
    private String uid;
    private int aid;
    private String agree;
    private double score;
    private String applyTime;
    private String agreeTime;

    public Participant() {
    }

    public int getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String isAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getAgreeTime() {
        return agreeTime;
    }

    public void setAgreeTime(String agreeTime) {
        this.agreeTime = agreeTime;
    }
}
