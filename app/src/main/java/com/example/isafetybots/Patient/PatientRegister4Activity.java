package com.example.isafetybots.Patient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.isafetybots.R;

public class PatientRegister4Activity extends AppCompatActivity {
    private Button registerNextBtn4;
    private EditText namePerson1,emailPerson1,mobilePerson1,namePerson2,emailPerson2,mobilePerson2,namePerson3,emailPerson3,mobilePerson3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register4);

        registerNextBtn4=(Button) findViewById(R.id.register_next_btn4);
        namePerson1=(EditText) findViewById(R.id.name_person1);
        namePerson2=(EditText) findViewById(R.id.name_person2);
        namePerson3=(EditText) findViewById(R.id.name_person3);
        emailPerson1=(EditText) findViewById(R.id.email_address_person1);
        emailPerson2=(EditText) findViewById(R.id.email_address_person2);
        emailPerson3=(EditText) findViewById(R.id.email_address_person3);
        mobilePerson1=(EditText) findViewById(R.id.mobile_number_person1);
        mobilePerson2=(EditText) findViewById(R.id.mobile_number_person2);
        mobilePerson3=(EditText) findViewById(R.id.mobile_number_person3);

        registerNextBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientRegister4Activity.this, PatientRegisterFinalActivity.class);
                startActivity(intent);
            }
        });
    }
}