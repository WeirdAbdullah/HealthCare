package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcare.databinding.ActivityWelComeBinding;

public class WelComeActivity extends AppCompatActivity {

    private ActivityWelComeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelComeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.login.setOnClickListener(view -> {
            Intent intent = new Intent(WelComeActivity.this, MainActivity.class);
            startActivity(intent);

        });

        binding.signup.setOnClickListener(view -> {
            Intent intent = new Intent(WelComeActivity.this, UserRegistration.class);
            startActivity(intent);

        });

        binding.adminlogin.setOnClickListener(view -> {
            Intent intent = new Intent(WelComeActivity.this, AdminLoginActivity.class);
            startActivity(intent);

        });
    }
}
