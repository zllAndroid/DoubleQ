package com.projects.zll.utilslibrarybyzll.aboutvolley;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

/**
 *
 */
public class VolleyHelper {
    private static VolleyHelper instance;
    private static Context mContext;
    private static RequestQueue mRequestQueue;
    private static final String TAG = "VolleyHelper";

    //单例模式
    public static VolleyHelper getInstance(Context context){
        if(instance == null){
            if (mContext == null){
                mContext = context.getApplicationContext();
            }
            mRequestQueue = getRequestQueue(mContext);
            instance = new VolleyHelper();
        }
        return instance;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        VolleyLog.d("添加请求到请求队列: %s", req.getUrl());


        mRequestQueue.add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        // set the default tag if tag is empty
        req.setTag(TAG);
        mRequestQueue.add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
    public static RequestQueue getRequestQueue(Context context) {
        if (mRequestQueue == null) {
            mRequestQueue = Volley
                    .newRequestQueue(context);
        }
        return mRequestQueue;
    }
}
