package com.example.isafetybots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DoctorRegisterActivity extends AppCompatActivity {
    private Button doctorRegisterBtn;
    private EditText inputDoctorName,inputDoctorFullName,inputDoctorAddress,inputDoctorEmail,inputDoctorMobile,inputDoctorWorkArea,inputDoctorRegNumber,inputDoctorPassword,inputDoctorConfirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);

        doctorRegisterBtn=(Button) findViewById(R.id.doctor_register_btn);
        inputDoctorName=(EditText) findViewById(R.id.doctor_name_with_initials);
        inputDoctorFullName=(EditText) findViewById(R.id.doctor_full_name);
        inputDoctorAddress=(EditText) findViewById(R.id.doctor_address);
        inputDoctorEmail=(EditText) findViewById(R.id.doctor_register_email_input);
        inputDoctorMobile=(EditText) findViewById(R.id.doctor_register_phone);
        inputDoctorWorkArea=(EditText) findViewById(R.id.doctor_area_working);
        inputDoctorRegNumber=(EditText) findViewById(R.id.doctor_register_email_input);
        inputDoctorPassword=(EditText) findViewById(R.id.register_password_doctor);
        inputDoctorConfirmPassword=(EditText) findViewById(R.id.doctor_register_password_confirm);

        doctorRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorRegisterActivity.this, DoctorRegisterFinalActivity.class);
                startActivity(intent);
            }
        });

    }
}