package com.example.healthcare;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class makebooktest extends AppCompatActivity {

    private EditText fullName, contactNumber, email, testName, testDate;
    private Button confirmBooking;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makebooktest); // Ensure this matches your XML layout file name

        databaseReference = FirebaseDatabase.getInstance().getReference("TestBookings");

        fullName = findViewById(R.id.full_name);
        contactNumber = findViewById(R.id.contact_number);
        email = findViewById(R.id.email);
        testName = findViewById(R.id.test_name);
        testDate = findViewById(R.id.test_date);
        confirmBooking = findViewById(R.id.confirm_booking);

        testName.setText("Blood Test");

        testDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        confirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBookingDetails();
            }
        });
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        testDate.setText(selectedDate);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    private void saveBookingDetails() {
        String name = fullName.getText().toString().trim();
        String contact = contactNumber.getText().toString().trim();
        String userEmail = email.getText().toString().trim();
        String test = testName.getText().toString().trim();
        String date = testDate.getText().toString().trim();

        if (name.isEmpty() || contact.isEmpty() || userEmail.isEmpty() || test.isEmpty() || date.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String bookingId = databaseReference.push().getKey();

        HashMap<String, String> bookingDetails = new HashMap<>();
        bookingDetails.put("fullName", name);
        bookingDetails.put("contactNumber", contact);
        bookingDetails.put("email", userEmail);
        bookingDetails.put("testName", test);
        bookingDetails.put("testDate", date);

        if (bookingId != null) {
            databaseReference.child(bookingId).setValue(bookingDetails).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(makebooktest.this, "Booking Confirmed", Toast.LENGTH_SHORT).show();
                    clearFields();
                } else {
                    Toast.makeText(makebooktest.this, "Failed to confirm booking", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void clearFields() {
        fullName.setText("");
        contactNumber.setText("");
        email.setText("");
        testDate.setText("");
    }
}
