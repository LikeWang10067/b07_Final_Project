package com.example.b07project;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
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
    private TextView all_event_view;

    ArrayList<String> tutorials = new ArrayList<String>();


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_event);


        //Do.cleaner((Boolean clean)->{});
        venue_name = getIntent().getStringExtra("Venue name");
        this.setTitle("Events at " + venue_name);
        allevent = new ArrayList<event>();
        string_allevent = new ArrayList<String>();
        Do.DisplayEventsByVenue(venue_name, (ArrayList<event> events) -> {
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
                string_allevent.add(Event.getEventName() + AdminActivity.repeat_blank(" ", Event.getEventName().length(), size)
                        + "No. Players: " + Event.getReg_num() + "/" + Event.getNum_players());
            }

            all_event_view = (TextView) findViewById(R.id.all_event_view);
            if(allevent.size() == 0){
                Log.d("Size", "0");
                all_event_view.setText("No Event Available at " + venue_name);
            }
            else{
                Log.d("Size", String.valueOf(allevent.size()));
                all_event_view.setText("All events at " + venue_name + " now");
            }
            User = (user) getIntent().getSerializableExtra("user");

            lstevent = (ListView) findViewById(R.id.venue_lstevent);

            final Typeface mTypeface = Typeface.createFromAsset(getAssets(), "fonts/vero.ttf");

            ArrayAdapter<String> venueAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, string_allevent){
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
                    if(Do.ifjoins(User, allevent.get(i)) == false){ // user have not join activity
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
