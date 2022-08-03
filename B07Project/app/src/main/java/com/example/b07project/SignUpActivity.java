package com.example.b07project;

public class SignUpActivity {
    user User;
    public SignUpActivity(){}

    public void Signup(String applicantname, int password) {
        database_operation.CheckSignUp(applicantname, password, (user User) -> {
            this.User = User;
            if (User == null) {
                UIinterface.redirectToStarterPage();
            } else {
                UIinterface.redirectToCustomerPage();
            }
        });
    }


}
