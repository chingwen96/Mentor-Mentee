package com.example.asus.cs;

import android.widget.EditText;

public class Mentee {
    private String id;
    private String name;

    public Mentee(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Mentee() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
