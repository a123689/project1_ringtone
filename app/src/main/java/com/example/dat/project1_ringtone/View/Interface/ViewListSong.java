package com.example.dat.project1_ringtone.View.Interface;

import com.example.dat.project1_ringtone.Model.Favorite;
import com.example.dat.project1_ringtone.Model.Song;

import java.util.List;

public interface ViewListSong {
    void listSong(List<Song> list, List<Favorite> favoriteList);
}
