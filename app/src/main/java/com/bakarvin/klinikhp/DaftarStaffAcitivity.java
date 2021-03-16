package com.bakarvin.klinikhp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bakarvin.klinikhp.databinding.ActivityDaftarStaffAcitivityBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DaftarStaffAcitivity extends AppCompatActivity {

    ActivityDaftarStaffAcitivityBinding daftarStaffBinding;
    DatabaseReference DBref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        daftarStaffBinding = ActivityDaftarStaffAcitivityBinding.inflate(getLayoutInflater());
        setContentView(daftarStaffBinding.getRoot());

        daftarStaffBinding.btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daftarStaff();
            }
        });
    }

    private void daftarStaff() {
        String uName = daftarStaffBinding.txtUserName.getText().toString();
        String uPass = daftarStaffBinding.txtPassword.getText().toString();
        String staffId = "020002";

        if (uName.isEmpty() || uPass.isEmpty()){
            Toast.makeText(this, "Gagal daftar", Toast.LENGTH_SHORT).show();
        } else {
            DBref = FirebaseDatabase.getInstance().getReference("Staff").child(staffId);
            HashMap<String, String> staffMap = new HashMap<>();
//            Staff staff1 = new Staff(staffId, uName, uPass, "Nama Staff", "alamat Staff", "hp Staff");
//            staffMap.put(staff1.getUserName(),staff1);
            staffMap.put("id_staff", staffId);
            staffMap.put("userName", uName);
            staffMap.put("pass", uPass);
            staffMap.put("no_hp", "hp staff");
            staffMap.put("nama", "nama staff");
            staffMap.put("alamat", "alamat staff");

            DBref.setValue(staffMap).addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    Log.d("Masuk", "idnya =" + staffId);
                }
            });
        }
    }
}