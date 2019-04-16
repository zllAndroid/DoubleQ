/*
 *
 *  * Copyright (C) 2018 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.mding.chatfeng.main_code.ui.about_load;

import android.content.Context;

public class LoadPresenter implements LoadInteractor.OnLoadFinishedListener,LoadInteractor.OnSqlListener {

    private LoadView loginView;
    private LoadInteractor loginInteractor;
    Context mContext;
    LoadPresenter( Context mContext,LoadView loginView, LoadInteractor loginInteractor) {
        this.mContext = mContext;
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }
    public void loadData() {
        if (loginView != null) {
            loginView.showProgress();
        }
        loginInteractor.linkManRequest( this);
    }
    public void onDestroy() {
        loginView = null;
    }
    @Override
    public void onError(String error) {
        if (loginView != null) {
            loginView.hideProgress();
        }
    }
//请求成功后存储数据库
    @Override
    public void onSuccess(String msg) {
        loginInteractor.setDataToRealm(mContext,msg,this);
    }
    @Override
    public void onSqlError() {
    }
    @Override
    public void onSqlSuccess() {
        if (loginView != null) {
            loginView.navigateToHome();
        }
    }
}
