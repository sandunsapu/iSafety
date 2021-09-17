package com.example.isafetybots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PatientLoginActivity extends AppCompatActivity {
    private Button loginButton,signInButton;
    private EditText inputEmail,inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);

        loginButton=(Button) findViewById(R.id.login_button);
        signInButton=(Button) findViewById(R.id.sign_in_button);
        inputEmail=(EditText) findViewById(R.id.login_email_input);
        inputPassword=(EditText) findViewById(R.id.login_password_input);


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientLoginActivity.this,PatientRegisterActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientLoginActivity.this,PatientNavigationActivity.class);
                startActivity(intent);
            }
        });
    }
}