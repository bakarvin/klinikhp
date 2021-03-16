package com.bakarvin.klinikhp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bakarvin.klinikhp.admin.LoginStaffActivity;
import com.bakarvin.klinikhp.admin.MainMenuStaffActivity;
import com.bakarvin.klinikhp.databinding.ActivityMainBinding;
import com.bakarvin.klinikhp.dokter.LoginDokterActivity;
import com.bakarvin.klinikhp.dokter.MainMenuDokterActivity;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    int kodeLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        checkPref();
        activityMainBinding.btnLoginStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginStaffActivity.class));
            }
        });
        activityMainBinding.btnLoginDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginDokterActivity.class));
            }
        });
    }

    private void checkPref(){
        if (Preferences.getLoginStatus(getBaseContext())){
            kodeLogin = Integer.parseInt(Preferences.getLoginKode(getBaseContext()));
            masukMenu();
        } else {
            Toast.makeText(this, "Gaada user yang Login", Toast.LENGTH_SHORT).show();
        }
    }

    private void masukMenu(){
        if (kodeLogin == 1){
            startActivity(new Intent(this, MainMenuDokterActivity.class));
        } else  {
            startActivity(new Intent(this, MainMenuStaffActivity.class));
        }
    }
}