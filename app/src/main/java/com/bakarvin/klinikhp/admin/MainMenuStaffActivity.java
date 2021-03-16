package com.bakarvin.klinikhp.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.bakarvin.klinikhp.Preferences;
import com.bakarvin.klinikhp.R;
import com.bakarvin.klinikhp.admin.fragment.DaftarStaffFragment;
import com.bakarvin.klinikhp.admin.fragment.DokterStaffFragment;
import com.bakarvin.klinikhp.admin.fragment.HomeStaffFragment;
import com.bakarvin.klinikhp.admin.fragment.PasienStaffFragment;
import com.bakarvin.klinikhp.admin.fragment.RekamStaffFragment;
import com.bakarvin.klinikhp.databinding.ActivityMainMenuStaffBinding;
import com.bakarvin.klinikhp.databinding.ItemFilterMedisBinding;
import com.bakarvin.klinikhp.dokter.LoginDokterActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainMenuStaffActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainMenuStaffBinding mainMenuStaffBinding;
    boolean drawerOpen;
    boolean filterMedis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainMenuStaffBinding = ActivityMainMenuStaffBinding.inflate(getLayoutInflater());
        setContentView(mainMenuStaffBinding.getRoot());
        drawerOpen = false;
        mainMenuStaffBinding.txtTitle.setText("Home");
        mainMenuStaffBinding.staffHomeFrag.setOnClickListener(this);
        mainMenuStaffBinding.staffDaftarFrag.setOnClickListener(this);
        mainMenuStaffBinding.staffDokterFrag.setOnClickListener(this);
        mainMenuStaffBinding.staffPasienFrag.setOnClickListener(this);
        mainMenuStaffBinding.staffMedisFrag.setOnClickListener(this);
        mainMenuStaffBinding.staffLogoutFrag.setOnClickListener(this);
        mainMenuStaffBinding.btnFilterRekam.setOnClickListener(this);
        mainMenuStaffBinding.btnDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerState();
            }
        });
        loadFrag(new HomeStaffFragment());
    }

    private boolean loadFrag(Fragment fragment){
        if (fragment !=null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.mainFragStaff, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }

    void drawerState() {
        if (!drawerOpen) {
            mainMenuStaffBinding.drawerStaff.openDrawer(Gravity.LEFT);
            drawerOpen = true;
        } else {
            mainMenuStaffBinding.drawerStaff.closeDrawers();
            drawerOpen = false;
        }
    }

    void setFilterMedis(){
        if (!filterMedis){
            mainMenuStaffBinding.btnFilterRekam.setActivated(false);
            mainMenuStaffBinding.btnFilterRekam.setVisibility(View.INVISIBLE);
        } else {
            mainMenuStaffBinding.btnFilterRekam.setActivated(true);
            mainMenuStaffBinding.btnFilterRekam.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.staffHomeFrag:
                loadFrag(new HomeStaffFragment());
                mainMenuStaffBinding.txtTitle.setText("Home");
                drawerState();
                filterMedis = false;
                setFilterMedis();
                break;
            case R.id.staffDaftarFrag:
                loadFrag(new DaftarStaffFragment());
                mainMenuStaffBinding.txtTitle.setText("Pendaftaran");
                drawerState();
                filterMedis = false;
                setFilterMedis();
                break;
            case R.id.staffDokterFrag:
                loadFrag(new DokterStaffFragment());
                mainMenuStaffBinding.txtTitle.setText("List Dokter");
                drawerState();
                filterMedis = false;
                setFilterMedis();
                break;
            case R.id.staffPasienFrag:
                loadFrag(new PasienStaffFragment());
                mainMenuStaffBinding.txtTitle.setText("List Pasien");
                drawerState();
                filterMedis = false;
                setFilterMedis();
                break;
            case R.id.staffMedisFrag:
                loadFrag(new RekamStaffFragment());
                mainMenuStaffBinding.txtTitle.setText("List Rekam Medis");
                drawerState();
                filterMedis = true;
                setFilterMedis();
                break;
            case R.id.staffLogoutFrag:
                Preferences.clearLoginUser(getBaseContext());
                startActivity(new Intent(getApplicationContext(), LoginDokterActivity.class));
                break;
        }
    }

//    void openFilter(){
//        AlertDialog builder = new AlertDialog.Builder(context).create();
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        ItemFilterMedisBinding filterMedisBinding = ItemFilterMedisBinding.inflate(layoutInflater);
//        builder.setView(filterMedisBinding.getRoot());
//        builder.show();
//        filterMedisBinding.btnMin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        calendar.set(year, month, dayOfMonth);
//                        filterMedisBinding.btnMin.setText(simpleDateFormat.format(calendar.getTime()));
//                        date_minimal = calendar.getTime();
//
//                        String input1 = filterMedisBinding.btnMin.getText().toString();
//                        String input2 = filterMedisBinding.btnMax.getText().toString();
//                        if (input1.isEmpty() || input2.isEmpty()){
//                            filterMedisBinding.btnApplyFilter.setClickable(false);
//                        }else {
//                            filterMedisBinding.btnApplyFilter.setClickable(true);
//                        }
//                    }
//                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//                datePickerDialog.show();
//            }
//        });
//        filterMedisBinding.btnMax.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        calendar.set(year, month, dayOfMonth);
//                        filterMedisBinding.btnMax.setText(simpleDateFormat.format(calendar.getTime()));
//                        date_maximal = calendar.getTime();
//
//                        String input1 = filterMedisBinding.btnMin.getText().toString();
//                        String input2 = filterMedisBinding.btnMax.getText().toString();
//                        if (input1.isEmpty() || input2.isEmpty()){
//                            filterMedisBinding.btnApplyFilter.setClickable(false);
//                        }else {
//                            filterMedisBinding.btnApplyFilter.setClickable(true);
//                        }
//                    }
//                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//                datePickerDialog.show();
//            }
//        });
//        filterMedisBinding.btnApplyFilter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String dateMin = filterMedisBinding.btnMin.getText().toString();
//                String dateMax = filterMedisBinding.btnMax.getText().toString();
//                Bundle bundle = new Bundle();
//                bundle.putString(dateMin, "date_minimal");
//                bundle.putString(dateMax, "date_maximal");
//                RekamStaffFragment medisFrag = new RekamStaffFragment();
//                medisFrag.setArguments(bundle);
//                Toast.makeText(context, dateMin+" & "+dateMax, Toast.LENGTH_SHORT).show();
////                builder.dismiss();
//            }
//        });
//    }

}