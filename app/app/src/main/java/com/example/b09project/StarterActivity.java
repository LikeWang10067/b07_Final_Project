package com.example.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class StarterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogIn, btnSignUp;
    private String error_message = null;
    private TextView tv_error_message;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);

        tv_error_message = (TextView) findViewById(R.id.tv_error_message);
        Intent intent = getIntent();
        if(intent != null){
            error_message = intent.getStringExtra("error_message");
            tv_error_message.setText(error_message);
        }
        btnLogIn = (Button) findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(this);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btnSignup:
                startActivity(new Intent(this, SignUpActivity.class));
        }
    }

}
