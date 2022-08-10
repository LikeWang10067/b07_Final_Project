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
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class UserEventActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView lstuserevent;
    private List<String> string_all_join_event;
    private List<event> all_join_event;
    private user User;
    private TextView join_activity;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_event);
        //Do.cleaner((Boolean clean)->{});

        User = (user) getIntent().getSerializableExtra("user");
        string_all_join_event = new ArrayList<String>();
        Do.DisplayEventsByUser(User.get_name(), (ArrayList<event> events) -> {
            all_join_event = events;

            int size = 0, new_size = 0;
            for(event Event : all_join_event){
                new_size = Event.getEventName().length();
                if(new_size > size){ size = new_size;}
            }
            if (size <= 18) {
                size = 18;
            }

            for(event Event: events){
                string_all_join_event.add(Event.getEventName() + AdminActivity.repeat_blank(" ", Event.getEventName().length(), size)
                        + "No. Players: " + Event.getReg_num() + "/" + Event.getNum_players());
            }

            join_activity = (TextView) findViewById(R.id.join_activity);
            if(all_join_event.size() == 0){
                join_activity.setText("You don't have any event now");
            }
            else{
                join_activity.setText("All Events that you have joined");
            }

            final Typeface mTypeface = Typeface.createFromAsset(getAssets(), "fonts/vero.ttf");

            lstuserevent = (ListView) findViewById(R.id.lstuserevent);
            ArrayAdapter<String> alljoineventAdapter = new ArrayAdapter<String>(UserEventActivity.this, android.R.layout.simple_list_item_1, string_all_join_event){
                @NonNull
                @Override
                public View getView (int position, View convertView, ViewGroup parent){
                    TextView item = (TextView) super.getView(position,convertView,parent);

                    item.setTypeface(mTypeface);
                    item.setTextSize(14);
                    return item;
                }
            };
            lstuserevent.setAdapter(alljoineventAdapter);

            lstuserevent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(UserEventActivity.this, CancelActivity.class);
                    intent.putExtra("user", User);
                    intent.putExtra("event", all_join_event.get(i));
                    startActivity(intent);
                }
            });






        });
//        lstuserevent = (ListView) findViewById(R.id.lstuserevent);
//        ArrayAdapter<String> alljoineventAdapter = new ArrayAdapter<String>(UserEventActivity.this, android.R.layout.simple_list_item_1, string_all_join_event);
//        lstuserevent.setAdapter(alljoineventAdapter);
//
//        lstuserevent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(UserEventActivity.this, CancelActivity.class);
//                intent.putExtra("user", User);
//                intent.putExtra("event", all_join_event.get(i));
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public void onClick(View view) {
        // do nothing
    }
}
