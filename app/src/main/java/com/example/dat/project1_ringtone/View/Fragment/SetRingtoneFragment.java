package com.example.dat.project1_ringtone.View.Fragment;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dat.project1_ringtone.Helper.Common;
import com.example.dat.project1_ringtone.Model.Song;
import com.example.dat.project1_ringtone.Presenter.FavoritePresenterLogic;
import com.example.dat.project1_ringtone.R;
import com.example.dat.project1_ringtone.View.Activity.MainActivity;
import com.example.dat.project1_ringtone.View.Interface.ViewAddFavorite;
import com.example.dat.project1_ringtone.View.Receiver.RingReceiver;
import com.example.dat.project1_ringtone.Helper.RingToneHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class SetRingtoneFragment extends Fragment implements View.OnClickListener, ViewAddFavorite {


    public SetRingtoneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_ringtone, container, false);
    }
    int countPlay,countHeart =0;
    MediaPlayer mediaPlayer;
    Handler handler;
    SeekBar seekBar;
    TextView tvStart,tvEnd,tvSong;
    ImageView imPlay,imHeart,imRingtone,imClose,imAlarm,imSms,imCloseAlarm,imCloseSms,imBack;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("m:ss");
    Dialog dialog,dialogSms,dialogAlarm;
    boolean checkPlay,checkHeart;
    Button btnLater,btnTry,btnLaterAlarm,btnLaterSms,btnTryAlarm,btnTrySms;
    RingReceiver receiver;
    Song song;
    FavoritePresenterLogic favoritePresenterLogic;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favoritePresenterLogic = new FavoritePresenterLogic(getActivity(),this);
        song = getArguments().getParcelable("song");
        findView(view);
        tvSong.setText(song.getName());
        setMediaPlayer(song);
        setTimeend();
        setHeart();


    }

    private void setHeart(){
        if(song.isPlaying()){
            imHeart.setImageResource(R.drawable.ic_favorite);
        }else {
            imHeart.setImageResource(R.drawable.ic_heart);
        }
    }

    private void findView(View view){

        imPlay = view.findViewById(R.id.imPlaySetringtone);
        tvSong = view.findViewById(R.id.tvSetRingName);
        seekBar = view.findViewById(R.id.seekBar);
        tvEnd = view.findViewById(R.id.tvEnd);
        tvStart = view.findViewById(R.id.tvStart);
        imHeart = view.findViewById(R.id.imHeart);
        imRingtone = view.findViewById(R.id.imringTone);
        imBack = view.findViewById(R.id.imBack);

        setRingtoe();
        setDialogAlarm();
        setDialogSms();

        imClose = dialog.findViewById(R.id.imClose);
        btnLater = dialog.findViewById(R.id.btnLater);
        btnTry = dialog.findViewById(R.id.btnTry);
        imAlarm = view.findViewById(R.id.imAlarm);
        imSms = view.findViewById(R.id.imSms);
        imCloseAlarm = dialogAlarm.findViewById(R.id.imCloseAlarm);
        imCloseSms = dialogSms.findViewById(R.id.imCloseSms);

        btnLaterAlarm = dialogAlarm.findViewById(R.id.btnLaterAlarm);
        btnTryAlarm = dialogAlarm.findViewById(R.id.btnTryAlarm);
        btnLaterSms = dialogSms.findViewById(R.id.btnLaterSms);
        btnTrySms = dialogSms.findViewById(R.id.btnTrySms);


        imClose.setOnClickListener(this);
        imRingtone.setOnClickListener(this);
        imHeart.setOnClickListener(this);
        imPlay.setOnClickListener(this);
        btnTry.setOnClickListener(this);
        btnLater.setOnClickListener(this);
        imAlarm.setOnClickListener(this);
        imSms.setOnClickListener(this);
        imBack.setOnClickListener(this);

        imCloseAlarm.setOnClickListener(this);
        imCloseSms.setOnClickListener(this);
        btnLaterSms.setOnClickListener(this);
        btnTryAlarm.setOnClickListener(this);
        btnLaterAlarm.setOnClickListener(this);
        btnTrySms.setOnClickListener(this);

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
            @Override
            public void onCancel(DialogInterface dialog)
            {
                mediaPlayer.pause();
                imPlay.setImageResource(R.drawable.ic_pause_button);
                checkPlay = false;
            }
        });

        dialogSms.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
            @Override
            public void onCancel(DialogInterface dialog)
            {
                imPlay.setImageResource(R.drawable.ic_pause_button);
                mediaPlayer.pause();
                checkPlay = false;
            }
        });

        dialogAlarm.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
            @Override
            public void onCancel(DialogInterface dialog)
            {
                imPlay.setImageResource(R.drawable.ic_pause_button);
                mediaPlayer.pause();
                checkPlay = false;
            }
        });

    }

    private void setDialogAlarm(){
        dialogAlarm = new  Dialog(getActivity());
        dialogAlarm.setContentView(R.layout.alarm_dialog);
        dialogAlarm.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogAlarm.setCanceledOnTouchOutside(false);
    }

    private void setDialogSms(){
        dialogSms = new Dialog(getActivity());
        dialogSms.setContentView(R.layout.dialog_sms);
        dialogSms.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogSms.setCanceledOnTouchOutside(false);
    }

    private void setRingtoe(){
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.ringtone_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
    }
    private void setMediaPlayer(Song song){
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(getActivity(),Uri.parse(song.getUrl()));
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private  void setTimeend(){


        tvEnd.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }

    public void upDatetime(){
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                tvStart.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this,1);
            }

        },1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(handler != null){
            handler.removeCallbacksAndMessages(null);
        }

        mediaPlayer.stop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mediaPlayer.pause();
        imPlay.setImageResource(R.drawable.ic_pause_button);
        Common.checkPlay = true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imClose:{
                dialog.dismiss();
                mediaPlayer.pause();
                break;
            }
            case R.id.imringTone:{

                dialog.show();
                mediaPlayer.start();
                break;
            }
            case R.id.imHeart:{
                if(song.isPlaying()){
                    imHeart.setImageResource(R.drawable.ic_heart);
                    favoritePresenterLogic.deleteFavoriteByName(song.getName().trim());
                }else {
                    if(!checkHeart){
                        imHeart.setImageResource(R.drawable.ic_favorite);
                        checkHeart = true;
                        favoritePresenterLogic.addFavorite(song.getName(),song.getUrl());


                    }else {
                        imHeart.setImageResource(R.drawable.ic_heart);
                        checkHeart = false;
                        favoritePresenterLogic.deleteFavoriteByName(song.getName().trim());
                    }
                }

                break;
            }
            case R.id.imPlaySetringtone:{
                upDatetime();
                if(Common.checkPlay){
                    mediaPlayer.start();
                    upDatetime();
                    imPlay.setImageResource(R.drawable.ic_play_button);
                    Common.checkPlay = false;
                }else {
                    if(!checkPlay){

                        mediaPlayer.start();
                        imPlay.setImageResource(R.drawable.ic_play_button);
                        checkPlay = true;

                    }else {

                        mediaPlayer.pause();
                        imPlay.setImageResource(R.drawable.ic_pause_button);
                        checkPlay = false;
                    }

                }
                break;
            }
            case R.id.btnLater:{

                mediaPlayer.pause();
                dialog.dismiss();
                break;
            }
            case R.id.btnTry:{
                mediaPlayer.pause();
                if(song !=null){
                    receiver = new RingReceiver(Uri.parse(song.getUrl()));
                }

                IntentFilter filter = new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
                getActivity().registerReceiver(receiver, filter);
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                break;
            }
            case R.id.imAlarm:{

                dialogAlarm.show();
                mediaPlayer.start();
                break;
            }
            case R.id.imSms:{
                dialogSms.show();
                mediaPlayer.start();
                break;
            }
            case R.id.imCloseAlarm:{
                dialogAlarm.dismiss();
                mediaPlayer.pause();
                break;
            }
            case R.id.imCloseSms:{
                dialogSms.dismiss();
                mediaPlayer.pause();
                break;
            }
            case R.id.btnLaterAlarm:{
                dialogAlarm.dismiss();
                mediaPlayer.pause();
                break;
            }
            case R.id.btnLaterSms:{
                dialogSms.dismiss();
                mediaPlayer.pause();
                break;
            }
            case R.id.btnTryAlarm:{
                RingToneHelper.changeAlarmtone(getActivity(),Uri.parse(song.getUrl()));
                dialogAlarm.dismiss();
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btnTrySms:{
                RingToneHelper.changeNotifycation(getActivity(),Uri.parse(song.getUrl()));
                dialogSms.dismiss();
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.imBack:{

                getActivity().onBackPressed();

                break;
            }

        }
    }


    @Override
    public void Succcess() {

    }
}
