package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener{
    private user User;
    private event Event;
    private Button Join;
    private TextView event_name, start, end, location, Max_people;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Join = (Button) findViewById(R.id.Join);
        Join.setOnClickListener(this);

        User = (user) getIntent().getSerializableExtra("user");
        Event = (event) getIntent().getSerializableExtra("event");

        event_name = (TextView) findViewById(R.id.event_name);
        event_name.setText(Event.getEventName());

        start = (TextView) findViewById(R.id.start);
        start.setText(Event.getstart().toString());

        end = (TextView) findViewById(R.id.end);
        end.setText(Event.getend().toString());

        location = (TextView) findViewById(R.id.location);
        location.setText(Event.getVenue());

        Max_people = (TextView) findViewById(R.id.Max_people);
        Max_people.setText(Event.getNum_players());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Join:
                Do.JoinEvent(User.get_name(), Event, (Boolean join) ->{
                    if(join == true){
                        Intent intent = new Intent(JoinActivity.this, CustomerActivity.class);
                        intent.putExtra("user", User);
                        startActivity(intent);
                    }
                    else{
                        Join.setError("User doesn't exist or event doesn't exist or event is already full");
                    }
                });
                break;
        }
    }


}
