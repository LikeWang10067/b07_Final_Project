package com.example.b07project;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
    private venue Venue;
    private TextView admin_event;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_event);

        //Do.cleaner((Boolean clean)->{});

        Venue = (venue) getIntent().getSerializableExtra("Venue");
        this.setTitle(Venue.getVenue_name() + " Events");

        allevent = new ArrayList<event>();
        string_allactivity = new ArrayList<String>();
        Do.DisplayEventsByVenue(Venue.getVenue_name(), (ArrayList<event> events) -> {
            allevent = events;

            int size = 0, new_size = 0;
            for(event Event : allevent){
                new_size = Event.getEventName().length();
                if(new_size > size){ size = new_size;}
            }
            if (size <= 18) {
                size = 18;
            }


            for (event Event: allevent) {
                string_allactivity.add(Event.getEventName() + AdminActivity.repeat_blank(" ", Event.getEventName().length(), size)
                        + "No. Players: " + Event.getReg_num() + "/" + Event.getNum_players());
            }



            admin_event = (TextView) findViewById(R.id.admin_event);
            if(allevent.size() == 0){
                admin_event.setText("No Activity Available at " + Venue.getVenue_name());
            }
            else{
                admin_event.setText("All activity Available at " + Venue.getVenue_name());
            }
            delete_venue = (Button) findViewById(R.id.delete_venue);
            delete_venue.setOnClickListener(this);

            add_event = (Button) findViewById(R.id.add_event);
            add_event.setOnClickListener(this);

            lstevent = (ListView) findViewById(R.id.lstevent);

            final Typeface mTypeface = Typeface.createFromAsset(getAssets(), "fonts/vero.ttf");
            ArrayAdapter<String> venueAdapter = new ArrayAdapter<String>(AdminEventActivity.this, android.R.layout.simple_list_item_1, string_allactivity) {
                @NonNull
                @Override
                public View getView (int position, View convertView, ViewGroup parent){
                    TextView item = (TextView) super.getView(position,convertView,parent);

                    item.setTypeface(mTypeface);
                    item.setTextSize(14);
                    return item;
                }
            };
            lstevent.setAdapter(venueAdapter);

            lstevent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(AdminEventActivity.this, DeleteEventActivity.class);
                    intent.putExtra("Event", allevent.get(i));
                    startActivity(intent);
                }
            });
        });

    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.add_event:
                Intent intent = new Intent(this, AddEventActivity.class);
                intent.putExtra("Venue", Venue);
                startActivity(intent);
                break;
            case R.id.delete_venue:
                Do.adaimDeleteVenue(Venue, (Boolean success) -> {
                    if(success == true){ // Delete success
                        startActivity(new Intent(this, AdminActivity.class));
                    }
                    else{ //Delete fail
//                        delete_venue.setError("Fail to delete venue");
                    }
                });
        }
    }
}
