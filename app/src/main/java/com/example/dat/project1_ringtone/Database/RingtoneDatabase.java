package com.example.dat.project1_ringtone.Database;

import android.content.Context;
import android.database.Cursor;
import android.media.RingtoneManager;

import com.example.dat.project1_ringtone.Model.Song;

import java.util.ArrayList;
import java.util.List;

public class RingtoneDatabase {
    Context context;
    public RingtoneDatabase(Context context){
        this.context = context;
    }
    public List<Song> listRingtone(){
        List<Song> songList = new ArrayList<>();
        RingtoneManager manager = new RingtoneManager(context);
        manager.setType(RingtoneManager.TYPE_ALL);
        Cursor cursor = manager.getCursor();

        while (cursor.moveToNext()) {
            String notificationTitle = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX);
            String notificationUri = cursor.getString(RingtoneManager.URI_COLUMN_INDEX) + "/" + cursor.getString(RingtoneManager.ID_COLUMN_INDEX);
            songList.add(new Song(notificationTitle,notificationUri));
        }
        return songList;
    }
}
