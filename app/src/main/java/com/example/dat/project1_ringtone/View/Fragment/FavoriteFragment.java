package com.example.dat.project1_ringtone.View.Fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dat.project1_ringtone.Model.Song;
import com.example.dat.project1_ringtone.View.AsyncTask.FavoriteAsync;
import com.example.dat.project1_ringtone.View.Event.CallBackMainFavorite;
import com.example.dat.project1_ringtone.View.Event.CallBackSetData;
import com.example.dat.project1_ringtone.Helper.Media;
import com.example.dat.project1_ringtone.Model.Favorite;
import com.example.dat.project1_ringtone.Presenter.FavoritePresenterLogic;
import com.example.dat.project1_ringtone.R;
import com.example.dat.project1_ringtone.View.Event.CallbackMain2;
import com.example.dat.project1_ringtone.View.Interface.ViewListFavorite;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements ViewListFavorite, CallBackSetData {


    CallbackMain2 callbackMain2;
    public FavoriteFragment(CallbackMain2 callbackMain2) {
        this.callbackMain2 = callbackMain2;
    }


    ImageView imBack;
    View view;
    FavoritePresenterLogic favoritePresenterLogic;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    RecyclerView recyclerView;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {


                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        34);


            }
        } else {


        }
        this.view = view;
        imBack = view.findViewById(R.id.imBackFavorite);
        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainFragment.bottomNavigationView.setSelectedItemId(R.id.CategoryFragment);
            }
        });
        favoritePresenterLogic = new FavoritePresenterLogic(getActivity(),this,this);
        favoritePresenterLogic.listFavorite();


    }


    @Override
    public void listFavorite(List<Favorite> favoriteList) {
        new FavoriteAsync(view, getContext(), this, new CallBackMainFavorite() {
            @Override
            public void onClick(Song song) {
                callbackMain2.onClick(song);
            }
        }).execute(favoriteList);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(favoritePresenterLogic != null){
            favoritePresenterLogic.listFavorite();
        }
    }



    @Override
    public void onPause() {
        super.onPause();
        Media.stop();
    }

    @Override
    public void setData() {
        favoritePresenterLogic.listFavorite();
    }
}
