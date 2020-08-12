package com.example.asus.cs;

public class User {
    private String Name;
    private String Password;
    private String ID;
    private String address;
    private String mentorName;

    public User(String name, String password, String ID, String address, String mentorName) {
        Name = name;
        Password = password;
        this.ID = ID;
        this.address = address;
        this.mentorName = mentorName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public User(String name, String password, String ID) {
        Name = name;
        Password = password;
        this.ID = ID;
    }

    public User() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public User(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }
}

