package com.example.b07project;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
    private TextView Customer_venue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        allvenue = new ArrayList<venue>();
        //Do.cleaner((Boolean clean)->{});

        string_allvenue = new ArrayList<String>();
        Do.DisplayVenues((ArrayList<venue> venue_list) -> {
            allvenue = venue_list;

            int size = 0, new_size = 0;
            for(venue Venue : allvenue){
                new_size = Venue.getVenue_name().length();
                if(new_size > size){ size = new_size;}
            }
            if (size <= 18) {
                size = 18;
            }

            for (venue Venue : allvenue) {
                if (Venue.getEventids() != null) {
                    string_allvenue.add(Venue.getVenue_name() + AdminActivity.repeat_blank(" ", Venue.getVenue_name().length(), size)
                            + "Future Events: " + Venue.getEventids().size());
                }
                else{
                    string_allvenue.add(Venue.getVenue_name() + AdminActivity.repeat_blank(" ", Venue.getVenue_name().length(), size)
                            + "Future Events: 0");
                }

            }
            Customer_venue = (TextView) findViewById(R.id.Customer_venue);
            if(allvenue.size() == 0){
                Customer_venue.setText("No Venue Available");
            }
            else{
                Customer_venue.setText("Venue Around the World!");
            }
            this.User = (user) getIntent().getSerializableExtra("user");
            this.setTitle("Welcome " + this.User.get_name() + " !");

            btnLogOut = (Button) findViewById(R.id.btnLogOut_u);
            btnLogOut.setOnClickListener(this);

        btnListJoinActivity = (Button) findViewById(R.id.btnListJoinActivity);
        btnListJoinActivity.setOnClickListener(this);

            lstvenue = (ListView) findViewById(R.id.lstvenue_u);

            final Typeface mTypeface = Typeface.createFromAsset(getAssets(), "fonts/vero.ttf");

            ArrayAdapter<String> venueAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, string_allvenue){
                @NonNull
                @Override
                public View getView (int position, View convertView, ViewGroup parent){
                    TextView item = (TextView) super.getView(position,convertView,parent);

                    item.setTypeface(mTypeface);
                    item.setTextSize(14);
                    return item;
                }
            };
            lstvenue.setAdapter(venueAdapter);

            lstvenue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String venue_name = allvenue.get(i).getVenue_name();
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
            case R.id.btnLogOut_u:
                Log.d("Logout", "Logout");


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
