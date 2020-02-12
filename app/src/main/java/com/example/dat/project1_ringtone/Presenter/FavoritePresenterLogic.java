package com.example.dat.project1_ringtone.Presenter;

import android.content.Context;

import com.example.dat.project1_ringtone.Database.DataFavorite;
import com.example.dat.project1_ringtone.View.Event.CallBackSetData;
import com.example.dat.project1_ringtone.Model.Favorite;
import com.example.dat.project1_ringtone.View.Interface.ViewAddFavorite;
import com.example.dat.project1_ringtone.View.Interface.ViewDeleteFavorite;
import com.example.dat.project1_ringtone.View.Interface.ViewListFavorite;

public class FavoritePresenterLogic implements FavoritePresenter {
    DataFavorite database;
    Context context;
    ViewListFavorite viewListFavorite;
    ViewAddFavorite viewAddFavorite;
    ViewDeleteFavorite viewDeleteFavorite;
    CallBackSetData callBackSetData;
    public FavoritePresenterLogic(Context context,ViewListFavorite viewListFavorite,CallBackSetData callBackSetData){
        this.context = context;
        this.viewListFavorite = viewListFavorite;
        this.callBackSetData = callBackSetData;
    }
    public FavoritePresenterLogic(Context context,ViewAddFavorite viewAddFavorite){
        this.context = context;
        this.viewAddFavorite = viewAddFavorite;
    }
    public FavoritePresenterLogic(Context context,ViewDeleteFavorite viewDeleteFavorite){
        this.context = context;
        this.viewDeleteFavorite = viewDeleteFavorite;
    }
    public FavoritePresenterLogic(Context context){
        this.context = context;
    }
    @Override
    public void listFavorite() {
        database = new DataFavorite(context);
        viewListFavorite.listFavorite(database.favoriteArrayList());
    }

    @Override
    public void addFavorite(String name, String uri) {
        database = new DataFavorite(context);
        Favorite favorite = new Favorite(name,uri);
        if(database.addFavorite(favorite)){
            viewAddFavorite.Succcess();
        }
    }

    @Override
    public void deleteFavorite(int id) {
        database = new DataFavorite(context);
        if(database.deleteFavorite(id)){
            viewDeleteFavorite.Succcess();
        }
    }

    @Override
    public void deleteFavoriteByName(String name) {
        database = new DataFavorite(context);
        database.deleteFavoriteByName(name);
    }


}
