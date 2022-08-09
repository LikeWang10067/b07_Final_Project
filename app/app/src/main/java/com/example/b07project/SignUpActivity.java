package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username, password;
    private Button btnSignUp;
//    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Do.cleaner((Boolean clean)->{});

        btnSignUp = (Button) findViewById(R.id.btnSignUps);
        btnSignUp.setOnClickListener(this);
//        btnBack = (Button) findViewById(R.id.btnBack);
//        btnBack.setOnClickListener(this);
        username = (EditText) findViewById(R.id.username_s);
        password = (EditText) findViewById(R.id.password_s);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignUps:
                SignUp();
                break;
        }
    }

    private void SignUp() {
        String username = this.username.getText().toString().trim();
        String password = this.password.getText().toString();
//        int num_password = Integer.valueOf(password);
        Do.CheckSignUp(username, password, (user User) -> {
            if (User == null) {
                Log.d("SignUp", "null");
                this.username.setError("User already exist");
//                Intent intent = new Intent(this, MainActivity.class);
//                intent.putExtra("error_message", "User already exist");
//                startActivity(intent);
            } else {
                Log.d("SignUp", "SignUp");
                Intent intent = new Intent(this, CustomerActivity.class);
                intent.putExtra("user", User); //remember class user need to implement Serializable
                startActivity(intent);
            }
        });
    }

}
