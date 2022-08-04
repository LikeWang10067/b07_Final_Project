package com.example.myfirstapp;

import java.io.Serializable;
import java.util.ArrayList;

public class venue implements Serializable {
//    private static int total_venueids;
    private String venue_name;
    private ArrayList<Integer> eventids;
//    private int id;
    public venue(){}
    public venue(String name){
        venue_name=name;
//        id = total_venueids;
//        total_venueids++;
        eventids=null;
    }
    public void setVenue_name(String n){
        venue_name=n;
    }
    public void setEventids(ArrayList<Integer> i){
        eventids=i;
    }
//    public int getId(){
//        return id;
//    }
    public ArrayList<Integer> getEventids(){
        return eventids;
    }

    public String getVenue_name() {
        return venue_name;
    }
}
