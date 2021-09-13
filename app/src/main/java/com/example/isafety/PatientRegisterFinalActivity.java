package com.example.isafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatientRegisterFinalActivity extends AppCompatActivity {
    private Button patientRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register_final);

        patientRegisterBtn=(Button) findViewById(R.id.final_register_btn);

        patientRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientRegisterFinalActivity.this, patientLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}