package com.example.myfirstapp;

import java.util.ArrayList;

public class user {
//    private static int totalid;
    private ArrayList<Integer> list_events;
    private String Name;
    private Boolean is_admin;
    private int password;
//    private int id;
    public user(String n, int p,boolean admin){
        Name=n;
        password=p;
        is_admin=admin;
//        id = totalid;
//        totalid++;
        list_events=null;
    }
    public  user(){

    }
    public String get_name(){
        return this.Name;
    }
    public void set_name(String s){
        this.Name=s;
    }
    public void setIs_admin(Boolean b){
        is_admin=b;
    }
    public boolean get_admin(){
        return is_admin;
    }
    public void setPassword(int n){
        password=n;
    }
    public int getPassword(){
        return password;
    }
//    public int getId(){
//        return id;
//    }
    public void setList_events(ArrayList<Integer> i){
        list_events=i;
    }
    public ArrayList<Integer> getList_events(){
        return list_events;
    }

}
