package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
    private user User;
    private Button btnaddvenue, btnLogOut;
    //    private Button btndeletevenue;
    private ListView lstvenue;
    private List<venue> allvenue;
    private List<String> string_allvenue;
    private EditText filter_text;
    private Button btnfilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        allvenue = new ArrayList<venue>();
        string_allvenue = new ArrayList<String>();
        Do.DisplayVenues((ArrayList<venue> venue_list) -> {
            allvenue = venue_list;

            for (venue Venue : allvenue) {
                string_allvenue.add(Venue.getVenue_name());
            }

            btnaddvenue = (Button) findViewById(R.id.btnaddvenue);
            btnaddvenue.setOnClickListener(this);

            btnLogOut = (Button) findViewById(R.id.btnLogOut);
            btnLogOut.setOnClickListener(this);

            filter_text = (EditText) findViewById(R.id.filter_text);

            btnfilter = (Button) findViewById(R.id.btnfilter);
            btnfilter.setOnClickListener(this);

            lstvenue = (ListView) findViewById(R.id.lstvenue_a);

            ArrayAdapter<String> venueAdapter = new ArrayAdapter<>(AdminActivity.this, android.R.layout.simple_list_item_1, string_allvenue);
            lstvenue.setAdapter(venueAdapter);

            lstvenue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String venue_name = allvenue.get(i).getVenue_name();
                    Intent intent = new Intent(AdminActivity.this, AdminEventActivity.class);
                    intent.putExtra("Venue", allvenue.get(i));
//                    intent.putExtra("user", User);
                    startActivity(intent);
                }
            });













        });

//        for (venue Venue : allvenue) {
//            string_allvenue.add(Venue.getVenue_name());
//        }
//
//        this.User = (user) getIntent().getSerializableExtra("user");
//
//        btnaddvenue = (Button) findViewById(R.id.btnaddvenue);
//        btnaddvenue.setOnClickListener(this);
//
////        btndeletevenue = (Button) findViewById(R.id.btndeletevenue);
////        btndeletevenue.setOnClickListener(this);
//
//        btnLogOut = (Button) findViewById(R.id.btnLogOut);
//        btnLogOut.setOnClickListener(this);
//
//        filter_text = (EditText) findViewById(R.id.filter_text);
//
//        btnfilter = (Button) findViewById(R.id.btnfilter);
//        btnfilter.setOnClickListener(this);
//
//        lstvenue = (ListView) findViewById(R.id.lstvenue_a);
//
//        ArrayAdapter<String> venueAdapter = new ArrayAdapter<>(AdminActivity.this, android.R.layout.simple_list_item_1, string_allvenue);
//        lstvenue.setAdapter(venueAdapter);
//
//        lstvenue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String venue_name = allvenue.get(i).getVenue_name();
//                Intent intent = new Intent(AdminActivity.this, AllEventActivity.class);
//                intent.putExtra("Venue name", venue_name);
//                intent.putExtra("user", User);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnaddvenue:
                startActivity(new Intent(this, AddVenueActivity.class));
                break;
            case R.id.btnLogOut:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btnfilter:
                String filter_text = this.filter_text.toString();
                Do.filterVenue(filter_text, (ArrayList<venue> venues) ->{
                    string_allvenue.clear();
                    for(venue Venue: venues){
                        string_allvenue.add(Venue.getVenue_name());
                    }
                    ArrayAdapter<String> venueAdapter = new ArrayAdapter<>(AdminActivity.this, android.R.layout.simple_list_item_1, string_allvenue);
                    lstvenue.setAdapter(venueAdapter);
                });
                break;
        }
    }
}
