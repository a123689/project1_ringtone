package com.example.dat.project1_ringtone.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.provider.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RingToneHelper  {
    public static void changeRingtone(Context context,Uri uri){

        RingtoneManager.setActualDefaultRingtoneUri(context,
                RingtoneManager.TYPE_RINGTONE,uri);
    }

    public static void changeAlarmtone(Context context,Uri uri){

        RingtoneManager.setActualDefaultRingtoneUri(context,
                RingtoneManager.TYPE_ALARM,uri);

    }

    public static void changeNotifycation(Context context,Uri uri){

        RingtoneManager.setActualDefaultRingtoneUri(context,
                RingtoneManager.TYPE_NOTIFICATION,uri);
    }
}
