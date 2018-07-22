package com.texopanda.texopandaapp;

public class User {

    private String username;
    private String email;
    private String phone;
    //private String events[];
    public User(){

    }

    public User(String x,String y,String z){
        this.username =x;
        this.email = y;
        this.phone =z;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String text) {
        this.username = text;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email =email ;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String name) {
        this.phone = name;
    }


}