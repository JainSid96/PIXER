package com.example.msjapplication.img;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class LoginActivity extends AppCompatActivity {
    EditText userText , passText ;
    Button loginButton ,signupButton;

    public void loginClick(View view){

        if (loginButton.getText().toString().matches("SIGN UP")) {

                ParseUser user = new ParseUser();
                user.setUsername(userText.getText().toString());
                user.setPassword(passText.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(LoginActivity.this, "SIGN UP SUCESSFULL ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        }else{

                ParseUser.logInInBackground(userText.getText().toString(), passText.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            Toast.makeText(LoginActivity.this, "LOGIN SUCESSFULL ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this,  e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        }
    }

    public void signupClick(View view){
        if (signupButton.getText().toString().matches("New here? Sign Up")){
            loginButton.setText("SIGN UP");
            signupButton.setText("Already a user ? Login here");
        }else{
            loginButton.setText("LOGIN");
            signupButton.setText("New here? Sign Up");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("5a967a381894abccdb56c1c6ecc4b385409b1c3f")
                .clientKey("630027cb21a16f374934feedd9195e90a86f6711")
                .server("http://ec2-13-126-125-192.ap-south-1.compute.amazonaws.com:80/parse/")
                .build()
        );

        userText = (EditText)findViewById(R.id.userText);
        passText = (EditText)findViewById(R.id.passText);
        loginButton = (Button) findViewById(R.id.button);
        signupButton = (Button) findViewById(R.id.button2);


        //ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
}
