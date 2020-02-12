package com.example.dat.project1_ringtone.Helper;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

public class Media {
    public static MediaPlayer mediaPlayer;
    Context context;
    public Media(Context context){
        this.context = context;
    }
    public void setPlayMediaPlayer(String path){
        if (mediaPlayer == null){

            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(context, Uri.parse(path));
                mediaPlayer.prepare();
                mediaPlayer.start();


            } catch (IOException e) {
                e.printStackTrace();

            }
            return;
        }
        stop();
        setPlayMediaPlayer(path);
    }


    public static void stop(){
        if (mediaPlayer == null){
            return;
        }

        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    public static void pause(){
        if (mediaPlayer == null){
            return;
        }
        mediaPlayer.pause();
    }

}
