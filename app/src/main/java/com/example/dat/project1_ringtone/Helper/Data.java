package com.example.dat.project1_ringtone.Helper;

import android.content.Context;

import com.example.dat.project1_ringtone.Database.DataFavorite;
import com.example.dat.project1_ringtone.Database.Database;
import com.example.dat.project1_ringtone.Database.RingtoneDatabase;

public class Data {
    public static DataFavorite dataFavorite;
    public static RingtoneDatabase ringtoneDatabase;
    public static DataFavorite getDataFavorite(Context context){
        if(dataFavorite == null){
            dataFavorite = new DataFavorite(context);
        }
        return dataFavorite;
    }
    public static RingtoneDatabase getRingtoneDatabase(Context context){
        if(ringtoneDatabase == null){
            ringtoneDatabase = new RingtoneDatabase(context);
        }
        return ringtoneDatabase;
    }
}
