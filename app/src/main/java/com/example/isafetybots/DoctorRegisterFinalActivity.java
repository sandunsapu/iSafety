package com.example.isafetybots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rey.material.widget.CheckBox;

public class DoctorRegisterFinalActivity extends AppCompatActivity {

    private Button registerBtn;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register_final);

        registerBtn=(Button) findViewById(R.id.final_register_btn);
        checkBox=(CheckBox) findViewById(R.id.agree_checkbox);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorRegisterFinalActivity.this, DoctorLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}