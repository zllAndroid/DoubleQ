package com.mding.models;


import android.os.Parcel;
import android.os.Parcelable;

public class ChatModel implements Parcelable {
    private String messege;
    private boolean isStatus;
    private String bigHeadImg;
    private String smallHeadImg;

    public void readFromParcel(Parcel in){
        messege = in.readString();
        isStatus = in.readByte() != 0;
        bigHeadImg = in.readString();
        smallHeadImg = in.readString();
    }

    protected ChatModel(Parcel in) {
        messege = in.readString();
        isStatus = in.readByte() != 0;
        bigHeadImg = in.readString();
        smallHeadImg = in.readString();
    }
    public ChatModel() {
    }
    public static final Creator<ChatModel> CREATOR = new Creator<ChatModel>() {
        @Override
        public ChatModel createFromParcel(Parcel in) {
            return new ChatModel(in);
        }

        @Override
        public ChatModel[] newArray(int size) {
            return new ChatModel[size];
        }
    };

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

    public boolean isStatus() {
        return isStatus;
    }

    public void setStatus(boolean status) {
        isStatus = status;
    }

    public String getBigHeadImg() {
        return bigHeadImg;
    }

    public void setBigHeadImg(String bigHeadImg) {
        this.bigHeadImg = bigHeadImg;
    }

    public String getSmallHeadImg() {
        return smallHeadImg;
    }

    public void setSmallHeadImg(String smallHeadImg) {
        this.smallHeadImg = smallHeadImg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(messege);
        dest.writeByte((byte) (isStatus ? 1 : 0));
        dest.writeString(bigHeadImg);
        dest.writeString(smallHeadImg);
    }
}
