package com.example.healthcare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class doctordetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctordetails);


        TextView special, title, adr, patient, exp, rate, bio;
        ImageView doctor = findViewById(R.id.doctor);
        Button ma;
        special = findViewById(R.id.special);
        title = findViewById(R.id.Title);
        adr = findViewById(R.id.adress);
        patient = findViewById(R.id.patienceCount);
        exp = findViewById(R.id.Experience);
        rate = findViewById(R.id.rate);
        bio = findViewById(R.id.Bio);
        ma = findViewById(R.id.ma);

        ma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(doctordetails.this, makeAppointment.class);
                startActivity(i);
                //Toast.makeText(doctordetails.this, "Done", Toast.LENGTH_SHORT).show();

            }
        });





        Intent i = getIntent();
        String s = i.getStringExtra("special");
        String t = i.getStringExtra("title");
        String a = i.getStringExtra("adress");
        String r = i.getStringExtra("rate");
        String b = i.getStringExtra("bio");
        String e = i.getStringExtra("exp");
        String p = i.getStringExtra("patient");



        int d = i.getIntExtra("doc_photo",0);


        doctor.setImageResource(d);



        special.setText(s);
        title.setText(t);
        adr.setText(a);
        patient.setText(p);
        exp.setText(e);
        rate.setText(r);
        bio.setText(b);




    }
}