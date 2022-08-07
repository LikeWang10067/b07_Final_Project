package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CustomerActivity extends AppCompatActivity implements View.OnClickListener {
    private user User;
    private Button btnLogOut, btnListJoinActivity;
    private ListView lstvenue;
    private List<venue> allvenue;
    private List<String> string_allvenue;



    String[] tutorials
            = { "Algorithms", "Data Structures",
            "Languages", "Interview Corner",
            "GATE", "ISRO CS",
            "UGC NET CS", "CS Subjects",
            "Web Technologies", "Algorithms", "Data Structures",
            "Languages", "Interview Corner",
            "GATE", "ISRO CS",
            "UGC NET CS", "CS Subjects",
            "Web Technologies" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        allvenue = new ArrayList<venue>();

        string_allvenue = new ArrayList<String>();
        Do.DisplayVenues((ArrayList<venue> venue_list) -> {
            allvenue = venue_list;

            for (venue Venue : allvenue) {
                string_allvenue.add(Venue.getVenue_name());
            }


            this.User = (user) getIntent().getSerializableExtra("user");

            btnLogOut = (Button) findViewById(R.id.btnLogOut_u);
            btnLogOut.setOnClickListener(this);

        btnListJoinActivity = (Button) findViewById(R.id.btnListJoinActivity);
        btnListJoinActivity.setOnClickListener(this);

            lstvenue = (ListView) findViewById(R.id.lstvenue_u);

            ArrayAdapter<String> venueAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, string_allvenue);
            lstvenue.setAdapter(venueAdapter);

            lstvenue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String venue_name = string_allvenue.get(i);
                    Intent intent = new Intent(CustomerActivity.this, AllEventActivity.class);
                    intent.putExtra("Venue name", venue_name);
                    intent.putExtra("user", User);
                    startActivity(intent);
                }
            });

        });

//
//
//        this.User = (user) getIntent().getSerializableExtra("user");
////        btnaddvenue = (Button) findViewById(R.id.btnaddvenue);
////        btnaddvenue.setOnClickListener(this);
////        btndeletevenue = (Button) findViewById(R.id.btndeletevenue);
////        btndeletevenue.setOnClickListener(this);
//
//        btnLogOut = (Button) findViewById(R.id.btnLogOut_u);
//        btnLogOut.setOnClickListener(this);
//
////        btnListJoinActivity = (Button) findViewById(R.id.btnListJoinActivity);
////        btnListJoinActivity.setOnClickListener(this);
//
//        lstvenue = (ListView) findViewById(R.id.lstvenue_u);
//
//        ArrayAdapter<String> venueAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, string_allvenue);
//        lstvenue.setAdapter(venueAdapter);
//
//        lstvenue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String venue_name = string_allvenue.get(i);
//                Intent intent = new Intent(CustomerActivity.this, AllEventActivity.class);
//                intent.putExtra("Venue name", venue_name);
//                intent.putExtra("user", User);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogOut:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btnListJoinActivity:
                Intent intent = new Intent(this, UserEventActivity.class);
                intent.putExtra("user", this.User);
                startActivity(intent);
                break;
        }
    }

}
