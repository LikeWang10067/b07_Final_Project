package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username, password;
    private Button btnSignUp
//    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSignUp = (Button) findViewById(R.id.btnLogIn);
        btnSignUp.setOnClickListener(this);
//        btnBack = (Button) findViewById(R.id.btnBack);
//        btnBack.setOnClickListener(this);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnSignUp:
                SignUp();
                break;
//            case R.id.btnBack:
//                startActivity(new Intent(this, MainActivity.class));
        }
    }

    private void SignUp(){
        String username = this.username.getText().toString().trim();
        String password = this.password.getText().toString();
        int num_password = Integer.valueOf(password);
        database_operation.CheckSignUp(username, num_password, (user User) -> {
            if(User == null){
                this.username.setError("User already exist");
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("error_message", "User already exist");
                startActivity(intent);
            }
            else if(User.get_admin() == true){
                Intent intent = new Intent(this, AdminActivity.class);
                intent.putExtra("user", User); //remember class user need to implement Serializable
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(this, CustomerActivity.class);
                intent.putExtra("user", User); //remember class user need to implement Serializable
                startActivity(intent);
            }
        });
    }

}
