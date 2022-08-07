package com.example.b07project;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.timepicker.MaterialTimePicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddEventActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView venue;
    private EditText MaxPeople, event_name;
    private EditText start_date, start_time;
    private EditText end_date, end_time;
    private DatePickerDialog start_date_picker, end_date_picker;
    private TimePickerDialog start_time_picker, end_time_picker;
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

        start_date = (EditText) findViewById(R.id.start_date);
        start_date.setInputType(InputType.TYPE_NULL);
        start_date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                start_date_picker = new DatePickerDialog(AddEventActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        start_date.setText(year+"-"+month+"-"+day);
                    }
                }, year, month, day);
                start_date_picker.show();
            }
        });

        end_date = (EditText) findViewById(R.id.end_date);
        end_date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                end_date_picker = new DatePickerDialog(AddEventActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        start_date.setText(year+"-"+month+"-"+day);
                    }
                }, year, month, day);
                end_date_picker.show();
            }
        });

        start_time = (EditText) findViewById(R.id.start_time);
        start_time.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int dhour = calendar.get(Calendar.HOUR_OF_DAY);
                int hminute = calendar.get(Calendar.MINUTE);

                start_time_picker = new TimePickerDialog(AddEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        start_time.setText(hour + ":" + minute);
                    }
                }, dhour, hminute, true);
                start_time_picker.show();;
            }
        });

        end_time = (EditText) findViewById(R.id.end_time);
        end_time.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int dhour = calendar.get(Calendar.HOUR_OF_DAY);
                int hminute = calendar.get(Calendar.MINUTE);

                end_time_picker = new TimePickerDialog(AddEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        start_time.setText(hour + ":" + minute);
                    }
                }, dhour, hminute, true);
                end_time_picker.show();;
            }
        });

        event_name = (EditText) findViewById(R.id.event_name_add);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.add:
                String string_max_people = this.MaxPeople.getText().toString();
//                String string_start = this.start.getText().toString();
//                String string_end = this.end.getText().toString();
                String string_event_name = this.event_name.getText().toString();
                String string_start_date = this.start_date.getText().toString();
                String string_start_time = this.start_time.getText().toString();
                String string_end_date = end_date.getText().toString();
                String string_end_time = end_time.getText().toString();
                String start = string_start_date + "T" + string_start_time + ":00";
                String end = string_end_date + "T" + string_end_time + ":00";
                int num_player = Integer.valueOf(string_max_people);
                event Event = new event(num_player, Venue.getVenue_name(), start, end, string_event_name);
                Do.checkEventOverlap(Event, (Boolean overlap) -> {
                    if(overlap == true){
                        add.setError("Event overlap with other event");
                    }
                    else{
                        Do.admainAddEvent(Event, (Integer success) -> {
                            if(success == 0){
                                add.setError("Venue does not exist");
                            }
                            else if(success == 1){
                                add.setError("Event already exist");
                            }
                            else{
                                startActivity(new Intent(this, AdminActivity.class));
                            }
                        });
                    }
                });

                break;
        }
    }
}