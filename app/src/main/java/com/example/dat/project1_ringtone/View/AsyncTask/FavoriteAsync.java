package com.example.dat.project1_ringtone.View.AsyncTask;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dat.project1_ringtone.Model.Song;
import com.example.dat.project1_ringtone.View.Adapter.AdapterFavorite;
import com.example.dat.project1_ringtone.View.Event.CallBackMainFavorite;
import com.example.dat.project1_ringtone.View.Event.CallBackSetData;
import com.example.dat.project1_ringtone.Model.Favorite;
import com.example.dat.project1_ringtone.R;
import com.example.dat.project1_ringtone.View.Event.CallbackFavorite;

import java.util.List;

public class FavoriteAsync extends AsyncTask<List<Favorite>, List<Favorite>,List<Favorite>>  {
    RecyclerView recyclerView;
    AdapterFavorite adapterFavorite;
    View view;
    Context context;
    CallBackSetData callBackSetData;
    CallBackMainFavorite callBackMainFavorite;
    public FavoriteAsync(View view, Context context,CallBackSetData callBackSetData,CallBackMainFavorite callBackMainFavorite){
        this.view = view;
        this.context = context;
        this.callBackSetData = callBackSetData;
        this.callBackMainFavorite = callBackMainFavorite;
    }
    @Override
    protected List<Favorite> doInBackground(List<Favorite>... lists) {
        List<Favorite> favorites  = lists[0];
        return favorites;

    }

    @SuppressLint("WrongConstant")
    @Override
    protected void onPostExecute(List<Favorite> favoriteList) {
        super.onPostExecute(favoriteList);
        recyclerView = view.findViewById(R.id.recycleviewFavorite);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapterFavorite = new AdapterFavorite(favoriteList, context, callBackSetData, new CallbackFavorite() {
            @Override
            public void onClick(Song song) {
                callBackMainFavorite.onClick(song);
            }
        });
        recyclerView.setAdapter(adapterFavorite);
    }
}
