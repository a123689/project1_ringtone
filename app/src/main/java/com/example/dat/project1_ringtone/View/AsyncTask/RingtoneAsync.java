package com.example.dat.project1_ringtone.View.AsyncTask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dat.project1_ringtone.View.Adapter.AdapterListRingtone;
import com.example.dat.project1_ringtone.Model.Favorite;
import com.example.dat.project1_ringtone.Model.Song;
import com.example.dat.project1_ringtone.R;

import java.util.List;

public  class RingtoneAsync extends AsyncTask<List<Song>, List<Song>,List<Song>> {
    RecyclerView recyclerView;
    AdapterListRingtone adapterListRingtone;
    View view;
    Context context;
    List<Favorite> favoriteList;
    public RingtoneAsync(View view, Context context, List<Favorite> favoriteList){

        this.view = view;
        this.context = context;
        this.favoriteList = favoriteList;
    }

    @Override
    protected List<Song> doInBackground(List<Song>... lists) {

        List<Song> songList  = lists[0];
        return songList;

    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onPostExecute(List<Song> ringtoneList) {
        super.onPostExecute(ringtoneList);
        recyclerView = view.findViewById(R.id.recyclewviewRingtone);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapterListRingtone = new AdapterListRingtone(context,ringtoneList,favoriteList);
        recyclerView.setAdapter(adapterListRingtone);


    }
}
