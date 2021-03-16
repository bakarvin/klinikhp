package com.bakarvin.klinikhp.admin.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.bakarvin.klinikhp.R;
import com.bakarvin.klinikhp.adapter.MedisAdapter;
import com.bakarvin.klinikhp.admin.crud.rekmedis.DetailRekamMedisStaffActivity;
import com.bakarvin.klinikhp.databinding.FragmentRekamStaffBinding;
import com.bakarvin.klinikhp.databinding.ItemFilterMedisBinding;
import com.bakarvin.klinikhp.dokter.rekmedis.DetailRekamMedisDokterActivity;
import com.bakarvin.klinikhp.model.RekamMedis;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RekamStaffFragment extends Fragment {

    FragmentRekamStaffBinding rekamStaffBinding;
    ArrayList<RekamMedis> medisList;
    MedisAdapter medisAdapter;
    DatabaseReference dbRekam;
    Context context;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date_minimal;
    Date date_maximal;
    String dateMin;
    String dateMax;

    public RekamStaffFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rekamStaffBinding = FragmentRekamStaffBinding.inflate(inflater, container, false);
        View v = rekamStaffBinding.getRoot();
        // Inflate the layout for this fragment
        dbRekam = FirebaseDatabase.getInstance().getReference("RekamMedis");
        medisList = new ArrayList<>();
        context = this.getContext();
        rekamStaffBinding.fabFilterRekamMedis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFilter();
            }
        });
//        Toast.makeText(getContext(), date_min + date_max, Toast.LENGTH_SHORT).show();
        getAllData();
        return  v;
    }

    private void getAllData() {
        dbRekam.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getFilterData(String dateMin, String dateMax){
        Query query = dbRekam.orderByChild("tgl_medis").startAt(dateMin).endAt(dateMax);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showData(DataSnapshot dataSnapshot){
        medisList.clear();
        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            RekamMedis rekamMedis = snapshot.getValue(RekamMedis.class);
            medisList.add(rekamMedis);
        }
        medisAdapter = new MedisAdapter(medisList);
        if (medisAdapter.getItemCount() == 0){
            rekamStaffBinding.lottieLayout.setVisibility(View.VISIBLE);
        } else {
            rekamStaffBinding.rvListMedis.setAdapter(medisAdapter);
            rekamStaffBinding.lottieLayout.setVisibility(View.INVISIBLE);
        }

        medisAdapter.ActionClick(new MedisAdapter.onAction() {
            @Override
            public void onActionClick(View view, int position) {
                RekamMedis rekamMedis = medisList.get(position);
                Intent i = new Intent(getContext(), DetailRekamMedisStaffActivity.class);
                i.putExtra("idMedis",rekamMedis.getId_Medis());
                startActivity(i);
            }
        });
    }

    private void openFilter(){
        AlertDialog builder = new AlertDialog.Builder(context).create();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ItemFilterMedisBinding filterMedisBinding = ItemFilterMedisBinding.inflate(layoutInflater);
        builder.setView(filterMedisBinding.getRoot());
        builder.show();
        filterMedisBinding.btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        filterMedisBinding.btnMin.setText(simpleDateFormat.format(calendar.getTime()));
                        date_minimal = calendar.getTime();

                        String input1 = filterMedisBinding.btnMin.getText().toString();
                        String input2 = filterMedisBinding.btnMax.getText().toString();
                        if (input1.isEmpty() || input2.isEmpty()){
                            filterMedisBinding.btnApplyFilter.setClickable(false);
                        }else {
                            filterMedisBinding.btnApplyFilter.setClickable(true);
                        }
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        filterMedisBinding.btnMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year, month, dayOfMonth);
                        filterMedisBinding.btnMax.setText(simpleDateFormat.format(calendar.getTime()));
                        date_maximal = calendar.getTime();

                        String input1 = filterMedisBinding.btnMin.getText().toString();
                        String input2 = filterMedisBinding.btnMax.getText().toString();
                        if (input1.isEmpty() || input2.isEmpty()){
                            filterMedisBinding.btnApplyFilter.setClickable(false);
                        }else {
                            filterMedisBinding.btnApplyFilter.setClickable(true);
                        }
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
        filterMedisBinding.btnApplyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateMin = filterMedisBinding.btnMin.getText().toString();
                dateMax = filterMedisBinding.btnMax.getText().toString();
                getFilterData(dateMin, dateMax);
                builder.dismiss();
            }
        });
    }


}