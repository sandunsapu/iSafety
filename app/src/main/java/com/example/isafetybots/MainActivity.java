package com.example.isafetybots;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.isafetybots.Doctor.DoctorLoginActivity;
import com.example.isafetybots.Patient.PatientLoginActivity;

public class MainActivity extends AppCompatActivity {
    private Button patientBtn,doctorBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        patientBtn = (Button) findViewById(R.id.patientbtn);
        doctorBtn=(Button) findViewById(R.id.doctorbtn);

        patientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PatientLoginActivity.class);
                startActivity(intent);
            }
        });

        doctorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DoctorLoginActivity.class);
                startActivity(intent);
            }
        });
    }

}