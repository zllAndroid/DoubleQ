// ILoginRequst.aidl
package com.mding;
import com.mding.ILoginCallBack;


interface ILoginRequst {
    void register(ILoginCallBack mILoginCallBack);
    void loginRequest(String loginUrl);
}
