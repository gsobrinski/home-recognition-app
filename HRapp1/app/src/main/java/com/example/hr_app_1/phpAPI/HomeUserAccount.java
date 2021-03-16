package com.example.hr_app_1.phpAPI;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class HomeUserAccount {

    private String key;
    private String username;
    private String firstName;
    private String lastName;
    private String passwordHash;
    private String email;

    public HomeUserAccount() {

    }

    public static String registerAccount(HomeUserAccount account) {
        try {
            String link = "http://localhost/HomeRecognition/registration.php";
            String data = URLEncoder.encode("username", "UTF-8") + "=" +
                    URLEncoder.encode(account.getUsername(), "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                    URLEncoder.encode(account.getPasswordHash(), "UTF-8");
            data += "&" + URLEncoder.encode("firstName", "UTF-8") + "=" +
                    URLEncoder.encode(account.getFirstName(), "UTF-8");
            data += "&" + URLEncoder.encode("lastName", "UTF-8") + "=" +
                    URLEncoder.encode(account.getLastName(), "UTF-8");
            data += "&" + URLEncoder.encode("email", "UTF-8") + "=" +
                    URLEncoder.encode(account.getEmail(), "UTF-8");
            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write(data);
            wr.flush();
        } catch (Exception e) {
            return e.getMessage();
        }


        return null;
    }

    public static HomeUserAccount retrieveAccountData(@NotNull String username, @NotNull String passwordHash) {
        try {
            HomeUserAccount acc = new HomeUserAccount();
            String link = "http://hrappphpapi-env.eba-edytepbk.us-east-2.elasticbeanstalk.com/";
            String data = URLEncoder.encode("username", "UTF-8") + "=" +
                    URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                    URLEncoder.encode(passwordHash, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write(data);
            wr.flush();

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();

            acc.key = reader.readLine();
            acc.username = reader.readLine();
            acc.firstName = reader.readLine();
            acc.lastName = reader.readLine();
            acc.email = reader.readLine();
            acc.passwordHash = reader.readLine();

            System.out.println(sb.toString());
            return acc;
        } catch (Exception e) {
            // Error retrieving account for some reason or another
            return null;
        }
    }

    public boolean setUsername(@NotNull String username) {
        // TODO check that username is valid using regex
        if(username.matches("")) {

        } else {

        }
        this.username = username;
        return true;
    }

    public boolean setFirstName(@NotNull String firstName) {
        // TODO check that first name is valid using regex
        if(firstName.matches("")) {

        } else {

        }
        // Bypass future regex
        this.firstName = firstName;
        return true;
    }

    public boolean setLastName(@NotNull String lastName) {
        // TODO check that last name is valid using regex
        if(lastName.matches("")) {

        } else {

        }
        // Bypass future regex
        this.lastName = lastName;
        return true;
    }

    public boolean setEmail(@NotNull String email) {
        // TODO check that email is valid using regex
        if(email.matches("")) {

        } else {

        }
        // Bypass future regex
        this.email = email;
        return true;
    }

    public boolean setPassword(@NotNull String password) {
        // TODO check that password is valid using regex
        if(password.matches("")) {

        } else {

        }
        // TODO hash and salt password before storing
        this.passwordHash = hashPassword(password);
        return true;
    }

    private String hashPassword(@NotNull String password) {
        // TODO hash passwords, and add salt to guarantee uniqueness
        return password;
    }

    public String getKey() {
        return key;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public String toString() {
        return key + username + firstName + lastName + email + passwordHash;
    }
} // End of File

