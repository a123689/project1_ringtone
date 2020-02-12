package com.example.dat.project1_ringtone.Presenter;

import android.content.Context;

import com.example.dat.project1_ringtone.Helper.Data;
import com.example.dat.project1_ringtone.View.Interface.ViewListSong;

public class SongPresenterLogic implements SongPresenter{
    ViewListSong viewListSong;
    Context context;
    public SongPresenterLogic(ViewListSong viewListSong, Context context){
        this.context = context;
        this.viewListSong = viewListSong;
    }

    @Override
    public void listSong() {
      viewListSong.listSong(Data.getRingtoneDatabase(context).listRingtone(), Data.getDataFavorite(context).favoriteArrayList());
    }
}
