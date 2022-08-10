package com.example.b07project;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener{
    private user User;
    private event Event;
    private Button Join;
    private TextView start, end, location, Max_people;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        Join = (Button) findViewById(R.id.Join);
        Join.setOnClickListener(this);

        User = (user) getIntent().getSerializableExtra("user");
        Event = (event) getIntent().getSerializableExtra("event");
        this.setTitle(Event.getEventName() + "'s Detail");

        start = (TextView) findViewById(R.id.start_j);
        start.setText(Event.getstart().replace("T", " "));

        end = (TextView) findViewById(R.id.end_j);
        end.setText(Event.getend().replace("T", " "));

        location = (TextView) findViewById(R.id.location_j);
        location.setText(Event.getVenue());

        Max_people = (TextView) findViewById(R.id.Max_people_j);
        Max_people.setText(Event.getReg_num() + "/" + Event.getNum_players());

        this.Join.requestFocus();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Join:
                Do.JoinEvent(User, Event, (Boolean join) ->{
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