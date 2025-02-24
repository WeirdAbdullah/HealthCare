package com.example.healthcare;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class makeAppointment extends AppCompatActivity {

    DatabaseReference database;

    EditText patientNameEditText, contactNumberEditText, symptomsEditText;
    Button confirmAppointmentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_appointment);

        database = FirebaseDatabase.getInstance().getReference("appointments");

        patientNameEditText = findViewById(R.id.patient_name);
        contactNumberEditText = findViewById(R.id.contact_number);
        symptomsEditText = findViewById(R.id.symptoms);
        confirmAppointmentButton = findViewById(R.id.ma);

        confirmAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientName = patientNameEditText.getText().toString().trim();
                String contactNumber = contactNumberEditText.getText().toString().trim();
                String symptoms = symptomsEditText.getText().toString().trim();

                if (patientName.isEmpty()) {
                    patientNameEditText.setError("Enter patient name");
                    return;
                }
                if (contactNumber.isEmpty()) {
                    contactNumberEditText.setError("Enter contact number");
                    return;
                }
                if (symptoms.isEmpty()) {
                    symptomsEditText.setError("Enter symptoms");
                    return;
                }

                String appointmentId = database.push().getKey();

                AppointmentModel appointment = new AppointmentModel(appointmentId, patientName, contactNumber, symptoms);

                if (appointmentId != null) {
                    database.child(appointmentId).setValue(appointment)
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(makeAppointment.this, "Appointment Confirmed", Toast.LENGTH_SHORT).show();

                                patientNameEditText.setText("");
                                contactNumberEditText.setText("");
                                symptomsEditText.setText("");
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(makeAppointment.this, "Failed to confirm appointment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                }
            }
        });
    }

    public static class AppointmentModel {
        private String appointmentId;
        private String patientName;
        private String contactNumber;
        private String symptoms;

        public AppointmentModel() {
        }

        public AppointmentModel(String appointmentId, String patientName, String contactNumber, String symptoms) {
            this.appointmentId = appointmentId;
            this.patientName = patientName;
            this.contactNumber = contactNumber;
            this.symptoms = symptoms;
        }

        public String getAppointmentId() {
            return appointmentId;
        }

        public void setAppointmentId(String appointmentId) {
            this.appointmentId = appointmentId;
        }

        public String getPatientName() {
            return patientName;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public String getContactNumber() {
            return contactNumber;
        }

        public void setContactNumber(String contactNumber) {
            this.contactNumber = contactNumber;
        }

        public String getSymptoms() {
            return symptoms;
        }

        public void setSymptoms(String symptoms) {
            this.symptoms = symptoms;
        }
    }
}
