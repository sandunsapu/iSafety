package com.example.isafetybots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PatientRegister3Activity extends AppCompatActivity {
    private Button registerNextBtn3;
    private EditText guardiansName,guardiansAddress,guardiansEmail,guardiansMobileNumber1,guardiansMobileNumber2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register3);

        registerNextBtn3=(Button) findViewById(R.id.register_next_btn3);
        guardiansAddress=(EditText) findViewById(R.id.guardians_address);
        guardiansEmail=(EditText) findViewById(R.id.guardians_email_address);
        guardiansName=(EditText) findViewById(R.id.guardians_name);
        guardiansMobileNumber1=(EditText) findViewById(R.id.guardians_mobile_1);
        guardiansMobileNumber2=(EditText) findViewById(R.id.guardians_mobile_2);

        registerNextBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientRegister3Activity.this, PatientRegister4Activity.class);
                startActivity(intent);
            }
        });
    }
}