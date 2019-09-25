package com.example.electromartmad;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnSignUp =(Button) findViewById(R.id.btnSignUp);
        Button btnLoging = (Button) findViewById(R.id.btnLoging);


        btnLoging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText UserNameET = (EditText) findViewById(R.id.ET_UserName);
                String UserNameStr = UserNameET.getText().toString();
                //Passing Password
                EditText PasswordET = (EditText) findViewById(R.id.ET_Password);
                String PasswordStr = PasswordET.getText().toString();


                String dbPassword = helper.LoginIn(UserNameStr);
                //Send UserName to Database to find it, and return Password
                //To compare it with Current Password from user input
                if (dbPassword.equals(PasswordStr)) {
                    Intent loginIntent = new Intent(login.this, MainActivity.class);
                    //Send Data
                    loginIntent.putExtra("UserName", UserNameStr);
                    loginIntent.putExtra("Password", PasswordStr);

                    startActivity(loginIntent);
                } else {
                    Toast.makeText(login.this, "UserName and Passwords dont match", Toast.LENGTH_SHORT).show();
                }
            }


        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SignUpIntent = new Intent(login.this, register.class);
                startActivity(SignUpIntent);
            }
        });

    }


}
