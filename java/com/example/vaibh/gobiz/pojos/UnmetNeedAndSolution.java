package com.example.vaibh.gobiz.pojos;

import android.os.Parcel;
import android.os.Parcelable;

public class UnmetNeedAndSolution implements Parcelable {

    private String unmetNeed;
    private String solution;
    private boolean isOpen;

    public UnmetNeedAndSolution(String unmetNeed, String solution) {
        this.unmetNeed = unmetNeed;
        this.solution = solution;
    }

    public String getUnmetNeed() {
        return unmetNeed;
    }

    public String getSolution() {
        return solution;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    protected UnmetNeedAndSolution(Parcel in) {
        unmetNeed = in.readString();
        solution = in.readString();
        isOpen = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(unmetNeed);
        dest.writeString(solution);
        dest.writeByte((byte) (isOpen ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UnmetNeedAndSolution> CREATOR = new Parcelable.Creator<UnmetNeedAndSolution>() {
        @Override
        public UnmetNeedAndSolution createFromParcel(Parcel in) {
            return new UnmetNeedAndSolution(in);
        }

        @Override
        public UnmetNeedAndSolution[] newArray(int size) {
            return new UnmetNeedAndSolution[size];
        }
    };
}