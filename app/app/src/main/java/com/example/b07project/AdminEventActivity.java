package com.example.b07project;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AdminEventActivity extends AppCompatActivity implements View.OnClickListener{
    private Button delete_venue;
    private Button add_event;
    private ListView lstevent;
    private List<event> allevent;
    private List<String> string_allactivity;
    private String venue_name;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_event);

        venue_name = getIntent().getStringExtra("Venue name");
        allevent = new ArrayList<event>();
        string_allactivity = new ArrayList<String>();
        Do.DisplayEventsByVenue(venue_name, (ArrayList<event> events) -> {
            allevent = events;
        });

        for (event Event: allevent) {
            string_allactivity.add(Event.getname());
        }

        delete_venue = (Button) findViewById(R.id.delete_venue);
        delete_venue.setOnClickListener(this);

        add_event = (Button) findViewById(R.id.add_event);
        add_event.setOnClickListener(this);

        lstevent = (ListView) findViewById(R.id.lstevent);

        ArrayAdapter<String> venueAdapter = new ArrayAdapter<String>(AdminEventActivity.this, android.R.layout.simple_list_item_1, string_allactivity);
        lstevent.setAdapter(venueAdapter);

        lstevent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String event_name = allevent.get(i).getname();
                Intent intent = new Intent(AdminEventActivity.this, DeleteEventActivity.class);
                intent.putExtra("Event", event_name);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.add_event:
                Intent intent = new Intent(this, AddEventActivity.class);
                intent.putExtra("Venue name", venue_name);
                startActivity(intent);
                break;
            case R.id.delete_venue:
                Do.adaimDeleteVenue(venue_name, (Boolean success) -> {
                    if(success == true){ // Delete success
                        startActivity(new Intent(this, AdminActivity.class));
                    }
                    else{ //Delete fail
                        delete_venue.setError("Fail to delete venue");
                    }
                });
        }
    }
}
