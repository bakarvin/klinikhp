package com.bakarvin.klinikhp.dokter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bakarvin.klinikhp.MainActivity;
import com.bakarvin.klinikhp.Preferences;
import com.bakarvin.klinikhp.R;
import com.bakarvin.klinikhp.admin.fragment.HomeStaffFragment;
import com.bakarvin.klinikhp.databinding.ActivityMainMenuDokterBinding;
import com.bakarvin.klinikhp.dokter.fragment.HomeDokterFragment;

public class MainMenuDokterActivity extends AppCompatActivity {

    ActivityMainMenuDokterBinding mainMenuDokterBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainMenuDokterBinding = ActivityMainMenuDokterBinding.inflate(getLayoutInflater());
        setContentView(mainMenuDokterBinding.getRoot());
        mainMenuDokterBinding.txtTitle.setText("Home");
        mainMenuDokterBinding.dokterHomeFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = null;
                fragment = new HomeDokterFragment();
                mainMenuDokterBinding.txtTitle.setText("Home");
                loadFrag(fragment);
            }
        });
        mainMenuDokterBinding.btnDokterLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preferences.clearLoginUser(getBaseContext());
                startActivity(new Intent(getApplicationContext(), LoginDokterActivity.class));
            }
        });
        loadFrag(new HomeDokterFragment());
    }
    private boolean loadFrag(Fragment fragment){
        if (fragment !=null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainFragDokter, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }
}