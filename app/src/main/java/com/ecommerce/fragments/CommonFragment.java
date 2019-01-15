package com.ecommerce.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecommerce.R;
import com.ecommerce.activities.HomeActivity;

public class CommonFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).getCustomActionBar().hideHomeButton();
        ((HomeActivity) getActivity()).getCustomActionBar().showBackButton();
    }
}
