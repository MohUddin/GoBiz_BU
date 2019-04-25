package com.example.vaibh.gobiz.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class Factoid implements Parcelable {

    private String fact;
    private int imageResource;

    public Factoid(String fact, int imageResource) {
        this.fact = fact;
        this.imageResource = imageResource;
    }

    public String getFact() {
        return fact;
    }

    public int getImageResource() {
        return imageResource;
    }

    private Factoid(Parcel in) {
        fact = in.readString();
        imageResource = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fact);
        dest.writeInt(imageResource);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Factoid> CREATOR = new Parcelable.Creator<Factoid>() {
        @Override
        public Factoid createFromParcel(Parcel in) {
            return new Factoid(in);
        }

        @Override
        public Factoid[] newArray(int size) {
            return new Factoid[size];
        }
    };
}