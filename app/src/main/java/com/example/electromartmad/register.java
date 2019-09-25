package com.example.electromartmad;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class register extends AppCompatActivity {

    EditText ET_Name , ET_Email , ET_UserName , ET_Password , ET_ConfirmPassword;
    Button btnSignUp ;

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

                if(!validateUserName() | !validateEmail() | !validatePassword() | !confirmPassword())
                {
                    return;
                }

                if (PasswordStr.equals(null)) {
                    Toast.makeText(register.this, "Should not be Null", Toast.LENGTH_SHORT).show();
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

    private boolean validateUserName() {

        String userNameInput = ET_UserName.getEditableText().toString().trim();

        if (userNameInput.isEmpty()) {

            ET_UserName.setError("User Name field can't be empty");
            return false;

        }
        else if(userNameInput.length()>10) {

            ET_UserName.setError("User Name is too long");
            return false;

        }

        else {

            ET_UserName.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {

        String emailInput = ET_Email.getEditableText().toString().trim();

        if (emailInput.isEmpty()) {

            ET_Email.setError("Email field can't be empty");
            return false;

        } else {

            ET_Email.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {

        String passwordInput = ET_Password.getEditableText().toString().trim();

        if (passwordInput.isEmpty()) {

            ET_Password.setError("Password field can't be empty");
            return false;

        } else {

            ET_Password.setError(null);
            return true;
        }
    }
    private boolean confirmPassword() {

        String confirmPasswordInput = ET_ConfirmPassword.getEditableText().toString().trim();

        if (confirmPasswordInput.isEmpty()) {

            ET_ConfirmPassword.setError("Password must be confirmed");
            return false;

        } else {

            ET_ConfirmPassword.setError(null);
            return true;
        }
    }


}
