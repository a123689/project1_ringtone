package com.example.dat.project1_ringtone.View.Fragment;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dat.project1_ringtone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {


    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    ImageView imBack,imCloseFeedBack;
    RelativeLayout rlRate,rlUpdate;
    Dialog dialogRate,dialogFeedback,dialogUpdate;
    Button btnRate, btnLate;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imBack = view.findViewById(R.id.imBackSetting);
        rlRate = view.findViewById(R.id.rlRate);
        dialogRate = new Dialog(getActivity());
        dialogUpdate = new Dialog(getActivity());
        dialogUpdate.setContentView(R.layout.dialog_update);
        dialogRate.setContentView(R.layout.dialog_rate);
        dialogRate.setCanceledOnTouchOutside(false);
        dialogFeedback = new Dialog(getActivity());
        dialogRate.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogFeedback.setContentView(R.layout.dialog_feedback);
        dialogFeedback.setCanceledOnTouchOutside(false);
        dialogFeedback.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        rlUpdate = view.findViewById(R.id.rlUpdate);
        rlUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogUpdate.show();

            }
        });
        rlRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogRate.show();
            }
        });
        btnRate = dialogRate.findViewById(R.id.btnRate);
        btnLate = dialogRate.findViewById(R.id.btnLate);
        imCloseFeedBack = dialogFeedback.findViewById(R.id.imCloseFeedback);
        imCloseFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogFeedback.dismiss();
            }
        });
        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogRate.dismiss();
                dialogFeedback.show();
            }
        });
        btnLate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogRate.dismiss();
            }
        });
        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

}
