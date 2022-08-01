package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayMessageActivity extends AppCompatActivity {

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
        event a1 = new event(13,"UTSC",13,14);
        event b1 = new event(18,"UTSG",4,12);
        ArrayList<Integer> x1 = new ArrayList<Integer>();
        x1.add(a1.hashCode());
        x1.add(b1.hashCode());
        user a = new user("Runyu",1,false);
        a.setList_events(x1);
        database_operation.adduser(a);
        user b = new user("Caleb",2,true);
        database_operation.adduser(b);
        event c = new event(13,"UTSC",13,14);
        c.setUsernames(x);
        database_operation.addevent(c);
        event d = new event(18,"UTSG",4,12);
        database_operation.addevent(d);
        venue e = new venue("Las Vagus");
        e.setEventids(x1);
        database_operation.addvenue(e);
        venue f = new venue("Beijing");
        database_operation.addvenue(f);




    }
}