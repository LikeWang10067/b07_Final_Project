package com.example.b07project;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

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
//    private Button btnfilter;
    private TextView admin_textview;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

//        Do.cleaner((Boolean clean)->{});

        allvenue = new ArrayList<venue>();
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
                    string_allvenue.add(Venue.getVenue_name() + AdminActivity.repeat_blank(" ", Venue.getVenue_name().length(), size) + "Future Events: " + Venue.getEventids().size());
                }
                else{
                    string_allvenue.add(Venue.getVenue_name() + AdminActivity.repeat_blank(" ", Venue.getVenue_name().length(), size) + "Future Events: 0");
                }
            }

            admin_textview = (TextView) findViewById(R.id.admin_textview);
            if(allvenue.size() == 0){
                admin_textview.setText("No Venue Currently");
            }
            else{
                admin_textview.setText("Venue Around The World!");
            }
            btnaddvenue = (Button) findViewById(R.id.btnaddvenue);
            btnaddvenue.setOnClickListener(this);

            btnLogOut = (Button) findViewById(R.id.btnLogOut);
            btnLogOut.setOnClickListener(this);

            filter_text = (EditText) findViewById(R.id.filter_text);
            filter_text.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    String filter_text = AdminActivity.this.filter_text.getText().toString();
                    Log.d("filter_text", filter_text);
                    Do.filterVenue(filter_text, (ArrayList<venue> venues) -> {
                        Log.d("Size of venues", String.valueOf(venues.size()));
                        string_allvenue.clear();
                        int size = 0, new_size = 0;
                        for(venue Venue : allvenue){
                            new_size = Venue.getVenue_name().length();
                            if(new_size > size){ size = new_size;}
                        }
                        size++;

                        for (venue Venue : venues) {
                            if (Venue.getEventids() != null) {
                                Log.d("Venue size", String.valueOf(Venue.getEventids()));
                                string_allvenue.add(Venue.getVenue_name() + AdminActivity.repeat_blank(" ", Venue.getVenue_name().length(), size) + "Future Events: " + Venue.getEventids().size());
                            }
                            else{
                                string_allvenue.add(Venue.getVenue_name() + AdminActivity.repeat_blank(" ", Venue.getVenue_name().length(), size) + "Future Events: 0");
                            }
                        }

                        final Typeface mTypeface = Typeface.createFromAsset(getAssets(), "fonts/vero.ttf");
                        ArrayAdapter<String> venueAdapter = new ArrayAdapter<String>(AdminActivity.this, android.R.layout.simple_list_item_1, string_allvenue) {
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
                    });
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });


//            btnfilter = (Button) findViewById(R.id.btnfilter);
//            btnfilter.setOnClickListener(this);

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
//            case R.id.btnfilter:
//                String filter_text = this.filter_text.getText().toString();
//                Log.d("filter_text", filter_text);
//                Do.filterVenue(filter_text, (ArrayList<venue> venues) ->{
//                    Log.d("Size of venues", String.valueOf(venues.size()));
//                    string_allvenue.clear();
//                    for(venue Venue: venues){
//                        string_allvenue.add(Venue.getVenue_name());
//                    }
//                    ArrayAdapter<String> venueAdapter = new ArrayAdapter<>(AdminActivity.this, android.R.layout.simple_list_item_1, string_allvenue);
//                    lstvenue.setAdapter(venueAdapter);
//                });
//                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
        Do.cleaner((ArrayList<Integer> hash)->{
            Do.filterVenue(".*", (ArrayList<venue> venues) -> {
                Log.d("Size of venues", String.valueOf(venues.size()));
                string_allvenue.clear();
                int size = 0, new_size = 0;
                for(venue Venue : allvenue){
                    new_size = Venue.getVenue_name().length();
                    if(new_size > size){ size = new_size;}
                }
                if (size <= 18) {
                    size = 18;
                }


                for (venue Venue : venues) {
                    if (Venue.getEventids() != null) {
                        int event_size = Venue.getEventids().size();
                        for(int i: Venue.getEventids()){
                            if(hash.contains(i)){
                                event_size--;
                            }
                        }
                        Log.d("Venue size", String.valueOf(event_size));
                        string_allvenue.add(Venue.getVenue_name() + AdminActivity.repeat_blank(" ", Venue.getVenue_name().length(), size) + "Future Events: " + event_size);
                    }
                    else{
                        string_allvenue.add(Venue.getVenue_name() + AdminActivity.repeat_blank(" ", Venue.getVenue_name().length(), size) + "Future Events: 0");
                    }
                }
                final Typeface mTypeface = Typeface.createFromAsset(getAssets(), "fonts/vero.ttf");

                ArrayAdapter<String> venueAdapter = new ArrayAdapter<String>(AdminActivity.this, android.R.layout.simple_list_item_1, string_allvenue){
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
            });

        });
        }

    public static String repeat_blank(String delimiter, int name_size, int size){
        String return_s = "";
        for(int i = 0; i < size-name_size; i++){
            return_s += delimiter;
        }
        return return_s;
    }
}
