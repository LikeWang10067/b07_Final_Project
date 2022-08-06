package com.example.b07project;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class event implements Comparable<event>, Serializable {
//    private static int total_ids;

    private int num_players;
    private int reg_num;
    private String venue;
    private String eventName;
    private LocalDateTime start;
    private LocalDateTime end;
    //    private int id;
    private ArrayList<String> usernames;


    public event() {
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public event(int num_player, String v, String start, String end, String eventName) {
        num_players = num_player;
        venue = v;
        this.start = LocalDateTime.parse(start);
        this.end = LocalDateTime.parse(end);
        this.eventName = eventName;
        reg_num = 0;
    }


    public void setUsernames(ArrayList<String> id) {
        usernames = id;
    }

    public void setNum_players(int num_players) {
        this.num_players = num_players;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setEnd(String end) {
        this.end = this.end = LocalDateTime.parse(end);
        ;
    }

    public void setReg_num(int reg_num) {
        this.reg_num = reg_num;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setStart(String start) {
        this.start = LocalDateTime.parse(start);
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

//    public int getId(){
//        return id;
//    }


    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getNum_players() {
        return num_players;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public int getReg_num() {
        return reg_num;
    }

    public String getVenue() {
        return venue;
    }

    public ArrayList<String> getUsernamess() {
        return usernames;
    }

    @Override
    public int hashCode() {
        return num_players + venue.hashCode() + start.hashCode() + end.hashCode();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int compareTo(event o) {
        return this.start.compareTo(o.start);
//        if(this.start>o.start){
//            return 1;
//        }
//        else if (this.start==o.start)
//            return 0;
//        return -1;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean checkOverlap(event b) {
        return !(this.end.compareTo(b.start) > 0 || b.end.compareTo(this.start) > 0);
    }
}