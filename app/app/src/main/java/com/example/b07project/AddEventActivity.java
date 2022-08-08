package com.example.b07project;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.format.Time;
import android.util.Log;
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

        add = (Button) findViewById(R.id.add1);
        add.setOnClickListener(this);

        Venue = (venue) getIntent().getSerializableExtra("Venue");
        this.setTitle("Add Event for " + Venue.getVenue_name());



        MaxPeople = (EditText) findViewById(R.id.MaxPeople);

        start_date = (EditText) findViewById(R.id.start_date);
        start_date.setOnClickListener(this);


        end_date = (EditText) findViewById(R.id.end_date);
        end_date.setOnClickListener(this);


        start_time = (EditText) findViewById(R.id.start_time);
        start_time.setOnClickListener(this);


        end_time = (EditText) findViewById(R.id.end_time);
        end_time.setOnClickListener(this);


        event_name = (EditText) findViewById(R.id.event_name_add);

    }

    public String check_format(String to_check, String regex, int num){
        String[] split_check = to_check.split(regex);
        String string_return = "";
        if(split_check[0].length() == 1){
            string_return = string_return + "0" + split_check[0];
        }
        else{
            string_return = string_return + split_check[0];
        }
        for(int i = 1; i < num; i++){
            if(split_check[i].length() == 1){
                string_return = string_return + regex + "0" + split_check[i];
            }
            else{
                string_return = string_return + regex + split_check[i];
            }
        }
        return string_return;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        final Calendar calendar = Calendar.getInstance();
        int dhour = calendar.get(Calendar.HOUR_OF_DAY);
        int hminute = calendar.get(Calendar.MINUTE);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        switch(view.getId()){
            case R.id.add1:
                String string_max_people = this.MaxPeople.getText().toString();
                String string_event_name = this.event_name.getText().toString();
                String string_start_date = this.start_date.getText().toString();
                String string_start_time = this.start_time.getText().toString();
                String string_end_date = end_date.getText().toString();
                String string_end_time = end_time.getText().toString();
                String start = check_format(string_start_date, "-", 3) + "T" + check_format(string_start_time, ":", 2) + ":00";
                String end = check_format(string_end_date, "-", 3) + "T" + check_format(string_end_time, ":", 2) + ":00";
                int num_player = Integer.valueOf(string_max_people);
                event Event = new event(num_player, Venue.getVenue_name(), start, end, string_event_name);
                if(Do.checkDateSequence(start, end)){//normal case
                    Log.d("check", "true");
                    Do.checkEventOverlap(Event, (Boolean overlap) -> {
                        if(overlap == true){
                            Log.d("Overlap", String.valueOf(overlap));
                            this.event_name.setError("Event overlap with other event");
                        }
                        else{
                            Log.d("Overlap", String.valueOf(overlap));
                            Do.admainAddEvent(Event, (Integer success) -> {
                                if(success == 0){
                                    Log.d("success", "0");
                                    this.event_name.setError("Venue does not exist");
                                }
                                else if(success == 1){
                                    Log.d("success", "1");
                                    this.event_name.setError("Event already exist");
                                }
                                else{
                                    Log.d("success", "2");
                                    startActivity(new Intent(this, AdminActivity.class));
                                }
                            });
                        }
                    });

                }
                else{ //end time go before start time
                    Log.d("check", "false");
                    event_name.setError("Start time start after end time");
                }

                break;
            case R.id.start_time:
                start_time_picker = new TimePickerDialog(AddEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        start_time.setText(hour + ":" + minute);
                    }
                }, dhour, hminute, true);
                start_time_picker.show();;


                break;
            case R.id.start_date:
                start_date_picker = new DatePickerDialog(AddEventActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        int modify_month = month+1;
                        start_date.setText(year+"-"+modify_month+"-"+day);
                    }
                }, year, month, day);
                start_date_picker.show();

                break;
            case R.id.end_date:
                end_date_picker = new DatePickerDialog(AddEventActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        int modify_month = month+1;
                        end_date.setText(year+"-"+modify_month+"-"+day);
                    }
                }, year, month, day);
                end_date_picker.show();

                break;
            case R.id.end_time:

                end_time_picker = new TimePickerDialog(AddEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        end_time.setText(hour + ":" + minute);
                    }
                }, dhour, hminute, true);
                end_time_picker.show();;


                break;
        }
    }
}
