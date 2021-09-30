package com.example.isafetybots.Patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.isafetybots.Model.Patients;
import com.example.isafetybots.NavigationPane.PatientNavigationActivity;
import com.example.isafetybots.Prevalent.Prevalent;
import com.example.isafetybots.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class PatientLoginActivity extends AppCompatActivity {
    private Button loginButton,signInButton;
    private EditText inputMobile,inputPassword;
    private ProgressDialog loadingBar;
    private String parentDbName="Patients";
    private CheckBox checkBoxRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);

        loginButton=(Button) findViewById(R.id.login_button);
        signInButton=(Button) findViewById(R.id.sign_in_button);
        inputMobile=(EditText) findViewById(R.id.login_mobile_input);
        inputPassword=(EditText) findViewById(R.id.login_password_input);
        loadingBar=new ProgressDialog(this);
        checkBoxRememberMe=(CheckBox) findViewById(R.id.rememberme_chkbx);

        Paper.init(this);



        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientLoginActivity.this, PatientRegisterActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPatient();

                //Intent intent = new Intent(PatientLoginActivity.this,PatientNavigationActivity.class);
                //startActivity(intent);
            }
        });
    }

    private void loginPatient() {

        String mobile = inputMobile.getText().toString();
        String password = inputPassword.getText().toString();

        if (TextUtils.isEmpty(mobile))
        {
            Toast.makeText(this, "Please enter your Mobile Number here", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please enter your Password here", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Logging In Account");
            loadingBar.setMessage("Please wait while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToAccount(mobile,password);



        }

    }

    private void AllowAccessToAccount(String mobile, String password) {

        if(checkBoxRememberMe.isChecked())
        {
            Paper.book().write(Prevalent.patientPhoneKey,mobile);
            Paper.book().write(Prevalent.patientPasswordKey,password);
        }


        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.child(parentDbName).child(mobile).exists())
                {
                    Patients patientsData=snapshot.child(parentDbName).child(mobile).getValue(Patients.class);
                    if (patientsData.getMobile().equals(mobile))
                    {
                        if (patientsData.getPassword().equals(password))
                        {
                            Toast.makeText(PatientLoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(PatientLoginActivity.this, PatientNavigationActivity.class);
                            startActivity(intent);
                        }
                        else {

                            loadingBar.dismiss();
                            Toast.makeText(PatientLoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else {

                    Toast.makeText(PatientLoginActivity.this, "Account with this "+mobile+" number does not exist!", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}