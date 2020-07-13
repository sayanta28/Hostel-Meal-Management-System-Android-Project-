package com.example.hostelapp;

public class adminmodelclass {

    String id,pass,name,phone,notice;
    int breakfast=0,lunch=0,dinner=0;
    int TotalMonthlyBill=0;
    int total=0;

    public adminmodelclass(String id, String pass, String name,String phone, String notice, int breakfast, int lunch, int dinner, int TotalMonthlyBill, int total) {
        this.id = id;
        this.pass = pass;
        this.name = name;
        this.phone=phone;
        this.notice=notice;
        this.breakfast=breakfast;
        this.lunch=lunch;
        this.dinner=dinner;
        this.TotalMonthlyBill=TotalMonthlyBill;
    }

    public adminmodelclass(){

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String pass) {
        this.phone = phone;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public int getBreakfast() {
        return breakfast;
    }

    public int getLunch() {
        return lunch;
    }

    public int getDinner() {
        return dinner;
    }

    public int getTotalMonthlyBill() {
        return TotalMonthlyBill;
    }

    public int getTotal() {
        return total;
    }
}
