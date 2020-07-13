package com.example.hostelapp;

public class Student {
    private String id,name,pass,number;

    public Student(String id, String name, String pass, String number) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.number = number;
    }

    public Student() {

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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String toString(){
        return this.id+"--"+name;
    }
}
