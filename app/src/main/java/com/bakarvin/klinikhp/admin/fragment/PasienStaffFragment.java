package com.bakarvin.klinikhp.admin.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bakarvin.klinikhp.adapter.PasienAdapter;
import com.bakarvin.klinikhp.admin.crud.pasien.DetailPasienActivity;
import com.bakarvin.klinikhp.databinding.FragmentPasienStaffBinding;
import com.bakarvin.klinikhp.model.Pasien;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PasienStaffFragment extends Fragment {

    FragmentPasienStaffBinding pasienStaffBinding;
    ArrayList<Pasien> pasienList;
    PasienAdapter pasienAdapter;
    DatabaseReference dbPasien;

    public PasienStaffFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        pasienStaffBinding = FragmentPasienStaffBinding.inflate(inflater, container, false);
        View v = pasienStaffBinding.getRoot();
        // Inflate the layout for this fragment
        dbPasien = FirebaseDatabase.getInstance().getReference("Pasien");
        pasienList = new ArrayList<>();
        getData();
//        getDataTapiPakeQuery();
//        cariData2();
        return  v;

    }

    void getDataTapiPakeQuery() {
//        dbPasien.orderByChild("no_ktp").equalTo("0001");
        Query q = dbPasien.orderByChild("no_ktp").equalTo("003");
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Pasien pasien = snap.getValue(Pasien.class);
                    pasienList.add(pasien);
                }
                pasienAdapter = new PasienAdapter(pasienList, getContext());
                pasienStaffBinding.rvListPasien.setAdapter(pasienAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    void cariData2(){
        dbPasien.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot datas : snapshot.getChildren()) {
                        Pasien pasien = snapshot.getValue(Pasien.class);
                        pasienList.add(pasien);
                    }
                        pasienAdapter = new PasienAdapter(pasienList, getContext());
                        pasienStaffBinding.rvListPasien.setAdapter(pasienAdapter);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), "ID null", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    void getData() {
        dbPasien.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Pasien pasien = snapshot.getValue(Pasien.class);
                            pasienList.add(pasien);
                        }
                        pasienAdapter = new PasienAdapter(pasienList, getContext());
                        pasienStaffBinding.rvListPasien.setAdapter(pasienAdapter);

                        pasienAdapter.ActionClick(new PasienAdapter.onAction() {
                            @Override
                            public void onActionClick(View view, int position) {
                                Pasien pasien = pasienList.get(position);
                                Intent i = new Intent(getContext(), DetailPasienActivity.class);
                                i.putExtra("no_ktp", pasien.getNo_ktp());
                                startActivity(i);
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}
