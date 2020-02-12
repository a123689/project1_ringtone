package com.example.dat.project1_ringtone.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dat.project1_ringtone.Model.Favorite;

import java.util.ArrayList;

public class DataFavorite {
    public static final String TABLE_NAME     = "FAVORITE";
    public static final String ID     = "ID";
    public static final String NAME       = "NAME";
    public static final String URI        = "URI";



    public static final String CREATETABLE = "CREATE TABLE "+TABLE_NAME+" ( "+
            ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            NAME+" TEXT, "+
            URI+" TEXT)";
    Database database;
    public DataFavorite(Context context){
        database = new Database(context);
    }
    public boolean addFavorite(Favorite favorite){
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put(NAME,favorite.getName());
        contentValues.put(URI,favorite.getUri());

        long check = db.insert(TABLE_NAME,null,contentValues);
        if(check != -1){
            return  true;
        }
        return false;
    }

    public boolean deleteFavorite (int id){
        SQLiteDatabase db = database.getWritableDatabase();
        int count  = db.delete(TABLE_NAME,ID+" = ?",new String[]{String.valueOf(id)});
        if(count > 0){
            return true;
        }
        return false;
    }

    public boolean deleteFavoriteByName (String name){
        SQLiteDatabase db = database.getWritableDatabase();
        int count  = db.delete(TABLE_NAME,NAME+" = ?",new String[]{name});
        if(count > 0){
            return true;
        }
        return false;
    }


    public ArrayList<Favorite> favoriteArrayList(){
        ArrayList<Favorite> favorites = new ArrayList<>();
        SQLiteDatabase db = database.getWritableDatabase();
        String truyvan    = "SELECT * FROM "+TABLE_NAME;
        Cursor cursor     = db.rawQuery(truyvan,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id     = cursor.getInt(cursor.getColumnIndex(ID));
            String name  = cursor.getString(cursor.getColumnIndex(NAME));
            String uri = cursor.getString(cursor.getColumnIndex(URI));

            Favorite favorite = new Favorite(id,name,uri);
            favorites.add(favorite);
            cursor.moveToNext();
        }
        return favorites;
    }
}
