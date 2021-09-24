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

import com.example.isafetybots.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class PatientRegisterActivity extends AppCompatActivity {
    private Button nextRegisterbtn;
    private EditText inputNameWithInitials,inputFullName,inputAddress,inputBirthday,inputEmail,inputMobileNumber,inputPasswordRegister,inputPasswordConfirm;
    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);

        nextRegisterbtn=(Button) findViewById(R.id.register_next_btn);
        inputNameWithInitials=(EditText) findViewById(R.id.name_with_initials);
        inputFullName=(EditText) findViewById(R.id.full_name);
        inputAddress=(EditText) findViewById(R.id.address);
        inputBirthday=(EditText) findViewById(R.id.birthday);
        inputEmail=(EditText) findViewById(R.id.register_email_input);
        inputMobileNumber=(EditText) findViewById(R.id.register_phone);
        inputPasswordRegister=(EditText) findViewById(R.id.register_password);
        inputPasswordConfirm=(EditText) findViewById(R.id.register_password_confirm);
        loadingBar= new ProgressDialog(this);

        nextRegisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }
        });
    }

    private void CreateAccount() {

        String namewithinitials = inputNameWithInitials.getText().toString();
        String fullname = inputFullName.getText().toString();
        String address = inputAddress.getText().toString();
        String birthday = inputBirthday.getText().toString();
        String email = inputEmail.getText().toString();
        String mobile = inputMobileNumber.getText().toString();
        String password = inputPasswordRegister.getText().toString();
        String passwordconfirm = inputPasswordConfirm.getText().toString();

        if (TextUtils.isEmpty(namewithinitials))
        {
            Toast.makeText(this, "Please enter your Name with initials  here", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(fullname))
        {
            Toast.makeText(this, "Please enter your Full Name here", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(address))
        {
            Toast.makeText(this, "Please enter your Address here", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(birthday))
        {
            Toast.makeText(this, "Please enter your Birthday here", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please enter your Email here", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(mobile))
        {
            Toast.makeText(this, "Please enter your Mobile Number here", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please enter your Password here", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(passwordconfirm))
        {
            Toast.makeText(this, "Please enter your Confirmed Password here", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Create Account");
            loadingBar.setMessage("Please wait while we are checking the credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatePatient(fullname,mobile,password);

        }

    }

    private void ValidatePatient(String fullname, String mobile, String password) {

        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Patients").child(mobile).exists()))
                {
                    HashMap<String, Object> patientDataMap=new HashMap<>();
                    patientDataMap.put("mobile",mobile);
                    patientDataMap.put("fullName",fullname);
                    patientDataMap.put("password",password);

                    RootRef.child("Patients").child(mobile).updateChildren(patientDataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(PatientRegisterActivity.this, "Congratulations Your account has been created.", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(PatientRegisterActivity.this,PatientRegister2Activity.class);
                                        startActivity(intent);
                                    }
                                    else 
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(PatientRegisterActivity.this, "Network Error : Please try again after some time...", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                }
                else
                {
                    Toast.makeText(PatientRegisterActivity.this, "This "+mobile+" already exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(PatientRegisterActivity.this, "Please try again using another email address", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(PatientRegisterActivity.this, PatientLoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}