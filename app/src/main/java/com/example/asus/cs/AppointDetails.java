package com.example.asus.cs;

import java.util.ArrayList;

class AppointDetails {

    public String date="";
    public String subject="";
    public String description="";

    public static ArrayList<AppointDetails> date_collection_arr;
    public AppointDetails(String date, String subject, String description){

        this.date=date;
        this.subject=subject;
        this.description= description;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getsubject() {
        return subject;
    }

    public void setsubject(String subject) {
        this.subject = subject;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public AppointDetails() {
    }
}