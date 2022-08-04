package com.example.b07project;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username, password;
    private Button btnLogIn;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogIn = (Button) findViewById(R.id.btnLogIn2);
        btnLogIn.setOnClickListener(this);
//        btnBack = (Button) findViewById(R.id.btnBack);
//        btnBack.setOnClickListener(this);
        username = (EditText) findViewById(R.id.username2);
        password = (EditText) findViewById(R.id.password2);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void LogIn() {
        String username = this.username.getText().toString().trim();
        String password = this.password.getText().toString();
        int num_password = Integer.valueOf(password);
        database_operation.fgetuser(username, num_password, (user User) -> {
            if (User == null) {
                this.username.setError("User Incorrect or Password Incorrect");
                this.password.setError("User Incorrect or Password Incorrect");
//                Intent intent = new Intent(this, MainActivity.class);
//                intent.putExtra("error_message", "User Incorrect or Password Incorrect");
//                startActivity(intent);

            } else if (User.get_admin() == true) {
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


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogIn2:
                LogIn();
                break;
//            case R.id.btnBack:
//                startActivity(new Intent(this, MainActivity.class));
        }
    }


}
