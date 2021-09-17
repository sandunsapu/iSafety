package com.example.isafetybots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DoctorLoginActivity extends AppCompatActivity {
    private Button loginBtn,signUpBtn;
    private EditText inputEmail,inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        loginBtn=(Button) findViewById(R.id.login_button);
        signUpBtn=(Button) findViewById(R.id.sign_in_button);
        inputEmail=(EditText) findViewById(R.id.login_email_input);
        inputPassword=(EditText) findViewById(R.id.login_password_input);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorLoginActivity.this,DoctorRegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}