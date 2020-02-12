package com.example.dat.project1_ringtone.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Favorite implements Parcelable {
    private int Id;
    private String Name;
    private String Uri;

    public Favorite(int id, String name, String uri) {
        Id = id;
        Name = name;
        Uri = uri;
    }

    public Favorite(String name, String uri) {
        Name = name;
        Uri = uri;
    }

    protected Favorite(Parcel in) {
        Id = in.readInt();
        Name = in.readString();
        Uri = in.readString();
    }

    public static final Creator<Favorite> CREATOR = new Creator<Favorite>() {
        @Override
        public Favorite createFromParcel(Parcel in) {
            return new Favorite(in);
        }

        @Override
        public Favorite[] newArray(int size) {
            return new Favorite[size];
        }
    };

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUri() {
        return Uri;
    }

    public void setUri(String uri) {
        Uri = uri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Id);
        parcel.writeString(Name);
        parcel.writeString(Uri);
    }
}
