package com.example.isafetybots.Doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.isafetybots.R;

public class DoctorDashboardActivity extends AppCompatActivity {
    private Button patientListBtn,calenderBtn;
    private ImageButton notificationsBtn,settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);

        patientListBtn=(Button) findViewById(R.id.patients_list_btn);
        calenderBtn=(Button) findViewById(R.id.calender_btn);
        notificationsBtn=(ImageButton) findViewById(R.id.doctor_notifications_btn);
        settingsBtn=(ImageButton) findViewById(R.id.doctor_settings_btn);

        patientListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DoctorDashboardActivity.this,DoctorPatientListActivity.class);
                startActivity(intent);

            }
        });
        notificationsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorDashboardActivity.this,DoctorNotificationsActivity.class);
                startActivity(intent);
            }
        });
    }
}