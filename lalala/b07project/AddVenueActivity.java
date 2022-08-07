package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddVenueActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText venue_name;
    private Button btnadd_venue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_venue);

        venue_name = (EditText) findViewById(R.id.venue_name);

        btnadd_venue = (Button) findViewById(R.id.btnaddvenue_true);
        btnadd_venue.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnaddvenue:
                String string_venue_name = venue_name.getText().toString();
                Do.admainAddVenue(string_venue_name, (venue Venue)->{
                    if(Venue == null){
                        venue_name.setError("Venue already exist");
                    }
                    else{
                        startActivity(new Intent(this, AdminActivity.class));
                    }
                });
                break;
        }
    }
}
