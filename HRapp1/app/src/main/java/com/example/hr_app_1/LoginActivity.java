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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = LoginActivity.this;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView registerText;
    private ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().hide();
        initViews();
        initListeners();
        //     initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button2);
        registerText = findViewById(R.id.errorMsg);
        loadingProgressBar = findViewById(R.id.loading);
    }
    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        loginButton.setOnClickListener(this);
        registerText.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        // databaseHelper = new DatabaseHelper(activity);
        //inputValidation = new InputValidation(activity);
    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button2:
                validateLogin();
                break;
            case R.id.errorMsg:
                //validateLogin();
                // Navigate to RegisterActivity
                // Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                //  startActivity(intentRegister);
                break;
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private void validateLogin() {
        if(usernameEditText.getText().toString().equals("admin@gmail.com") && passwordEditText.getText().toString().equals("password")){
            Intent accountsIntent = new Intent(activity, MainActivity.class);
            emptyInputEditText();
            startActivity(accountsIntent);
        }
        else{
            Context context = getApplicationContext();
            CharSequence text = "Incorrect username or password";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        usernameEditText.setText(null);
        passwordEditText.setText(null);
    }
}
