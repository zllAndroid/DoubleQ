package com.mding.models;

import android.os.Parcel;
import android.os.Parcelable;

public class CusChatData implements Parcelable {
    private String myNumber;

    public CusChatData() {
    }

    public CusChatData(Parcel in) {
        readFromParcel(in);
    }
    public void readFromParcel(Parcel in){
        myNumber = in.readString();
    }



    public static final Creator<CusChatData> CREATOR = new Creator<CusChatData>() {
        @Override
        public CusChatData createFromParcel(Parcel in) {
            return new CusChatData(in);
        }

        @Override
        public CusChatData[] newArray(int size) {
            return new CusChatData[size];
        }
    };




    public String getMyNumber() {
        return myNumber;
    }

    public void setMyNumber(String myNumber) {
        this.myNumber = myNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(myNumber);
    }
}
