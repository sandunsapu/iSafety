package com.example.isafetybots.Doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.isafetybots.DetailedReportsActivity;
import com.example.isafetybots.R;

public class DoctorSendInstructionsActivity extends AppCompatActivity {
    private Button liveCheckupBtn,viewDetailedReportBtn,viewMedicalReportsBtn,sendBtn;
    private EditText instructionsBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_send_instructions);

        liveCheckupBtn=(Button)findViewById(R.id.live_checkup_btn);
        viewDetailedReportBtn=(Button)findViewById(R.id.view_detailed_report_btn);
        viewMedicalReportsBtn=(Button)findViewById(R.id.view_medical_reports_btn);
        sendBtn=(Button)findViewById(R.id.send_instruction_btn);
        instructionsBox=(EditText) findViewById(R.id.send_instructions_box);

        viewDetailedReportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DoctorSendInstructionsActivity.this, DetailedReportsActivity.class);
                startActivity(intent);
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorSendInstructionsActivity.this, DoctorPatientListActivity.class);
                startActivity(intent);
            }
        });
    }
}