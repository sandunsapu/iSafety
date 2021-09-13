package com.example.isafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PatientRegisterActivity extends AppCompatActivity {
    private Button nextRegisterbtn;
    private EditText inputNameWithInitials,inputFullName,inputAddress,inputBirthday,inputEmail,inputMobileNumber,inputPasswordRegister,inputPasswordConfirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);

        nextRegisterbtn=(Button) findViewById(R.id.register_next_btn);
        inputNameWithInitials=(EditText) findViewById(R.id.name_with_initials);
        inputFullName=(EditText) findViewById(R.id.full_name);
        inputAddress=(EditText) findViewById(R.id.address);
        inputBirthday=(EditText) findViewById(R.id.birthday);
        inputEmail=(EditText) findViewById(R.id.register_email_input);
        inputMobileNumber=(EditText) findViewById(R.id.register_phone);
        inputPasswordRegister=(EditText) findViewById(R.id.register_password);
        inputPasswordConfirm=(EditText) findViewById(R.id.register_password_confirm);

        nextRegisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientRegisterActivity.this,PatientRegister2Activity.class);
                startActivity(intent);
            }
        });
    }
}