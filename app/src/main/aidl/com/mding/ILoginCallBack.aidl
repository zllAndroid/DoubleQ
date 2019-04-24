// ILoginCallBack.aidl
package com.mding;
import com.mding.models.LoginModel;

interface ILoginCallBack {
    void readData(in LoginModel mLoginModel);
    void onFailure(String res);
    void onSuccess(String res);
}
