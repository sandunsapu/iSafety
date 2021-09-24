package com.example.isafetybots.Patient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.isafetybots.R;

public class PatientRequestCheckupActivity extends AppCompatActivity {
    private Button payRequestBtn;
    private EditText requestNoteBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_request_checkup);

        payRequestBtn=(Button) findViewById(R.id.pay_request_btn);
        requestNoteBox=(EditText) findViewById(R.id.notes_for_doctor);


    }
}