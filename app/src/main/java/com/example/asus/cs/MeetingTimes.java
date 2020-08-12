package com.example.asus.cs;

public class MeetingTimes {
    private String name;
    private int times;
    private String key;

    public MeetingTimes(String name, int times) {
        this.name = name;
        this.times = times;
    }

    public MeetingTimes() {
    }

    public MeetingTimes(String nam) {

    }

    public MeetingTimes(String key,String name, int times) {
        this.name = name;
        this.times = times;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

