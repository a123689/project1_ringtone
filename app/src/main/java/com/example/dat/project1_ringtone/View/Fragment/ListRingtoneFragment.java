package com.example.dat.project1_ringtone.View.Fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dat.project1_ringtone.View.AsyncTask.RingtoneAsync;
import com.example.dat.project1_ringtone.Helper.Media;
import com.example.dat.project1_ringtone.Model.Favorite;
import com.example.dat.project1_ringtone.Model.Song;
import com.example.dat.project1_ringtone.Presenter.SongPresenterLogic;
import com.example.dat.project1_ringtone.R;
import com.example.dat.project1_ringtone.View.Interface.ViewListSong;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListRingtoneFragment extends Fragment implements ViewListSong {

    private ArrayList<Song> arraySong = new ArrayList<>();

    public ListRingtoneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_ringtone, container, false);
    }



    ImageView imBack;
    View view;
    SongPresenterLogic songPresenterLogic;
    @SuppressLint("WrongConstant")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        imBack = view.findViewById(R.id.imBackCategory);
        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        songPresenterLogic = new SongPresenterLogic(this,getActivity());
        songPresenterLogic.listSong();

    }

    @Override
    public void onResume() {
        super.onResume();
        if(songPresenterLogic != null){
            songPresenterLogic.listSong();
        }
    }

    @Override
    public void listSong(List<Song> list, List<Favorite> favoriteList) {
        new RingtoneAsync(view,getActivity(),favoriteList).execute(list);
    }

    @Override
    public void onPause() {
        super.onPause();
        Media.stop();
    }


}
