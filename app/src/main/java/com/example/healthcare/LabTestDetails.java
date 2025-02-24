package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LabTestDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test_details);

        ImageView labTest = findViewById(R.id.lab_test_img);
        TextView lab_test_name = findViewById(R.id.lab_test_name);
        TextView lab_test_info = findViewById(R.id.lab_test_info);
        TextView price = findViewById(R.id.patienceCount);
        TextView no_of_test = findViewById(R.id.Experience);
        Intent i = getIntent();
        Button b = findViewById(R.id.bt);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LabTestDetails.this, makebooktest.class);
                startActivity(i);
                //Toast.makeText(doctordetails.this, "Done", Toast.LENGTH_SHORT).show();

            }
        });

        int d = i.getIntExtra("lab_img",0);
        String lab_test = i.getStringExtra("lab_test_name");
        String info = i.getStringExtra("lab_test_info");
        String p = i.getStringExtra("price");
        String no = i.getStringExtra("no_of_test");

        lab_test_name.setText(lab_test);
        lab_test_info.setText(info);
        price.setText(p);
        labTest.setImageResource(d);
        no_of_test.setText(no);



    }
}