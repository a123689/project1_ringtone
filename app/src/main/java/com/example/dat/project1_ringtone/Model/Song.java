package com.example.dat.project1_ringtone.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;


public class Song implements Parcelable {
    private String name;
    private String url;
    private boolean isPlaying;
    private boolean isHeart;

    public Song(String name, String url) {
        this.name = name;
        this.url = url;
    }

    protected Song(Parcel in) {
        name = in.readString();
        url = in.readString();
        isPlaying = in.readByte() != 0;
        isHeart = in.readByte() != 0;
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    public boolean isHeart() {
        return isHeart;
    }

    public void setHeart(boolean heart) {
        isHeart = heart;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(url);
        parcel.writeByte((byte) (isPlaying ? 1 : 0));
        parcel.writeByte((byte) (isHeart ? 1 : 0));
    }
}
