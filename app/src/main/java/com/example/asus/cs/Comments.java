package com.example.asus.cs;

public class Comments {
    private String key;
    private String comment;
    private String name;
    private String date;

    public Comments(String key, String comment, String name, String date) {
        this.key = key;
        this.comment = comment;
        this.name = name;
        this.date = date;
    }

    public Comments(String comment, String name, String date) {
        this.comment = comment;
        this.name = name;
        this.date = date;
    }

    public Comments() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Comments(String name) {
        key = key;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
