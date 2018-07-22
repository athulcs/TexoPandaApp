package com.texopanda.texopandaapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class User {

    private String username;
    private String email;
    private String phone;
    private List<Boolean> events=new ArrayList<Boolean>(Arrays.asList(new Boolean[4]));;

    public User(){

    }

    public User(String x,String y,String z){
        this.username =x;
        this.email = y;
        this.phone =z;
        Collections.fill(events, Boolean.FALSE);
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

    public void setEvents() {Collections.fill(events,Boolean.FALSE);}

    public List<Boolean> getEvents() { return events; }
}
