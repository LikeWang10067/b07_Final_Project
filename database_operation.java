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
    public static void fgetusernull(String username, int password, Consumer<user> callback){
        callback.accept(null);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void fgetuser(String username, int password, Consumer<user> callback){
        user a=new user("xyz",12,false);
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
//                            adduser(new user(applicantname,password,false));
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

    public static void CheckIsAdmain(String applicantname, int password,Consumer<Boolean> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    //Boolean flag = false;
                    for(DataSnapshot d: task.getResult().getChildren()) {
                        user test = d.getValue(user.class);
                        if (applicantname.equals(test.get_name()) && password == test.getPassword()) {
//                            adduser(new user(applicantname,password,false));
                            callback.accept(test.get_admin());
                            //flag=true;
                            break;
                        }
                    }
//                    if(flag==false){
//                        callback.accept(null);
//                    }
//                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                }
            }

        });
    }




    public static void CheckSignUp(String applicantname,int password,Consumer<user> callback) {
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
                            callback.accept(null);
                            flag = false;
                            break;
                        }
                    }
                    if(flag){
                        user a = new user(applicantname,password,false);
                        adduser(a);
                        callback.accept(a);
                    }
//                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                }
            }

        });
    }


    public static void DisplayVenues(Consumer<ArrayList<venue>> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Venue");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    ArrayList<venue> s = new ArrayList<venue>();
                    for(DataSnapshot d: task.getResult().getChildren()) {
                        venue test = d.getValue(venue.class);
                        s.add(test);
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

    public static void DisplayEventsByUser(String username,Consumer<ArrayList<event>> callback) {
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
                        if(test.getUsernamess() != null) {
                            for (String name : test.getUsernamess()) {
                                if (name.equals(username)) {
                                    s.add(test);
                                }
                            }
                        }
                    }
                    Collections.sort(s);
                    callback.accept(s);
//                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                }
            }

        });
    }


    public static void CheckEventStatus(event a,Consumer<Boolean> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    int eventhashcode=a.hashCode();
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


    public static void GetEventInfo(event a, Consumer<event> callback) {
        int eventhashcode=a.hashCode();
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
                            callback.accept(test);
                            break;
                        }
                    }
                    callback.accept(null);

                }
            }

        });
    }

    public static  void  JoinEvent(String username, event target, Consumer<Boolean> callback){
        int eventhashcode=target.hashCode();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event");
        DatabaseReference refU = FirebaseDatabase.getInstance().getReference("User");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    for(DataSnapshot d: task.getResult().getChildren()) {
                        event test = d.getValue(event.class);
                        if(eventhashcode==test.hashCode() && test.getReg_num() < test.getNum_players()) {
                            int temp = test.getReg_num();
                            temp ++;
                            test.setReg_num(temp);
                            ArrayList<String> usersInEvent = test.getUsernamess();
                            if(usersInEvent == null){
                                usersInEvent = new ArrayList<String>();
                            }
                            usersInEvent.add(username);
                            test.setUsernames(usersInEvent);
                            addevent(test);

                            refU.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @RequiresApi(api = Build.VERSION_CODES.N)
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    if (!task.isSuccessful()) {
                                        Log.e("firebase", "Error getting data", task.getException());
                                    } else {
                                        for(DataSnapshot d1: task.getResult().getChildren()) {
                                                user test1 = d1.getValue(user.class);
                                                if(test1.get_name().equals(username)){
                                                    ArrayList<Integer> events=test1.getList_events();
                                                    if(events == null){
                                                        events = new ArrayList<Integer>();
                                                    }
                                                    events.add(eventhashcode);
                                                    test1.setList_events(events);
                                                    adduser(test1);
                                                    callback.accept(true);
                                                }
                                        }
                                        callback.accept(false);

                                    }
                                }

                            });
                        }
                    }
                    callback.accept(false);

                }
            }

        });

    }

    public static void admainAddVenue(String venueName, Consumer<venue> callback){
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Venue");
            ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    for(DataSnapshot d: task.getResult().getChildren()) {
                        venue test = d.getValue(venue.class);
                        if(venueName.equals(test.getVenue_name())){
                            callback.accept(null);
                        }
                    }
                    venue newVenue = new venue(venueName);
                    addvenue(newVenue);
                    callback.accept(newVenue);
                }
            }

        });
    }

    public static void admainAddEvent(event newEvent,Consumer<Integer> callback){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Venue");
        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Event");

        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    for(DataSnapshot d: task.getResult().getChildren()) {
                        venue test = d.getValue(venue.class);
                        if(newEvent.getVenue().equals(test.getVenue_name())){

                            ArrayList<Integer> newList= test.getEventids();
                            if(newList == null) {
                                newList = new ArrayList<Integer>();
                            }else{
                                for(int one:newList){
                                    if(one == newEvent.hashCode()){
                                        callback.accept(1);
                                    }
                                }
                            }
                            newList.add(newList.hashCode());
                            test.setEventids(newList);
                            addvenue(test);
                            ref1.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @RequiresApi(api = Build.VERSION_CODES.N)
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    if (!task.isSuccessful()) {
                                        Log.e("firebase", "Error getting data", task.getException());
                                    } else {
                                            addevent(newEvent);
                                            callback.accept(2);




                                    }
                                }

                            });

                        }
                    }

                }
                callback.accept(0);
            }

        });


    }



//    public static void GetEventVenue(int eventhashcode, Consumer<String> callback) {
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event");
//        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (!task.isSuccessful()) {
//                    Log.e("firebase", "Error getting data", task.getException());
//                } else {
//                    for(DataSnapshot d: task.getResult().getChildren()) {
//                        event test = d.getValue(event.class);
//                        if(eventhashcode==test.hashCode()) {
//                            callback.accept(test.getVenue());
//                        }
//                    }
//                    callback.accept(null);
//
//                }
//            }
//
//        });
//    }




}
