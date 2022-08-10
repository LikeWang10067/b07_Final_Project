package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class DisplayMessageActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
        //Writing to a realtime database
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference(); //update URL!!!
        ArrayList<String> x = new ArrayList<String>();
        x.add("Runyu");
        x.add("Caleb");
        event a1 = new event(13,"UTSC","2015-02-20T06:30:00","2015-02-20T06:40:00");
        event b1 = new event(18,"UTSG","2016-02-20T06:30:00","2021-02-20T06:30:00");
        database_operation.addevent(a1);
        b1.setUsernames(x);
        database_operation.addevent(b1);
        ArrayList<Integer> x1 = new ArrayList<Integer>();
        x1.add(a1.hashCode());
        x1.add(b1.hashCode());
        user a = new user("Runyu",1,false);
        a.setList_events(x1);
        database_operation.adduser(a);
        user d1 = new user("Runyu",200,true);
        ArrayList<Integer> au = d1.getList_events();
        if(au==null)
            au=new ArrayList<Integer>();
        au.add(1000);
        for(int az:au){
            System.out.println(az);
        }
        d1.setList_events(au);
        database_operation.adduser(d1);
        user b = new user("Caleb",2,true);
        database_operation.adduser(b);
        event c = new event(13,"UTSC","2015-02-20T07:30:00","2015-02-20T08:30:00");
        c.setUsernames(x);
        database_operation.addevent(c);////
        int re=c.getReg_num();
        re++;
        c.setReg_num(re);
        database_operation.addevent(c);
        ArrayList<String> ax = c.getUsernamess();
        if(ax==null){
            ax=new ArrayList<String>();
        }
        ax.add("bob");
        c.setUsernames(ax);
        database_operation.addevent(c);
        event d = new event(18,"UTSG","2021-02-20T06:30:00","2022-02-20T06:30:00");
        database_operation.addevent(d);
        venue e = new venue("Las Vagus");
        e.setEventids(x1);
        database_operation.addvenue(e);
        venue f = new venue("Beijing");
        database_operation.addvenue(f);
        int xz =1;
        LocalDateTime first = LocalDateTime.parse("2015-02-20T06:30:00");
        LocalDateTime first1 = LocalDateTime.parse("2015-02-20T06:30:01");
        System.out.println(first.getDayOfMonth());
        System.out.println(first.getDayOfWeek());
        System.out.println(first.getHour());
        System.out.println(first.getMinute());
        System.out.println(first.compareTo(first1));
        System.out.println(first.hashCode()==first1.hashCode());




    }
}