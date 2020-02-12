package com.example.dat.project1_ringtone.Presenter;

import com.example.dat.project1_ringtone.Model.Favorite;

import java.util.ArrayList;
import java.util.List;

public interface FavoritePresenter {
    void listFavorite();
    void addFavorite(String name,String uri);
    void deleteFavorite(int id);
    void deleteFavoriteByName(String name);

}
