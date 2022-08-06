package com.example.b07project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddEventActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView venue;
    private EditText MaxPeople, start, end, event_name;
    private Button add;
    private venue Venue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(this);

        Venue = (venue) getIntent().getSerializableExtra("Venue");

        this.venue = (TextView) findViewById(R.id.venue);
        this.venue.setText(Venue.getVenue_name());

        MaxPeople = (EditText) findViewById(R.id.MaxPeople);

        start = (EditText) findViewById(R.id.start);

        end = (EditText) findViewById(R.id.end);

        event_name = (EditText) findViewById(R.id.event_name);

    }

    @Override
    public void onClick(View view) {
        String string_max_people = this.MaxPeople.getText().toString();
        String string_start = this.start.getText().toString();
        String string_end = this.end.getText().toString();
        String string_event_name = this.event_name.getText().toString();
        switch(view.getId()){
            case R.id.add:
                
                break;
        }
    }
}
