package com.example.myfirstapp;

public class LoginPage {
    public LoginPage(){}

    public void login(String applicantname, int password){
        database_operation.CheckLogIn(applicantname, password, (user User) ->{
            if(User == null){ UIinterface.redirectToStarterPage();}
            else if(User.get_admin() == true){ UIinterface.redirectToAdminPage();}
            else{ UIinterface.redirectToCustomerPage();}
        });
    }

}