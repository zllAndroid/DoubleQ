package com.mding.models;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginModel implements Parcelable {
    private String userid;
    private String psw;
    private boolean isLoginSuccess;

    public static Creator<LoginModel> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<LoginModel> CREATOR = new Creator<LoginModel>() {
        @Override
        public LoginModel createFromParcel(Parcel in) {
            return new LoginModel(in);
        }

        @Override
        public LoginModel[] newArray(int size) {
            return new LoginModel[size];
        }
    };

    public void readFromParcel(Parcel in){
        userid = in.readString();
        psw = in.readString();
        isLoginSuccess = in.readByte() != 0;
    }

    //从Parcel中读出之前写进的数据
    protected LoginModel(Parcel in) {
        userid = in.readString();
        psw = in.readString();
        isLoginSuccess = in.readByte() != 0;
    }

    public LoginModel() {

    }

    public boolean isLoginSuccess() {
        return isLoginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
         isLoginSuccess = loginSuccess;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }




    //实现 Parcelable 接口所需重写的方法1，一般不用管
    @Override
    public int describeContents() {
        return 0;
    }

    //实现 Parcelable 接口所需重写的方法2，将对象的每个字段写入到Parcel中
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userid);
        dest.writeString(psw);
        dest.writeByte((byte) (isLoginSuccess ? 1 : 0));
    }



}
