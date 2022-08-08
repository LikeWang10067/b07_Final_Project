package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CancelActivity extends AppCompatActivity implements View.OnClickListener {
    private user User;
    private event Event;
    private Button Cancel;
    private TextView  start, end, location, Max_people;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);

        Do.cleaner((Boolean clean)->{});

        Cancel = (Button) findViewById(R.id.Cancel);
        Cancel.setOnClickListener(this);

        User = (user) getIntent().getSerializableExtra("user");
        Event = (event) getIntent().getSerializableExtra("event");
        this.setTitle(Event.getEventName() + "'s Detail");


        start = (TextView) findViewById(R.id.start);
        start.setText(Event.getstart().toString().replace("T", " "));

        end = (TextView) findViewById(R.id.end);
        end.setText(Event.getend().toString().replace("T", " "));

        location = (TextView) findViewById(R.id.location);
        location.setText(Event.getVenue());

        Max_people = (TextView) findViewById(R.id.Max_people);

        Max_people.setText(Event.getReg_num() + "/" + Event.getNum_players());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Cancel:
                Do.unjoinEvent(User.get_name(), Event, (Integer cancel) ->{
                    if(cancel == 0){
                        Cancel.setError("No such user");
                    }
                    else if (cancel == 1){
                        Cancel.setError("Target event not in join list");
                    }
                    else{
                        Intent intent = new Intent(CancelActivity.this, CustomerActivity.class);
                        intent.putExtra("user", User);
                        startActivity(intent);
                    }
                });
                break;
        }
    }
}
