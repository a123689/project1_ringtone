package com.example.dat.project1_ringtone.View.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;

import com.example.dat.project1_ringtone.Helper.RingToneHelper;

public class RingReceiver extends BroadcastReceiver {
    Uri uri;
    public RingReceiver(Uri uri){
        this.uri = uri;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equalsIgnoreCase(TelephonyManager.ACTION_PHONE_STATE_CHANGED)) {

            String callState = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if (callState.equals(TelephonyManager.EXTRA_STATE_RINGING)) {

                RingToneHelper.changeRingtone(context,uri);
            }
        }
    }
}
