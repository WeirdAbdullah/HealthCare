package com.example.healthcare;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class bmi extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bmi);


        EditText weight, htft, htin;
        Button calc;
        TextView res;
        ImageView out;
        calc = findViewById(R.id.cal);
        weight = findViewById(R.id.wt);
        htft = findViewById(R.id.htft);
        htin = findViewById(R.id.htin);
        res = findViewById(R.id.res);
        out = findViewById(R.id.out);


        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int wt = Integer.parseInt(weight.getText().toString());
                int ft = Integer.parseInt(htft.getText().toString());
                int in = Integer.parseInt(htin.getText().toString());

                int total_in = ft*12 + in;
                double total_cm = total_in * 2.54;
                double total_m = total_cm/100;

                double r = wt/(total_m*total_m);

                String bmiResult = String.format("%.2f", r);
                String resultText;
                if(r>25){
                    resultText = "You are overweight";
                    out.setImageResource(R.drawable.sad);
                }

                else if(r<18){
                    resultText = "You are underweight";
                    out.setImageResource(R.drawable.sad);

                }


                else{
                    resultText = "You are healthy";
                    out.setImageResource(R.drawable.happy);

                }

                res.setText("Your BMI is: " + bmiResult + "\n" + resultText);

            }
        });





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}