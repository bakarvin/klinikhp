package com.bakarvin.klinikhp.admin.crud.dokter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bakarvin.klinikhp.R;
import com.bakarvin.klinikhp.admin.crud.dokter.fragment.DaftarDataDokterFragment;
import com.bakarvin.klinikhp.admin.crud.dokter.fragment.DaftarJadwalDokterFragment;
import com.bakarvin.klinikhp.admin.fragment.HomeStaffFragment;
import com.bakarvin.klinikhp.admin.fragment.RekamStaffFragment;
import com.bakarvin.klinikhp.databinding.ActivityDaftarDokterBinding;
import com.bakarvin.klinikhp.model.Staff;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DaftarDokterActivity extends AppCompatActivity {

    DatabaseReference DBref;
    ActivityDaftarDokterBinding daftarDokterBinding;
    List<String> steps = new ArrayList<String>();
    private int currentStep;
    private String currentFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        daftarDokterBinding = ActivityDaftarDokterBinding.inflate(getLayoutInflater());
        setContentView(daftarDokterBinding.getRoot());
        List<String> steps = new ArrayList<String>(){{
         add("Data Dokter");
         add("Jadwal Dokter");
        }};
//        daftarDokterBinding.btnBackDaftarDokter.setEnabled(false);
//        daftarDokterBinding.btnBackDaftarDokter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                currentStep --;
//                setCurrentStep(currentStep);
//            }
//        });
//        daftarDokterBinding.btnNextDaftarDokter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                currentStep ++;
//                setCurrentStep(currentStep);
//            }
//        });
        daftarDokterBinding.stepDaftarDokter.setSteps(steps);
//        setCurrentStep(currentStep);
        loadFrag(new DaftarDataDokterFragment());
//        getCurrentFragment();
    }
    private boolean loadFrag(Fragment fragment){
        if (fragment !=null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragDaftarDokter, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }
    void setCurrentStep(int currentStep){
        if (currentStep == 0){
            Fragment fragment = null;
            fragment = new DaftarDataDokterFragment();
            loadFrag(fragment);
//            daftarDokterBinding.btnBackDaftarDokter.setEnabled(false);
//            daftarDokterBinding.btnBackDaftarDokter.setText("Batal");
//            daftarDokterBinding.btnNextDaftarDokter.setText("Next");
//            daftarDokterBinding.stepDaftarDokter.done(false);
//            daftarDokterBinding.stepDaftarDokter.go(currentStep, true);
        } else if (currentStep == 1) {
            Fragment fragment = null;
            fragment = new DaftarJadwalDokterFragment();
            loadFrag(fragment);
//            daftarDokterBinding.btnBackDaftarDokter.setEnabled(true);
//            daftarDokterBinding.btnBackDaftarDokter.setText("Back");
//            daftarDokterBinding.btnNextDaftarDokter.setText("Selesai");
//            daftarDokterBinding.stepDaftarDokter.go(currentStep, true);
        } else if (currentStep > 1){
            Toast.makeText(getApplicationContext(), "Jumlah Step : " + currentStep, Toast.LENGTH_SHORT).show();
            daftarDokterBinding.stepDaftarDokter.done(true);
        }
    }

//    void getCurrentFragment(){
//        Fragment fragment = daftarDokterBinding.fragDaftarDokter;
//        if (fragment != null){
//            if (fragment == new DaftarDataDokterFragment()){
//                daftarDokterBinding.stepDaftarDokter.done(false);
//                daftarDokterBinding.stepDaftarDokter.go(0, true);
//            } else if (fragment == new DaftarJadwalDokterFragment()){
//                daftarDokterBinding.stepDaftarDokter.go(1, true);
//            }
//        }
//    }

}