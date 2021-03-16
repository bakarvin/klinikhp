package com.bakarvin.klinikhp.admin.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakarvin.klinikhp.Preferences;
import com.bakarvin.klinikhp.R;
import com.bakarvin.klinikhp.databinding.FragmentHomeStaffBinding;

public class HomeStaffFragment extends Fragment {

    FragmentHomeStaffBinding homeStaffBinding;

    public HomeStaffFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        homeStaffBinding = FragmentHomeStaffBinding.inflate(inflater, container, false);
        View v = homeStaffBinding.getRoot();
        String uname = Preferences.getLoginUname(getContext());
        String id = Preferences.getUserLogin(getContext());
        homeStaffBinding.txtNamaStaff.setText(uname);
        homeStaffBinding.txtIDStaff.setText(id);
        return v;
    }
}