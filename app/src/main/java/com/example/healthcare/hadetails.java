package com.example.healthcare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class hadetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hadetails);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView img = findViewById(R.id.articleImage);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView title = findViewById(R.id.articleTitle);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView short_description = findViewById(R.id.articleDescription);


        Intent i = getIntent();


        int image = i.getIntExtra("image",0);
        String t = i.getStringExtra("title");
        String d = i.getStringExtra("description");

        img.setImageResource(image);
        title.setText(t);
        short_description.setText(d);




//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
}