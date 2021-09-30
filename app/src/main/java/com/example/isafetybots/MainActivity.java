package com.example.isafetybots;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.isafetybots.Doctor.DoctorLoginActivity;
import com.example.isafetybots.Model.Patients;
import com.example.isafetybots.NavigationPane.PatientNavigationActivity;
import com.example.isafetybots.Patient.PatientLoginActivity;
import com.example.isafetybots.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    private Button patientBtn,doctorBtn;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        patientBtn = (Button) findViewById(R.id.patientbtn);
        doctorBtn=(Button) findViewById(R.id.doctorbtn);
        loadingBar=new ProgressDialog(this);

        Paper.init(this);

        patientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PatientLoginActivity.class);
                startActivity(intent);
            }
        });

        doctorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DoctorLoginActivity.class);
                startActivity(intent);
            }
        });

        String patientPhoneKey=Paper.book().read(Prevalent.patientPhoneKey);
        String patientPasswordKey=Paper.book().read(Prevalent.patientPasswordKey);

        if(patientPhoneKey!="" && patientPasswordKey!="") {
            if (!TextUtils.isEmpty(patientPhoneKey) && !TextUtils.isEmpty(patientPasswordKey)) {

                AllowAccess(patientPhoneKey,patientPasswordKey);

                loadingBar.setTitle("Already Logged In");
                loadingBar.setMessage("Please Wait....");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();

            }
        }
    }

    private void AllowAccess(String mobile, String password) {

        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.child("Patients").child(mobile).exists())
                {
                    Patients patientsData=snapshot.child("Patients").child(mobile).getValue(Patients.class);
                    if (patientsData.getMobile().equals(mobile))
                    {
                        if (patientsData.getPassword().equals(password))
                        {
                            Toast.makeText(MainActivity.this, "Please wait... You are already logged in.", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(MainActivity.this, PatientNavigationActivity.class);
                            startActivity(intent);
                        }
                        else {

                            loadingBar.dismiss();
                            Toast.makeText(MainActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else {

                    Toast.makeText(MainActivity.this, "Account with this "+mobile+" number does not exist!", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}