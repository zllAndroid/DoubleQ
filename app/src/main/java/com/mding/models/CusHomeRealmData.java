package com.mding.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CusHomeRealmData implements Parcelable {

    private String name;
    private List<CusChatData> chatMsgs;


    public static final Creator<CusHomeRealmData> CREATOR = new Creator<CusHomeRealmData>() {
        @Override
        public CusHomeRealmData createFromParcel(Parcel in) {
            return new CusHomeRealmData(in);
        }

        @Override
        public CusHomeRealmData[] newArray(int size) {
            return new CusHomeRealmData[size];
        }
    };
    public CusHomeRealmData() {
    }

    public CusHomeRealmData(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeList(chatMsgs);
    }

    public void readFromParcel(Parcel in){
        name = in.readString();
//        chatMsgs= in.createTypedArrayList(CusChatData.CREATOR);
//        chatMsgs= in.readArrayList(CusChatData.class.getClassLoader());
        chatMsgs=in.readArrayList(CusChatData.class.getClassLoader());
//        in.readList(chatMsgs,CusChatData.class.getClassLoader());
//        this.songs = in.createTypedArrayList(CusChatData.CREATOR);
    }



    @Override
    public int describeContents() {
        return 0;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CusChatData> getChatMsgs() {
        return chatMsgs;
    }

    public void setChatMsgs(List<CusChatData> chatMsgs) {
        this.chatMsgs = chatMsgs;
    }
}
