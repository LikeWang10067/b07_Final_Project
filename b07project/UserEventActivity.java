package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class UserEventActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView lstuserevent;
    private List<String> string_all_join_event;
    private List<event> all_join_event;
    private user User;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_event);

        User = (user) getIntent().getSerializableExtra("user");
        string_all_join_event = new ArrayList<String>();
        Do.DisplayEventsByUser(User.get_name(), (ArrayList<event> events) -> {
            all_join_event = events;
            for(event Event: events){
                string_all_join_event.add(Event.getEventName());
            }

            lstuserevent = (ListView) findViewById(R.id.lstuserevent);
            ArrayAdapter<String> alljoineventAdapter = new ArrayAdapter<String>(UserEventActivity.this, android.R.layout.simple_list_item_1, string_all_join_event);
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
