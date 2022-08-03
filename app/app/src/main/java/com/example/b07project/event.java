package com.example.b07project;

import java.util.ArrayList;

public class event implements Comparable<event>{
//    private static int total_ids;

    private int num_players;
    private int reg_num;
    private String venue;
    private int start;
    private int end;
    //    private int id;
    private ArrayList<String> usernames;


    public event(){}
    public event(int num_player,String v,int start,int end){
        num_players=num_player;
        venue=v;
        this.start=start;
        this.end = end;
        reg_num=0;
//        id=total_ids;
//        total_ids++;
    }

    public void setUsernames(ArrayList<String> id){
        usernames=id;
    }

    public void setNum_players(int num_players) {
        this.num_players = num_players;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void setReg_num(int reg_num) {
        this.reg_num = reg_num;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

//    public int getId(){
//        return id;
//    }

    public int getNum_players() {
        return num_players;
    }

    public int getStart(){
        return start;
    }

    public int getEnd(){
        return end;
    }
    public int getReg_num(){
        return reg_num;
    }

    public String getVenue() {
        return venue;
    }
    public ArrayList<String> getUsernamess(){
        return usernames;
    }
    @Override
    public int hashCode(){
        return num_players+venue.hashCode()+start+end;
    }

    @Override
    public int compareTo(event o) {
        if(this.start>o.start){
            return 1;
        }
        else if (this.start==o.start)
            return 0;
        return -1;
    }
}
