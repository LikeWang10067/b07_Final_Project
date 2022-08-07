package com.example.b07project;

import java.io.Serializable;
import java.util.ArrayList;

public class user implements Serializable {
    //    private static int totalid;
    private ArrayList<Integer> list_events;
    private String Name;
    private int admin;
    private int password;

    //    private int id;
    public user(String n, int p, int admin) {
        Name = n;
        password = p;
        this.admin = admin;
//        id = totalid;
//        totalid++;
        list_events = null;
    }

    public user() {

    }

    public String get_name() {
        return this.Name;
    }

    public void set_name(String s) {
        this.Name = s;
    }

    public void set_admin(int b) {
        admin = b;
    }

    public int get_admin() {
        return admin;
    }

    public void setPassword(int n) {
        password = n;
    }

    public int getPassword() {
        return password;
    }

    //    public int getId(){
//        return id;
//    }
    public void setList_events(ArrayList<Integer> i) {
        list_events = i;
    }

    public ArrayList<Integer> getList_events() {
        return list_events;
    }

}
