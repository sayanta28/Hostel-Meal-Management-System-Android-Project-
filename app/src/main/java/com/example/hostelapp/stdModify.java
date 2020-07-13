package com.example.hostelapp;

import androidx.annotation.NonNull;

public class stdModify {
    private String id;


    private  String name;

    public stdModify() {
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

    public stdModify(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return getId() + getName();
    }
}