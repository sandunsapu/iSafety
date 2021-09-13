package com.example.isafety;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class PatientRegisterActivity extends AppCompatActivity {
    private Button nextRegisterbtn;
    private EditText inputNameWithInitials,inputFullName,inputAddress,inputBirthday,inputEmail,inputMobileNumber,inputPasswordRegister,inputPasswordConfirm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);
    }
}