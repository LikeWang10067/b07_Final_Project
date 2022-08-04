package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
    private user User;
    private Button btnaddvenue, btndeletevenue, btnLogOut;
    private ListView lstvenue;
    private List<venue> allvenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        allvenue = new ArrayList<venue>();
        database_operation.DisplayVenues((ArrayList<venue> venue_list) ->{
            allvenue = venue_list;
        });
        this.User = (user) getIntent().getSerializableExtra("user");

        btnaddvenue = (Button) findViewById(R.id.btnaddvenue);
        btnaddvenue.setOnClickListener(this);

        btndeletevenue = (Button) findViewById(R.id.btndeletevenue);
        btndeletevenue.setOnClickListener(this);

        btnLogOut = (Button) findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(this);

        lstvenue = (ListView) findViewById(R.id.lstvenue);
        lstvenue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String venue_name = allvenue.get(i).getVenue_name();
                Intent intent = new Intent(AdminActivity.this, EventActivity.class);
                intent.putExtra("Venue name", venue_name);
                intent.putExtra("user", User);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnaddvenue:
                startActivity(new Intent(this, AddVenueActivity.class));
                break;
            case R.id.btndeletevenue:
                startActivity(new Intent(this, DeleteVenueActivity.class));
                break;
            case R.id.btnLogOut:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
