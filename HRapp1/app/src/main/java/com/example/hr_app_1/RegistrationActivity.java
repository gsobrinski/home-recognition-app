package com.example.hr_app_1;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hr_app_1.R;

public class RegistrationActivity extends AppCompatActivity {
    // view components
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button registerButton;
    private TextView loginText;

    // form fields
    String firstName;
    String lastName;
    String email;
    String initialPass;
    String confirmPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //getSupportActionBar().hide();
        initViews();
        initListeners();
        //     initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        firstNameEditText = findViewById(R.id.firstName);
        lastNameEditText = findViewById(R.id.lastName);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.initialPassword);
        confirmPasswordEditText = findViewById(R.id.confirmPassword);
        registerButton = findViewById(R.id.register);
        loginText = findViewById(R.id.loginInstead);
    }

    private void initListeners(){
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = firstNameEditText.getText().toString();
                lastName = lastNameEditText.getText().toString();
                email = emailEditText.getText().toString();
                initialPass = passwordEditText.getText().toString();
                confirmPass = confirmPasswordEditText.getText().toString();

                if (firstName.length() == 0) {
                    firstNameEditText.setHint("Please enter your first name");
                }
                if (lastName.length() == 0){
                    firstNameEditText.setHint("Please enter your last name");
                }
                if (email.length() == 0){
                    emailEditText.setHint("Please enter your email");
                }
                if (initialPass.length() == 0){
                    passwordEditText.setHint("Please enter your password");
                }
                if (confirmPass.length() == 0){
                    confirmPasswordEditText.setHint("Please re-enter your password");
                }

                if(firstName.length() != 0 && lastName.length() != 0 && email.length() != 0 &&
                        initialPass.length() != 0 && confirmPass.length() != 0) {
                    if(!initialPass.equals(confirmPass)){
                        Context context = getApplicationContext();
                        CharSequence text = "Please re-enter the same password";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        firstNameEditText.setText("Alright, you've registered!");
                        lastNameEditText.setText("");
                        emailEditText.setText("");
                        passwordEditText.setText("");
                        confirmPasswordEditText.setText("");

                    }
                }

            }

        });
    }

}
