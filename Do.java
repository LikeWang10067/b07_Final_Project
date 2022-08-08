package com.example.b07project;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Do {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean checkDateSequence(String start, String end) {
        LocalDateTime first = LocalDateTime.parse(start);
        LocalDateTime second = LocalDateTime.parse(end);
        return first.compareTo(second) < 0;
    }

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
    public static void fgetusernull(String username, int password, Consumer<user> callback) {
        callback.accept(null);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void fgetuser(String username, String password, Consumer<user> callback) {
        user a = new user("xyz", "12", 0);
        callback.accept(a);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void fgeteventnull(event a, Consumer<event> callback) {
        callback.accept(null);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void fgetevent(event a, Consumer<event> callback) {
        callback.accept(a);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void fgetvenuenull(venue a, Consumer<venue> callback) {
        callback.accept(null);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void fgetvenue(venue a, Consumer<venue> callback) {
        callback.accept(a);
    }

//    public static void CheckLogIn(String applicantname, int password, Consumer<user> callback) {
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
//        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (!task.isSuccessful()) {
//                    Log.e("firebase", "Error getting data", task.getException());
//                } else {
//                    Boolean flag = false;
//                    for (DataSnapshot d : task.getResult().getChildren()) {
//                        user test = d.getValue(user.class);
//
//                        if (applicantname.equals(test.get_name()) && password == test.getPassword()) {
////                            adduser(new user(applicantname,password,false));
//                            //test.setIs_admin(false);
////                            Log.d("firebase",String.valueOf(test.get_admin()));
//                            callback.accept(test);
//                            flag = true;
//                            break;
//                        }
//                    }
//                    if (flag == false) {
//                        callback.accept(null);
//                    }
////                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
//
//                }
//            }
//
//        });
//    }

    public static void CheckLogIn(String applicantname, String password,Consumer<user> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
        ref.child(applicantname).addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user user1 = snapshot.getValue(user.class);
                if(user1!=null){
                    if(user1.getPassword().equals(password)){
                        callback.accept(user1);
                        return;
                    }
                }

//                Log.d("wudi", String.valueOf(user1.get_admin()));
                callback.accept(null);
                return;


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }








//    ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//        @RequiresApi(api = Build.VERSION_CODES.N)
//        @Override
//        public void onComplete(@NonNull Task<DataSnapshot> task) {
//            if (!task.isSuccessful()) {
//                Log.e("firebase", "Error getting data", task.getException());
//            } else {
//                ref.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (DataSnapshot d : snapshot.getChildren()) {
//                            user test = d.getValue(user.class);
//                            if (applicantname.equals(test.get_name()) && password == test.getPassword()) {
//                                callback.accept(test);
//                                break;
//                            }
//                        }
//                        callback.accept(null);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
////                    Boolean flag = false;
////                    for(DataSnapshot d: task.getResult().getChildren()) {
////                        user test = d.getValue(user.class);
////                        if (applicantname.equals(test.get_name()) && password == test.getPassword()) {
//////                            adduser(new user(applicantname,password,false));
////                            callback.accept(test);
////                            flag=true;
////                            break;
////                        }
////                    }
////                    if(flag==false){
////                        callback.accept(null)
//            }
//        }
//    });


    public static void CheckIsAdmain(String applicantname, String password, Consumer<Integer> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    //Boolean flag = false;
                    for (DataSnapshot d : task.getResult().getChildren()) {
                        user test = d.getValue(user.class);
                        if (applicantname.equals(test.get_name()) && password.equals(test.getPassword())) {
//                            adduser(new user(applicantname,password,false));
                            callback.accept(test.get_admin());
                            //flag=true;
                            return;
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


    public static void CheckSignUp(String applicantname, String password, Consumer<user> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    Boolean flag = true;
                    for (DataSnapshot d : task.getResult().getChildren()) {
                        user test = d.getValue(user.class);
                        if (applicantname.equals(test.get_name())) {
                            callback.accept(null);
                            flag = false;
                            return;
                        }
                    }
                    if (flag) {
                        user a = new user(applicantname, password, 0);
                        adduser(a);
                        callback.accept(a);
                        return;
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
                    for (DataSnapshot d : task.getResult().getChildren()) {
                        venue test = d.getValue(venue.class);
                        s.add(test);
                    }
                    callback.accept(s);
//                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    return;
                }
            }

        });
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void DisplayEventsByVenue(String venuename, Consumer<ArrayList<event>> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event");
        LocalDateTime t = LocalDateTime.now();

        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (!task.isSuccessful()) {
                    Log.e("firebase!!!!!!!!!!", "Error getting data", task.getException());
                } else {
                    ArrayList<event> s = new ArrayList<event>();
                    for (DataSnapshot d : task.getResult().getChildren()) {
                        event test = (event) d.getValue(event.class);
                        if (venuename.equals(test.getVenue())) {
                            //s.add(test);
                            if (t.compareTo(LocalDateTime.parse(test.getstart())) < 0) {
                                s.add(test);
                            }
                        }
                    }

                    Collections.sort(s);

                    callback.accept(s);
//                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    return;
                }
            }

        });
    }

    public static void DisplayEventsByUser(String username, Consumer<ArrayList<event>> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    ArrayList<event> s = new ArrayList<event>();
                    for (DataSnapshot d : task.getResult().getChildren()) {
                        event test = d.getValue(event.class);
                        if (test.getusernames() != null) {
                            for (String name : test.getusernames()) {
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


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void CheckEventStatus(event a, Consumer<Boolean> callback) {
        LocalDateTime t = LocalDateTime.now();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    int eventhashcode = a.hashCode();
                    Boolean flag = false;
                    for (DataSnapshot d : task.getResult().getChildren()) {
                        event test = d.getValue(event.class);
                        if (eventhashcode == test.hashCode() && test.getReg_num() < test.getNum_players() && t.compareTo(LocalDateTime.parse(test.getstart())) < 0) {
                            flag = true;
                        }
                    }
                    callback.accept(flag);

                }
            }

        });
    }


    public static void GetEventInfo(event a, Consumer<event> callback) {
        int eventhashcode = a.hashCode();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    for (DataSnapshot d : task.getResult().getChildren()) {
                        event test = d.getValue(event.class);
                        if (eventhashcode == test.hashCode()) {
                            callback.accept(test);
                            break;
                        }
                    }
                    callback.accept(null);

                }
            }

        });
    }

    public  static boolean helper_checkJoined(user user1,event target){

        if(user1.getList_events() == null){
            return false;
        }
        return user1.getList_events().contains(target.hashCode());


    }
    public static void JoinEvent(user user1, event target, Consumer<Boolean> callback) {
        int eventhashcode = target.hashCode();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event");
        DatabaseReference refU = FirebaseDatabase.getInstance().getReference("User");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
//            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    String username = user1.get_name();
                    boolean flag = helper_checkJoined(user1,target);
                    if(flag == true){
                        callback.accept(false);
                        return;
                    }
                    for (DataSnapshot d : task.getResult().getChildren()) {
                        event test = d.getValue(event.class);
                        if (eventhashcode == test.hashCode() && test.getReg_num() < test.getNum_players()) {
                            int temp = test.getReg_num();
                            temp++;
                            test.setReg_num(temp);
                            ArrayList<String> usersInEvent = test.getusernames();
                            if (usersInEvent == null) {
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
                                        for (DataSnapshot d1 : task.getResult().getChildren()) {
                                            user test1 = d1.getValue(user.class);
                                            if (test1.get_name().equals(username)) {
                                                ArrayList<Integer> events = test1.getList_events();
                                                if (events == null) {
                                                    events = new ArrayList<Integer>();
                                                }
                                                events.add(eventhashcode);
                                                test1.setList_events(events);
                                                adduser(test1);
                                                callback.accept(true);
                                                return;
                                            }
                                        }
                                        callback.accept(false);
                                        return;

                                    }
                                }

                            });
                        }
                    }
                    callback.accept(false);
                    return;

                }
            }

        });

    }
    public static void unjoinEvent(String username, event target, Consumer<Integer> callback) {
        int eventhashcode = target.hashCode();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event");
        DatabaseReference refU = FirebaseDatabase.getInstance().getReference("User");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
//            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    for (DataSnapshot d : task.getResult().getChildren()) {
                        event test = d.getValue(event.class);
                        if (eventhashcode == test.hashCode()) {
                            ArrayList<String> list = test.getusernames();
                            if (list == null || (!list.contains(username))) {
                                callback.accept(1);//the user does not exists in the event/event does not exist in user
                                return;
                            }
                            int index = list.indexOf(username);
                            list.remove(index);
                            int reg = test.getReg_num();
                            reg--;
                            test.setReg_num(reg);
                            test.setUsernames(list);
                            addevent(test);


                            refU.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DataSnapshot> task) {
                                    if (!task.isSuccessful()) {
                                        Log.e("firebase", "Error getting data", task.getException());
                                    } else {
                                        if (task.getResult().child(username).exists()) {

                                            //                                       for (DataSnapshot d : task.getResult().getChildren()) {
                                            user test = task.getResult().child(username).getValue(user.class);
                                            Log.d("$$$$$$$$",test.get_name());
                                            if (test.get_name().equals(username)) {
                                                ArrayList<Integer> userevents = test.getList_events();
                                                if (userevents == null || (!userevents.contains(target.hashCode()))) {
                                                    callback.accept(1);
                                                    return;
                                                }
                                                int index = userevents.indexOf(target.hashCode());
                                                userevents.remove(index);
                                                test.setList_events(userevents);
                                                adduser(test);
                                                callback.accept(2);//success
                                                return;
                                            }
                                            callback.accept(0);
                                            return;
                                        }
                                    }
                                }
                            });

                        }
                    }
                    callback.accept(3);//the event/user does not exists in list
                    return;
                }
            }
        });

    }

    public static void admainAddVenue(String venueName, Consumer<venue> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Venue");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    for (DataSnapshot d : task.getResult().getChildren()) {
                        venue test = d.getValue(venue.class);
                        if (venueName.equals(test.getVenue_name())) {
                            callback.accept(null);
                            return;
                        }
                    }
                    venue newVenue = new venue(venueName);
                    addvenue(newVenue);
                    callback.accept(newVenue);
                    return;
                }
            }

        });
    }

    public static void checkEventOverlap(event newEvent, Consumer<Boolean> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    for (DataSnapshot d : task.getResult().getChildren()) {
                        event test = d.getValue(event.class);
                        if (test.checkOverlap(newEvent) == true && test.getVenue().equals(newEvent.getVenue())) {
                            callback.accept(true);
                            return;
                        }
                    }
                    callback.accept(false);
                    return;
                }
            }
        });
    }

    public static void admainAddEvent(event newEvent, Consumer<Integer> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Venue");
        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("Event");

        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    for (DataSnapshot d : task.getResult().getChildren()) {
                        venue test = d.getValue(venue.class);
                        if (newEvent.getVenue().equals(test.getVenue_name())) {

                            ArrayList<Integer> newList = test.getEventids();
                            if (newList == null) {
                                newList = new ArrayList<Integer>();
                            } else {
                                for (int one : newList) {
                                    if (one == newEvent.hashCode()) {
                                        callback.accept(1);
                                        return;
                                    }
                                }
                            }
                            newList.add(newEvent.hashCode());
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
                                        return;
                                    }
                                }

                            });

                        }
                    }

                }
                callback.accept(0);
                return;
            }

        });


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void admainDeleteEventUserpart(event delEvent, Consumer<Boolean> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");
        ArrayList<String> usernames = delEvent.getusernames();
        if(usernames == null){
            callback.accept(true);
            return;
        }
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                int event_id = delEvent.hashCode();
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    for (String username : usernames) {
                        DataSnapshot d = task.getResult().child(username).child("list_events");
                        DatabaseReference r = d.getRef();

                        GenericTypeIndicator<ArrayList<Integer>> t = new GenericTypeIndicator<ArrayList<Integer>>() {};
                        ArrayList<Integer> k = new ArrayList<Integer>();
                        k = d.getValue(t);
                        if (k != null && k.contains(event_id)) {
                            r.child(String.valueOf(k.indexOf(event_id))).removeValue();
                        }
                    }
                    callback.accept(true);
                    return;
                }
            }

        });
    }

    public static void admainDeleteEventEventpart(event delEvent, Consumer<Boolean> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Event");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    for (DataSnapshot d : task.getResult().getChildren()) {
                        event test = d.getValue(event.class);
                        if (test.hashCode() == delEvent.hashCode()) {
                            ref.child(String.valueOf(test.hashCode())).removeValue();
                            callback.accept(true);
                            return;
                        }
                    }
                    callback.accept(false);
                    return;
                }
            }

        });

    }

    public static void admainDeleteEventVenuepart(event delEvent, Consumer<Boolean> callback) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Venue");
        String vanue_name = delEvent.getVenue();
        ref.child(vanue_name).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                int event_id = delEvent.hashCode();

                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    DataSnapshot d = task.getResult().child("eventids");
                    DatabaseReference r = d.getRef();

                    GenericTypeIndicator<ArrayList<Integer>> t = new GenericTypeIndicator<ArrayList<Integer>>() {
                    };
                    ArrayList<Integer> k = new ArrayList<Integer>();
                    k = d.getValue(t);
                    if (k != null && k.contains(event_id)) {
                        r.child(String.valueOf(k.indexOf(event_id))).removeValue();
                        callback.accept(true);
                        return;
                    }
                    callback.accept(false);
                    return;
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void adaminDeleteEvent(event delEvent, Consumer<Boolean> callback) {
        admainDeleteEventUserpart(delEvent, (Boolean get) -> {
            Boolean flag = true;
            if (get == true) {
                admainDeleteEventEventpart(delEvent, (Boolean get1) -> {
                    if (get1 == true) {
                        admainDeleteEventVenuepart(delEvent, (Boolean get2) -> {
                            if (get2 == true) {
                                callback.accept(true);
                                return;
                            }
                            callback.accept(false);
                            return;
                        });
                    }
                    callback.accept(false);
                    return;
                });
            }
            callback.accept(false);
            return;
        });
    }
    public static void adaimDeleteVenue(venue delVenue, Consumer<Boolean> callback){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Venue");


        ref.child(delVenue.getVenue_name()).addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                venue test = snapshot.getValue(venue.class);
                if(test == null){
                    callback.accept(false);
                    return;
                }
                ArrayList<Integer> events = test.getEventids();
                if(events == null){
                    ref.child(delVenue.getVenue_name()).removeValue();
                    callback.accept(true);
                    return;
                }
//                Log.d("+++++++++++++",String.valueOf(events.get(0)));
//                Log.d("+++++++++++++",String.valueOf(events.get(1)));
                //Log.d("+++++++++++++",String.valueOf(events.get(2)));
                ref.child(delVenue.getVenue_name()).removeValue();


                for(int eventId: events) {
                    DatabaseReference ref_E = FirebaseDatabase.getInstance().getReference("Event");
                    ref_E.child(String.valueOf(eventId)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            event a = snapshot.getValue(event.class);
                            adaminDeleteEvent(a, (Boolean get2) -> {
                                if (get2 == false){
                                    callback.accept(false);
                                    return;
                                }

                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
                callback.accept(true);
                return;



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

//    public static void adaimDeleteVenue(venue delVenue, Consumer<Boolean> callback) {
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Venue");
//        DatabaseReference refE = FirebaseDatabase.getInstance().getReference("Event");
//        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (!task.isSuccessful()) {
//                    Log.e("firebase", "Error getting data", task.getException());
//                } else {
//                    Boolean flag = true;
//                    for (DataSnapshot d : task.getResult().getChildren()) {
//                        venue test = d.getValue(venue.class);
//                        if (delVenue.getVenue_name().equals(test.getVenue_name())) {
//                            ArrayList<Integer> eventlist = test.getEventids();
//                            if (eventlist != null) {
//                                for (int eventid : eventlist) {
//                                    refE.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//                                        @RequiresApi(api = Build.VERSION_CODES.N)
//                                        @Override
//                                        public void onComplete(@NonNull Task<DataSnapshot> task) {
//                                            if (!task.isSuccessful()) {
//                                                Log.e("firebase", "Error getting data", task.getException());
//                                            } else {
//                                                for (DataSnapshot d : task.getResult().getChildren()) {
//                                                    event test = d.getValue(event.class);
//                                                    if (test.hashCode() == eventid) {
//                                                        adaminDeleteEvent(test, (Boolean get2) -> {
//                                                            if (get2 == false)
//                                                                callback.accept(false);
//                                                            return;
//                                                        });
//                                                    }
//                                                }
//
//                                            }
//                                        }
//
//                                    });
//                                }
//                            }
//                            ref.child(delVenue.getVenue_name()).removeValue();
//                            callback.accept(true);
//                            return;
//                        }
//
//                        callback.accept(false);
//                        return;
//                    }
//                }
//            }
//
//        });
//
//    }

    public static void filterVenue(String input, Consumer<ArrayList<venue>> callback) {
        String s = input.toLowerCase();
        String regex = ".*" + s + ".*";
        Log.d("regex", regex);
        Pattern style1 = Pattern.compile(regex);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Venue");
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    ArrayList<venue> res = new ArrayList<venue>();
                    for (DataSnapshot d : task.getResult().getChildren()) {
                        venue test = d.getValue(venue.class);
                        Matcher m1 = style1.matcher(test.getVenue_name().toLowerCase());
                        if (m1.matches()) {
                            res.add(test);
                        }

                    }
                    callback.accept(res);
                    return;

                }
            }

        });

    }

    public static boolean ifjoins(user u, event e){
        if(e.getusernames()==null){
            return false;
        }
        return e.getusernames().contains(u.get_name());
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
