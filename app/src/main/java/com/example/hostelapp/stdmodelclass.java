package com.example.hostelapp;

public class stdmodelclass {

    private int breakfast,lunch,dinner,total=0;
    private String pass;

    public stdmodelclass(int breakfast,int lunch,int dinner,int total) {
        this.breakfast=breakfast;
        this.lunch=lunch;
        this.dinner=dinner;
        this.total=total;
    }

    public stdmodelclass() {

    }

    public void setBreakfast(int breakfast) {
        this.breakfast = breakfast;
    }

    public void setLunch(int lunch) {
        this.lunch = lunch;
    }

    public void setDinner(int dinner) {
        this.dinner = dinner;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }
}
