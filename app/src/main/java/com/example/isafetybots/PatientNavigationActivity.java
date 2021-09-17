package com.example.isafetybots;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PatientNavigationActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new DashboardFragment()).commit();

        bottomNavigationView=(BottomNavigationView) findViewById(R.id.patient_bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment temp=null;

                switch (item.getItemId()){
                    case R.id.menu_dashboard:temp=new DashboardFragment();
                        break;
                    case R.id.menu_instructions:temp=new InstructionsFragment();
                        break;
                    case R.id.menu_notifications:temp=new NotificationsFragment();
                        break;
                    case R.id.menu_reports:temp=new ReportsFragment();
                        break;
                    case R.id.menu_settings:temp=new SettingsFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,temp).commit();


                return true;
            }
        });

    }
}