package com.example.dat.project1_ringtone.View.Fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.dat.project1_ringtone.Helper.Common;
import com.example.dat.project1_ringtone.Model.Song;
import com.example.dat.project1_ringtone.View.Event.CallbackClickMain;
import com.example.dat.project1_ringtone.R;
import com.example.dat.project1_ringtone.View.Event.CallbackMain2;
import com.google.android.material.bottomnavigation.BottomNavigationView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener, CallbackClickMain, CallbackMain2 {


    public MainFragment() {
        // Required empty public constructor
    }

    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
    public static BottomNavigationView bottomNavigationView;
    RelativeLayout relativeLayout;
    final int  MY_PERMISSIONS_REQUEST_READ_CONTACTS = 0;
    ImageView imSetting;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("");


        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.SET_ALARM)
                != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.SET_ALARM)) {

            } else {

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.SET_ALARM},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);


            }
        } else {


        }
        v = view;
        relativeLayout = view.findViewById(R.id.reative);

        fragmentManager = getActivity().getSupportFragmentManager();
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.CategoryFragment);
        imSetting = view.findViewById(R.id.imSetting);

        imSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_settingFragment);
            }
        });



    }

    FragmentManager fragmentManager;
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.CategoryFragment:{
                relativeLayout.setVisibility(View.VISIBLE);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                CategoryFragment categoryFragment       = new CategoryFragment(this);
                fragmentTransaction.replace(R.id.frame,categoryFragment,"fragmentcategory");
                fragmentTransaction.commit();

                break;
            }
            case R.id.FavoriteFragment:{
                relativeLayout.setVisibility(View.GONE);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FavoriteFragment favoriteFragment       = new FavoriteFragment(this);
                fragmentTransaction.replace(R.id.frame,favoriteFragment,"fragmentfavorite");
                fragmentTransaction.commit();
                break;
            }
        }
        return true;
    }



    @Override
    public void onclickmain() {
        final NavController navController = Navigation.findNavController(v);
        navController.navigate(R.id.action_mainFragment_to_listRingtoneFragment);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(Song song) {
        Common.checkscreen = true;
        Bundle bundle = new Bundle();
        bundle.putParcelable("song",  song);
        Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_setRingtoneFragment, bundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(Common.checkscreen){
            Common.checkscreen = false;
            bottomNavigationView.setSelectedItemId(R.id.FavoriteFragment);
        }

    }
}
