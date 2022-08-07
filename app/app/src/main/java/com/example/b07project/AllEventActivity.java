package com.example.b07project;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AllEventActivity extends AppCompatActivity {

    private ListView lstevent;
    private List<event> allevent;
    private List<String> string_allevent;
    private String venue_name;
    private user User;

    ArrayList<String> tutorials = new ArrayList<String>();


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_event);



        venue_name = getIntent().getStringExtra("Venue name");
        allevent = new ArrayList<event>();
        string_allevent = new ArrayList<String>();
        Do.DisplayEventsByVenue(venue_name, (ArrayList<event> events) -> {
            allevent = events;
            Log.d("lalalala", String.valueOf(allevent.size()));
            for (event Event: allevent) {
                string_allevent.add(Event.getEventName());
            }

            User = (user) getIntent().getSerializableExtra("user");

            lstevent = (ListView) findViewById(R.id.venue_lstevent);

            ArrayAdapter<String> venueAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, string_allevent);
            lstevent.setAdapter(venueAdapter);

            lstevent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if(Do.ifjoins(User, allevent.get(i)) == true){ // user have join activity
                        Intent intent = new Intent(AllEventActivity.this, JoinActivity.class);
                        intent.putExtra("event", allevent.get(i));
                        intent.putExtra("user", User);
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(AllEventActivity.this, CancelActivity.class);
                        intent.putExtra("event", allevent.get(i));
                        intent.putExtra("user", User);
                        startActivity(intent);

                    }
                }
            });

        });



//        lstevent = (ListView) findViewById(R.id.venue_lstevent);
//
//        ArrayAdapter<String> venueAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, string_allevent);
//        lstevent.setAdapter(venueAdapter);
//
//        lstevent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String event_name = string_allevent.get(i);
//                Intent intent = new Intent(AllEventActivity.this, UserEventActivity.class);
//                intent.putExtra("Event", event_name);
//                startActivity(intent);
//            }
//        });
    }


}
