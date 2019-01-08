package com.projects.zll.utilslibrarybyzll.aboutvolley;

import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

import static com.projects.zll.utilslibrarybyzll.aboutvolley.VolleyInterface.errorListener;
import static com.projects.zll.utilslibrarybyzll.aboutvolley.VolleyInterface.listener;


public abstract class VolleyResponse {
	public static    Response.Listener<String> res_istener;
	public static   Response.ErrorListener res_errorListener;

	public VolleyResponse(Response.Listener<String> res_istener, Response.ErrorListener res_errorListener) {
		 this.res_istener = res_istener;
		 this.res_errorListener = res_errorListener;
	}

	public abstract void onResSuccess(String result);

	public abstract void onResError(VolleyError result);

	public  Listener<String> resListener() {
		listener = new Listener<String>() {

			@Override
			public void onResponse(String result) {
				// TODO Auto-generated method stub
				onResSuccess(result);
			}
		};
		return listener;
	}

	public ErrorListener reserrorListener() {
		errorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError result) {
				// TODO Auto-generated method stub
				onResError(result);
			}
		};
		return errorListener;
	}

}
