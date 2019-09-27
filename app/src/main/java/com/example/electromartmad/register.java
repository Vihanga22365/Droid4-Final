package com.example.electromartmad;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class register extends AppCompatActivity {


    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnSignUp = (Button) findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ET_Name = (EditText) findViewById(R.id.ET_Name);
                EditText ET_Email = (EditText) findViewById(R.id.ET_Email);
                EditText ET_UserName = (EditText) findViewById(R.id.ET_UserName);
                EditText ET_Password = (EditText) findViewById(R.id.ET_Password);
                EditText ET_ConfirmPassword = (EditText) findViewById(R.id.ET_ConfirmPassword);

                String NameStr = ET_Name.getText().toString();
                String EmailStr = ET_Email.getText().toString();
                String UserNameStr = ET_UserName.getText().toString();
                String PasswordStr = ET_Password.getText().toString();
                String ConfirmPasswordStr = ET_ConfirmPassword.getText().toString();


                if (TextUtils.isEmpty(NameStr) || TextUtils.isEmpty(EmailStr) || TextUtils.isEmpty(UserNameStr) || TextUtils.isEmpty(PasswordStr) || TextUtils.isEmpty(ConfirmPasswordStr) ) {
                    Toast.makeText(register.this, "Field Should not be Null", Toast.LENGTH_SHORT).show();
                    return;

                }

                if (!PasswordStr.equals(ConfirmPasswordStr)) {
                    Toast.makeText(register.this, "Passwords dont match", Toast.LENGTH_SHORT).show();
                } else {
                    //Insert infor to Database
                    Contact contact = new Contact();
                    contact.SetName(NameStr);
                    contact.SetEmail(EmailStr);
                    contact.SetUserName(UserNameStr);
                    contact.SetPassword(PasswordStr);
                    helper.InsertContacts(contact);

                    Intent loginIntent = new Intent(register.this, login.class);
                    //Send Data
                    loginIntent.putExtra("UserName", UserNameStr);
                    loginIntent.putExtra("Password", PasswordStr);

                    startActivity(loginIntent);

                }
            }
        });
    }





}
