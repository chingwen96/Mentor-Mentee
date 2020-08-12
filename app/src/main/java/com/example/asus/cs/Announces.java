package com.example.asus.cs;

public class Announces {
    private String annName;
    private String announce;
    private String date;
    private String key;

    public Announces(String key,String annName, String announce, String date) {
        this.annName = annName;
        this.announce = announce;
        this.date = date;
        this.key = key;
    }

    public Announces(String annName, String announce, String date) {
        this.annName = annName;
        this.announce = announce;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Announces() {
    }

    public String getAnnounce() {
        return announce;
    }

    public void setAnnounce(String announce) {
        this.announce = announce;
    }

    public String getAnnName() {
        return annName;
    }

    public void setAnnName(String annName) {
        this.annName = annName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
