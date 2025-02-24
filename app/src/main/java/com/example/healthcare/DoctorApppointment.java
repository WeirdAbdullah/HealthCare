package com.example.healthcare;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthcare.adapter.RecyclerDoctor;
import com.example.healthcare.model.DoctorModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorApppointment extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<DoctorModel> docList ;
    RecyclerDoctor adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_apppointment);

        docList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecyclerDoctor(this, docList);
        recyclerView.setAdapter(adapter);

        fetchDoctorsFromFirebase();
    }

    private void fetchDoctorsFromFirebase() {


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Doctors");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                docList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.child("name").getValue(String.class);
                    String degree = snapshot.child("degree").getValue(String.class);
                    String special = snapshot.child("special").getValue(String.class);
                    String rate = snapshot.child("rate").getValue(String.class);
                    String address = snapshot.child("address").getValue(String.class);
                    String patience = snapshot.child("patience").getValue(String.class);
                    String experience = snapshot.child("Experience").getValue(String.class);
                    String bio = snapshot.child("Bio").getValue(String.class);


                    int img = snapshot.child("img").getValue(int.class);


                    DoctorModel doctor = new DoctorModel(img, degree, name, special, rate, address, patience, experience, bio);


                    docList.add(doctor);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(DoctorApppointment.this, "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
