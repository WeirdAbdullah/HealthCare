package com.example.healthcare;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthcare.adapter.Recycler;
import com.example.healthcare.model.LabTestModel;

import java.util.ArrayList;

public class LabTest extends AppCompatActivity {
    ArrayList<LabTestModel> labs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test);

        RecyclerView recyclerView = findViewById(R.id.recyclerLab);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        labs.add(new LabTestModel(R.drawable.l1, "CBC", "৳450","5", "It is best", "2 hours"));
//        labs.add(new LabTestModel(R.drawable.l2, "Thyroid", "৳500","2", "It is best", "3 hours"));
//        labs.add(new LabTestModel(R.drawable.l3, "ECG", "৳800","1", "It is best", "1 hours"));
//        labs.add(new LabTestModel(R.drawable.l4, "Urine", "৳1150","1", "It is best", "5 hours"));
//        labs.add(new LabTestModel(R.drawable.l5, "Blood", "৳750","2", "It is best", "2 hours"));
//        labs.add(new LabTestModel(R.drawable.l6, "CRP", "৳850","2", "It is best", "2 hours"));
//        labs.add(new LabTestModel(R.drawable.l8, "X-Ray", "৳750","2", "It is best", "2 hours"));
//        labs.add(new LabTestModel(R.drawable.l10, "MRI", "৳750","2", "It is best", "2 hours"));
        labs.add(new LabTestModel(R.drawable.l1, "CBC", "৳450","5", "Complete Blood Count (CBC) helps in evaluating overall health and detecting disorders such as anemia, infection, and many other diseases.", "2 hours"));
        labs.add(new LabTestModel(R.drawable.l2, "Thyroid", "৳500","2", "The Thyroid test measures thyroid hormone levels to check for thyroid gland disorders.", "3 hours"));
        labs.add(new LabTestModel(R.drawable.l3, "ECG", "৳800","1", "An Electrocardiogram (ECG) records the electrical signals in the heart to detect heart conditions.", "1 hour"));
        labs.add(new LabTestModel(R.drawable.l4, "Urine", "৳1150","1", "Urine analysis detects various disorders such as urinary tract infections, kidney disease, and diabetes.", "5 hours"));
        labs.add(new LabTestModel(R.drawable.l5, "Blood", "৳750","2", "A comprehensive blood test that evaluates multiple health markers to give insight into overall health.", "2 hours"));
        labs.add(new LabTestModel(R.drawable.l6, "CRP", "৳850","2", "C-Reactive Protein (CRP) test detects inflammation in the body, which can indicate infections or chronic inflammatory diseases.", "2 hours"));
        labs.add(new LabTestModel(R.drawable.l8, "X-Ray", "৳750","2", "X-ray imaging helps visualize the internal structures of the body, especially bones, to identify fractures or infections.", "2 hours"));
        labs.add(new LabTestModel(R.drawable.l10, "MRI", "৳750","2", "Magnetic Resonance Imaging (MRI) provides detailed images of organs and tissues, useful for diagnosing various conditions.", "2 hours"));












        Recycler adapter = new Recycler(this, labs);
        recyclerView.setAdapter(adapter);




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}