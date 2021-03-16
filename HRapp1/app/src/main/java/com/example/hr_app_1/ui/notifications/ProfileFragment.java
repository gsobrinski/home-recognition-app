package com.example.hr_app_1.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hr_app_1.R;

public class ProfileFragment extends Fragment {

    // views
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView errorMsg;
    private View root;

    // form fields
    String username;
    String password;

    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        root = inflater.inflate(R.layout.fragment_profile, container, false);

        initViews();

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();

                if(username.length() == 0) {
                    errorMsg.setText("Please enter your username");
                } else if (password.length() == 0) {
                    errorMsg.setText("Please enter your password");
                } else {
                    errorMsg.setText("Welcome " + username + "!");
                }
//                System.out.println(username);
//                System.out.println(password);
                usernameEditText.setText("");
                passwordEditText.setText("");

                // database activity goes here
                // place error/success message in the registerText View
            }
        });

        return root;
    }

//    init views
    private void initViews() {
        usernameEditText = (EditText) root.findViewById(R.id.username);
        passwordEditText = (EditText) root.findViewById(R.id.password);
        loginButton = (Button) root.findViewById(R.id.login_button2);
        errorMsg = (TextView) root.findViewById(R.id.errorMsg);
    }
}

//final TextView textView = root.findViewById(R.id.text_notifications);
//        profileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                //textView.setText(s);
//            }
//        });