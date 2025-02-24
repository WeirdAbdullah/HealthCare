package com.example.healthcare;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthcare.adapter.Recycler;
import com.example.healthcare.adapter.RecyclerHA;
import com.example.healthcare.model.LabTestModel;
import com.example.healthcare.model.hamodel;

import java.util.ArrayList;

public class healtharticle extends AppCompatActivity {
    ArrayList<hamodel> h = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_healtharticle);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RecyclerView recyclerView = findViewById(R.id.ha);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        h.add(new hamodel(R.drawable.h1,"Understanding Hypertension","A brief overview of hypertension, its causes, and how it can be managed", "Hypertension, or high blood pressure, is a common condition that affects millions worldwide. It occurs when the force of blood against the walls of your arteries is consistently too high. Left untreated, it can lead to serious health issues such as heart disease and stroke. This article explores the causes, risk factors, symptoms, and effective management strategies for hypertension."));
        h.add(new hamodel(R.drawable.h2,"Benefits of a Balanced Diet","The importance of maintaining a balanced diet for overall health and wellness", "A balanced diet is essential for maintaining good health. It provides the necessary nutrients—vitamins, minerals, protein, and fiber—that your body needs to function properly. This article delves into the key components of a balanced diet, including the importance of fruits, vegetables, lean proteins, and whole grains, as well as tips on how to create a healthy eating plan"));
        h.add(new hamodel(R.drawable.h3,"Mental Health and Stress Management","Explore strategies for managing stress and improving mental well-being.", "Stress is a natural response to daily challenges, but chronic stress can negatively impact mental and physical health. This article offers practical tips for managing stress, including mindfulness, exercise, and healthy coping mechanisms. It also highlights the importance of seeking professional help when necessary and how maintaining mental health is crucial for overall well-being"));
        h.add(new hamodel(R.drawable.h4,"Preventing Type 2 Diabetes","Key steps to reduce the risk of developing Type 2 diabetes.", "Type 2 diabetes is a preventable condition, and lifestyle changes can significantly reduce the risk of developing it. This article covers the key prevention strategies, including maintaining a healthy weight, staying physically active, and eating a diet rich in fiber and low in refined sugars. It also discusses the importance of regular medical checkups and early detection of risk factors."));


        //Recycler adapter = new Recycler(this, labs);
        //recyclerView.setAdapter(adapter);

        RecyclerHA adapter = new RecyclerHA(this,h);
        recyclerView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}