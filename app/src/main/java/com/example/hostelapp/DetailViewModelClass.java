package com.example.hostelapp;

import java.util.ArrayList;

public class DetailViewModelClass {

    String title;
    ArrayList<stdModify> list;


    public DetailViewModelClass(String title, ArrayList<stdModify> list) {
        this.title = title;
        this.list = list;
    }

    public DetailViewModelClass() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<stdModify> getList() {
        return list;
    }

    public void setList(ArrayList<stdModify> list) {
        this.list = list;
    }
}
