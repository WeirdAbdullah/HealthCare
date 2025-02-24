package com.example.healthcare;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcare.databinding.ActivityAddDoctorBinding;
import com.example.healthcare.model.DoctorModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddDoctorActivity extends AppCompatActivity {

    private ActivityAddDoctorBinding binding;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance().getReference("Doctors");

        setupSpecialitySpinner();
        setupImageSpinner();

        binding.addbtn.setOnClickListener(v -> validateAndAddDoctor());
    }

    private void setupSpecialitySpinner() {
        List<String> specialties = new ArrayList<>();
        specialties.add("Cardiologist");
        specialties.add("Dermatologist");
        specialties.add("Neurologist");
        specialties.add("Orthopedist");
        specialties.add("Pediatrician");
        specialties.add("Psychiatrist");
        specialties.add("Radiologist");
        specialties.add("Surgeon");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, specialties);
        binding.special.setAdapter(adapter);
    }

    private void setupImageSpinner() {
        List<String> imageNumbers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            imageNumbers.add(String.valueOf(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, imageNumbers);
        binding.img.setAdapter(adapter);
    }

    private void validateAndAddDoctor() {
        String name = binding.name.getText().toString().trim();
        String degree = binding.degree.getText().toString().trim();
        String rate = binding.rate.getText().toString().trim();
        String address = binding.address.getText().toString().trim();
        String patience = binding.patience.getText().toString().trim();
        String experience = binding.Experience.getText().toString().trim();
        String bio = binding.Bio.getText().toString().trim();
        String special = binding.special.getSelectedItem().toString();
        String imgString = binding.img.getSelectedItem().toString();
        int imgResId = getImageResource(Integer.parseInt(imgString));

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(degree) || TextUtils.isEmpty(rate) ||
                TextUtils.isEmpty(address) || TextUtils.isEmpty(patience) || TextUtils.isEmpty(experience) ||
                TextUtils.isEmpty(bio)) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        DoctorModel doctor = new DoctorModel(imgResId, degree, name, special, rate, address, patience, experience, bio);

        String doctorId = database.push().getKey();
        if (doctorId != null) {
            database.child(doctorId).setValue(doctor)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(AddDoctorActivity.this, "Doctor added successfully!", Toast.LENGTH_SHORT).show();
                        clearFields();
                    })
                    .addOnFailureListener(e -> Toast.makeText(AddDoctorActivity.this, "Failed to add doctor", Toast.LENGTH_SHORT).show());
        }
    }

    private int getImageResource(int index) {
        switch (index) {
            case 1: return R.drawable.doctor1;
            case 2: return R.drawable.doctor2;
            case 3: return R.drawable.doctor3;
            case 4: return R.drawable.doctor4;
            case 5: return R.drawable.doctor5;
            case 6: return R.drawable.doctor6;
            case 7: return R.drawable.doctor7;
            case 8: return R.drawable.doctor8;
            case 9: return R.drawable.doctor9;
            case 10: return R.drawable.doctor10;
            default: return R.drawable.doctor10;
        }
    }

    private void clearFields() {
        binding.name.setText("");
        binding.degree.setText("");
        binding.rate.setText("");
        binding.address.setText("");
        binding.patience.setText("");
        binding.Experience.setText("");
        binding.Bio.setText("");
        binding.special.setSelection(0);
        binding.img.setSelection(0);
    }
}
