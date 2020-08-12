package com.example.asus.cs;
public class Academic {
    private String courseID,code;

    public Academic() {

    }

    public String getCourseID() {
        return courseID;
    }
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public Academic(String courseID, String code) {
        this.courseID = courseID;
        this.code = code;
    }
    public Academic(String courseID) {
        this.courseID = courseID;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }


}