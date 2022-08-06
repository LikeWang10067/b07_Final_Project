package com.example.b07project;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DeleteEventActivity extends AppCompatActivity implements View.OnClickListener {
    private event Event;
    private Button delete_event;
    private TextView start, end, location, MaxPlayer, event_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_event);

        delete_event = (Button) findViewById(R.id.delete_event);
        delete_event.setOnClickListener(this);

        Event = (event) getIntent().getSerializableExtra("Event");

        start = (TextView) findViewById(R.id.start);
        start.setText(Event.getStart().toString());

        end = (TextView) findViewById(R.id.end);
        end.setText(Event.getEnd().toString());

        location = (TextView) findViewById(R.id.location);
        location.setText(Event.getVenue().toString());

        MaxPlayer = (TextView) findViewById(R.id.MaxPlayer);
        MaxPlayer.setText(Event.getNum_players());

        event_name = (TextView) findViewById(R.id.event_name);
        event_name.setText(Event.getEventName());

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.delete_venue:
                Do.adaminDeleteEvent(Event, (Boolean success) ->{
                    if(success == true){ // success delete
                        startActivity(new Intent(this, AdminActivity.class));
                    }
                    else{
                        delete_event.setError("Delete Event Fail");
                    }
                });
                break;
        }
    }
}
