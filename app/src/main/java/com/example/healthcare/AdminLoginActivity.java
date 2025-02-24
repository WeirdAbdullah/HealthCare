package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;

    String email = "abdullah@gmail.com";
    String password = "12345678";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = usernameEditText.getText().toString().trim();
                String enteredPassword = passwordEditText.getText().toString().trim();

                if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                    Toast.makeText(AdminLoginActivity.this, "Please enter both username and password.", Toast.LENGTH_SHORT).show();
                } else {
                    if (enteredUsername.equals(email) && enteredPassword.equals(password)) {
                        Toast.makeText(AdminLoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                         Intent intent = new Intent(AdminLoginActivity.this, AddDoctorActivity.class);
                         startActivity(intent);
                         finish();
                    } else {
                        Toast.makeText(AdminLoginActivity.this, "Invalid username or password.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
