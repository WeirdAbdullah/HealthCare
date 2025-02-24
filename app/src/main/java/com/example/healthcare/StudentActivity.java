package com.example.healthcare;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcare.databinding.ActivityStudentBinding;
import com.example.healthcare.model.StudentModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentActivity extends AppCompatActivity {
    ActivityStudentBinding binding;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Firebase database reference
        database = FirebaseDatabase.getInstance().getReference("student");

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = binding.id.getText().toString();
                String name = binding.name.getText().toString();
                String dept = binding.dept.getText().toString();

                // Input validation
                if(id.isEmpty() || name.isEmpty() || dept.isEmpty()) {
                    Toast.makeText(StudentActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create a student object
                StudentModel student = new StudentModel(id, name, dept);

                // Add data to Firebase
                database.child(id).setValue(student).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(StudentActivity.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
