package com.example.isafetybots.Doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.isafetybots.R;

public class DoctorPatientListActivity extends AppCompatActivity {
    private View patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_patient_list);

        patient=(View) findViewById(R.id.patient_viewer);

        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DoctorPatientListActivity.this,DoctorSendInstructionsActivity.class);
                startActivity(intent);
            }
        });
    }
}