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

import com.example.dat.project1_ringtone.Helper.Media;
import com.example.dat.project1_ringtone.Model.Favorite;
import com.example.dat.project1_ringtone.Model.Song;
import com.example.dat.project1_ringtone.Presenter.FavoritePresenterLogic;
import com.example.dat.project1_ringtone.R;
import com.example.dat.project1_ringtone.View.Interface.ViewAddFavorite;

import java.util.List;

public class AdapterListRingtone extends RecyclerView.Adapter<AdapterListRingtone.Viewholer> implements ViewAddFavorite {
    Context context;
    List<Song> ringtoneList;
    private int selected = 0;
    private int previousSelected = 0;
    boolean check,checkPlay,checkHeart,checkFavorite;
    Media media;
    FavoritePresenterLogic favoritePresenterLogic;
    List<Favorite> favoriteList;

    public AdapterListRingtone(Context context,List<Song> ringtoneList,List<Favorite> favoriteList){
        this.context = context;
        this.ringtoneList = ringtoneList;
        media = new Media(context);
        favoritePresenterLogic = new FavoritePresenterLogic(context,this);
        this.favoriteList = favoriteList;
    }

    @NonNull
    @Override
    public Viewholer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_ringtone,parent,false);
        return new Viewholer(view);
    }



    @Override
    public void onBindViewHolder(@NonNull final Viewholer holder, final int position) {

        if(check){
            holder.imPlay.setImageResource(R.drawable.ic_pause_button);

        }

        for(Favorite favorite : favoriteList){
            if(ringtoneList.get(position).getName().trim().equals(favorite.getName())){

                ringtoneList.get(position).setPlaying(true);
            }
        }

        if(ringtoneList.get(position).isPlaying()){
            holder.imHeart.setImageResource(R.drawable.ic_favorite);
        }else {
            holder.imHeart.setImageResource(R.drawable.ic_heart);
        }
        holder.tvRingName.setText(ringtoneList.get(position).getName());
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
                        media.setPlayMediaPlayer(ringtoneList.get(position).getUrl());
                        checkPlay = true;
                    }

                }else {
                    if(checkPlay){

                        holder.imPlay.setImageResource(R.drawable.ic_pause_button);
                        media.stop();
                        checkPlay = false;

                    }else {
                        holder.imPlay.setImageResource(R.drawable.ic_play_button);
                        media.setPlayMediaPlayer(ringtoneList.get(position).getUrl());
                        checkPlay = true;
                    }
                }

            }
        });

        holder.imHeart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if(ringtoneList.get(position).isPlaying()){
                    holder.imHeart.setImageResource(R.drawable.ic_heart);
                    ringtoneList.get(position).setPlaying(false);
                    favoritePresenterLogic.deleteFavoriteByName(ringtoneList.get(position).getName().trim());
                }else {
                    if(!checkHeart){
                        holder.imHeart.setImageResource(R.drawable.ic_favorite);
                        favoritePresenterLogic.addFavorite(ringtoneList.get(position).getName(),ringtoneList.get(position).getUrl());
                        checkHeart = true;
                    }else {
                        holder.imHeart.setImageResource(R.drawable.ic_heart);
                        checkHeart = false;
                        favoritePresenterLogic.deleteFavoriteByName(ringtoneList.get(position).getName().trim());
                    }
                }



            }
        });



        holder.rlRingtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Song song = ringtoneList.get(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable("song",  song);
                Navigation.findNavController(view).navigate(R.id.action_listRingtoneFragment_to_setRingtoneFragment, bundle);
            }
        });
    }



    @Override
    public int getItemCount() {
        return ringtoneList.size();
    }

    @Override
    public void Succcess() {

    }

    public class Viewholer extends RecyclerView.ViewHolder{

        ImageView imPlay, imHeart, imNext;
        TextView tvRingName;
        RelativeLayout rlRingtone;
        public Viewholer(@NonNull View itemView) {
            super(itemView);
            imPlay = itemView.findViewById(R.id.imPlay);
            imHeart = itemView.findViewById(R.id.imHeart);
            imNext  = itemView.findViewById(R.id.imNext);
            tvRingName = itemView.findViewById(R.id.tvRingName);
            rlRingtone = itemView.findViewById(R.id.rlRingtone);
        }
    }


}
