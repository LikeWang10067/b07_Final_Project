package com.example.b07project;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnLogIn, btnSignUp;
    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        btnLogIn = (Button) findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(this);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void LogIn() {
        String username = this.username.getText().toString().trim();
        String password = this.password.getText().toString();
        if (username.equals("") || password.equals("")) {
            this.username.setError("Please input username");
            this.password.setError("Please input password");
            return;
        }else {
            int num_password = Integer.valueOf(password);
            Do.CheckLogIn(username, num_password, (user User) -> {
                Log.d("User", String.valueOf(User == null));
                if (User == null) {
                    this.username.setError("User Incorrect or Password Incorrect");
                    this.password.setError("User Incorrect or Password Incorrect");
                    //                Intent intent = new Intent(this, MainActivity.class);
                    //                intent.putExtra("error_message", "User Incorrect or Password Incorrect");
                    //                startActivity(intent);

                } else if (User.get_admin() == 1) {
                    Intent intent = new Intent(this, AdminActivity.class);
                    intent.putExtra("user", User); //remember class user need to implement Serializable
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, CustomerActivity.class);
                    intent.putExtra("user", User); //remember class user need to implement Serializable
                    startActivity(intent);
                }

            });
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogIn:
                LogIn();
                break;
            case R.id.btnSignUp:
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
        }
    }

}