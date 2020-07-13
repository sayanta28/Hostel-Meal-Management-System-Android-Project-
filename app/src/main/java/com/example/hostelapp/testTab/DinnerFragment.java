package com.example.hostelapp.testTab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.hostelapp.R;

import java.util.LinkedHashMap;
import java.util.List;

public class DinnerFragment extends Fragment {

    public LinkedHashMap<String, List<String>> dinnerList = new LinkedHashMap<>();



    public DinnerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_03, container, false);
    }

}
