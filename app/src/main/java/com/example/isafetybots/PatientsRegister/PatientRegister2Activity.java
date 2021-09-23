package com.example.isafetybots.PatientsRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.isafetybots.R;

public class PatientRegister2Activity extends AppCompatActivity {
    private Button diseaseSelect,reportUpload,registerNextBtn2,skipBtn;
    private EditText descriptionBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register2);

        diseaseSelect=(Button) findViewById(R.id.disease_select);
        reportUpload=(Button) findViewById(R.id.report_upload);
        registerNextBtn2=(Button) findViewById(R.id.register_next_btn2);
        skipBtn=(Button) findViewById(R.id.skip_medical_condition);
        descriptionBox=(EditText) findViewById(R.id.description_box_area);

        registerNextBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientRegister2Activity.this,PatientRegister3Activity.class);
                startActivity(intent);
            }
        });

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientRegister2Activity.this,PatientRegisterFinalActivity.class);
                startActivity(intent);
            }
        });
    }
}