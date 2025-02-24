package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcare.databinding.ActivityUserRegistrationBinding;
import com.example.healthcare.model.SignupModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class UserRegistration extends AppCompatActivity {
    @NonNull
    ActivityUserRegistrationBinding binding;
    DatabaseReference database;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();

        binding.register.setOnClickListener(view -> {
            String name = binding.name.getText().toString().trim();
            String email = binding.email.getText().toString().trim();
            String address = binding.address.getText().toString().trim();
            String ageStr = binding.age.getText().toString().trim();
            String password = binding.password.getText().toString();
            String cpassword = binding.cpassword.getText().toString();

            if (validateInput(name, email, address, ageStr, password, cpassword)) {
                int age = Integer.parseInt(ageStr);
                signUp(email, password, name, address, age);
            }
        });
    }

    private boolean validateInput(String name, String email, String address, String ageStr, String password, String cpassword) {
        if (name.isEmpty()) {
            binding.name.setError("Enter your name");
            return false;
        }
        if (email.isEmpty() || !isValidEmail(email)) {
            binding.email.setError("Enter a valid email");
            return false;
        }
        if (address.isEmpty()) {
            binding.address.setError("Enter your address");
            return false;
        }
        if (ageStr.isEmpty()) {
            binding.age.setError("Enter your age");
            return false;
        }
        try {
            int age = Integer.parseInt(ageStr);
            if (age <= 0 || age > 120) {
                binding.age.setError("Enter a valid age");
                return false;
            }
        } catch (NumberFormatException e) {
            binding.age.setError("Age must be a number");
            return false;
        }
        if (password.isEmpty()) {
            binding.password.setError("Enter a password");
            return false;
        }
        if (cpassword.isEmpty() || !password.equals(cpassword)) {
            binding.cpassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+$";
        return Pattern.matches(emailPattern, email);
    }

    private void signUp(String email, String password, String name, String address, int age) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            String userId = user.getUid();
                            SignupModel signupModel = new SignupModel(userId, name, email, address, String.valueOf(age), password);
                            database.child(userId).setValue(signupModel)
                                    .addOnSuccessListener(unused -> {
                                        Toast.makeText(UserRegistration.this, "SignUp Successfully", Toast.LENGTH_SHORT).show();
                                        mAuth.signOut();
                                        startActivity(new Intent(UserRegistration.this, MainActivity.class));
                                        finish();
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(UserRegistration.this, "Failed to save user data: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                    });
                        }
                    } else {
                        Toast.makeText(UserRegistration.this, "SignUp Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
