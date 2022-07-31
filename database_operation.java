package com.example.myfirstapp;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;

public class database_operation {
    public static void adduser(user a) {
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference(); //update URL!!!
        DatabaseReference refuser = ref.child("User").child(String.valueOf(a.get_name()));
        refuser.setValue(a);
    }

    public static boolean addevent(event a) {
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference(); //update URL!!!
        DatabaseReference refuser = ref.child("Event").child(String.valueOf(a.hashCode()));
        refuser.setValue(a);
        return true;
    }

    public static boolean addvenue(venue a) {
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference(); //update URL!!!
        DatabaseReference refuser = ref.child("Venue").child(String.valueOf(a.getVenue_name()));
        refuser.setValue(a);
        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void fgetusernull(user a, Consumer<user> callback){
        callback.accept(null);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void fgetuser(user a, Consumer<user> callback){
        callback.accept(a);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void fgeteventnull(event a, Consumer<event> callback){
        callback.accept(null);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void fgetevent(event a, Consumer<event> callback){
        callback.accept(a);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void fgetvenuenull(venue a, Consumer<venue> callback){
        callback.accept(null);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void fgetvenue(venue a, Consumer<venue> callback){
        callback.accept(a);
    }

    public static void CheckLogIn(String applicantname, int password,Consumer<user> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    Boolean flag = false;
                    for(DataSnapshot d: task.getResult().getChildren()) {
                        user test = d.getValue(user.class);
                        if (applicantname.equals(test.get_name()) && password == test.getPassword()) {
                            callback.accept(test);
                            flag=true;
                            break;
                        }
                    }
                    if(flag==false){
                        callback.accept(null);
                    }
//                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                }
            }

        });
    }


    public static void CheckSignUp(String applicantname,Consumer<Boolean> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    Boolean flag = true;
                    for(DataSnapshot d: task.getResult().getChildren()) {
                        user test = d.getValue(user.class);
                        if (applicantname.equals(test.get_name())) {
                            callback.accept(false);
                            flag = false;
                            break;
                        }
                    }
                    if(flag){
                        callback.accept(true);
                    }
//                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                }
            }

        });
    }


    public static void DisplayVenues(Consumer<ArrayList<String>> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Venue");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    ArrayList<String> s = new ArrayList<String>();
                    for(DataSnapshot d: task.getResult().getChildren()) {
                        venue test = d.getValue(venue.class);
                        s.add(test.getVenue_name());
                    }
                    callback.accept(s);
//                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                }
            }

        });
    }


    public static void DisplayEventsByVenue(String venuename,Consumer<ArrayList<event>> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    ArrayList<event> s = new ArrayList<event>();
                    for(DataSnapshot d: task.getResult().getChildren()) {
                        event test = d.getValue(event.class);
                        if(venuename.equals(test.getVenue())){
                            s.add(test);
                        }
                    }
                    Collections.sort(s);
                    callback.accept(s);
//                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                }
            }

        });
    }


    public static void CheckEventStatus(int eventhashcode,Consumer<Boolean> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    Boolean flag=false;
                    for(DataSnapshot d: task.getResult().getChildren()) {
                        event test = d.getValue(event.class);
                        if(eventhashcode==test.hashCode()&&test.getReg_num()<test.getNum_players()){
                            flag=true;
                        }
                    }
                    callback.accept(flag);

                }
            }

        });
    }


    public static void GetEventInfo(int eventhashcode, Consumer<ArrayList<Integer>> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    ArrayList<Integer> i=new ArrayList<Integer>();
                    for(DataSnapshot d: task.getResult().getChildren()) {
                        event test = d.getValue(event.class);
                        if(eventhashcode==test.hashCode()) {
                            i.add(test.getReg_num());
                            i.add(test.getNum_players());
                            i.add(test.getStart());
                            i.add(test.getEnd());
                            break;
                        }
                    }
                    callback.accept(i);

                }
            }

        });
    }


    public static void GetEventVenue(int eventhashcode, Consumer<String> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    for(DataSnapshot d: task.getResult().getChildren()) {
                        event test = d.getValue(event.class);
                        if(eventhashcode==test.hashCode()) {
                            callback.accept(test.getVenue());
                        }
                    }
                    callback.accept(null);

                }
            }

        });
    }




}
