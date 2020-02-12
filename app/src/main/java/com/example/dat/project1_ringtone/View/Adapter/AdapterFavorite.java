package com.example.dat.project1_ringtone.View.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dat.project1_ringtone.Model.Song;
import com.example.dat.project1_ringtone.View.Event.CallBackSetData;
import com.example.dat.project1_ringtone.Helper.Media;
import com.example.dat.project1_ringtone.Model.Favorite;
import com.example.dat.project1_ringtone.Presenter.FavoritePresenterLogic;
import com.example.dat.project1_ringtone.R;
import com.example.dat.project1_ringtone.View.Event.CallbackFavorite;
import com.example.dat.project1_ringtone.View.Interface.ViewDeleteFavorite;

import java.util.List;

public class AdapterFavorite extends RecyclerView.Adapter<AdapterFavorite.Viewholder> implements ViewDeleteFavorite {
    List<Favorite>favoriteList;
    private int selected = 0;
    private int previousSelected = 0;
    boolean check,checkPlay,checkHeart;
    Media media;
    Context context;
    FavoritePresenterLogic favoritePresenterLogic;
    CallBackSetData callBackSetData;
    CallbackFavorite callbackFavorite;
    public AdapterFavorite(List<Favorite> favoriteList, Context context,CallBackSetData callBackSetData,CallbackFavorite callbackFavorite){
        this.favoriteList = favoriteList;
        this.context = context;
        media = new Media(context);
        favoritePresenterLogic = new FavoritePresenterLogic(context,this);
        this.callBackSetData = callBackSetData;
        this.callbackFavorite = callbackFavorite;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_favorite,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder holder, final int position) {

        if(check){
            holder.imPlay.setImageResource(R.drawable.ic_pause_button);

        }
        holder.tvRingName.setText(favoriteList.get(position).getName());
        holder.imPlay.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

                if(position != selected){
                    previousSelected = selected;
                    selected = position;
                    check = true;
                    notifyItemChanged(previousSelected);

                    if(previousSelected != position){
                        checkPlay = false;
                    }

                    if(checkPlay){
                        holder.imPlay.setImageResource(R.drawable.ic_pause_button);
                        media.stop();
                        checkPlay = false;
                    }else {

                        holder.imPlay.setImageResource(R.drawable.ic_play_button);
                        media.setPlayMediaPlayer(favoriteList.get(position).getUri());
                        checkPlay = true;
                    }

                }else {
                    if(checkPlay){
                        holder.imPlay.setImageResource(R.drawable.ic_pause_button);
                        media.stop();
                        checkPlay = false;
                    }else {
                        holder.imPlay.setImageResource(R.drawable.ic_play_button);
                        media.setPlayMediaPlayer(favoriteList.get(position).getUri());
                        checkPlay = true;
                    }
                }

            }
        });

        holder.imHeart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                favoritePresenterLogic.deleteFavorite(favoriteList.get(position).getId());
            }
        });



        holder.rlFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Song song = new Song(favoriteList.get(position).getName(),favoriteList.get(position).getUri());
                song.setPlaying(true);
                song.setHeart(true);
                callbackFavorite.onClick(song);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    @Override
    public void Succcess() {
        callBackSetData.setData();
    }


    public class Viewholder extends RecyclerView.ViewHolder{

        ImageView imPlay, imHeart, imNext;
        TextView tvRingName;
        RelativeLayout rlFavorite;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imPlay = itemView.findViewById(R.id.imPlay);
            imHeart = itemView.findViewById(R.id.imHeart);
            imNext  = itemView.findViewById(R.id.imNext);
            tvRingName = itemView.findViewById(R.id.tvRingName);
            rlFavorite = itemView.findViewById(R.id.rlFavorite);
        }
    }
}
