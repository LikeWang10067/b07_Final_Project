package com.example.myfirstapp;

public class SignUpPage {
    user User;
    public SignUpPage(){}

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
