package com.projects.zll.utilslibrarybyzll.aboutvolley;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

public abstract class VolleyInterface {
	public static Listener<String> listener;
	public static ErrorListener errorListener;

	public VolleyInterface(Listener<String> listener, ErrorListener errorListener) {
		VolleyInterface.listener = listener;
		VolleyInterface.errorListener = errorListener;
	}

	public abstract void onSuccess(String result);

	public abstract void onError(VolleyError result);

	public Listener<String> loadingListener() {
		listener = new Listener<String>() {

			@Override
			public void onResponse(String result) {
				// TODO Auto-generated method stub
				if (!result.contains("()"))
					onSuccess(result);
				else
					onError(null);
			}
		};
		return listener;
	}

	public ErrorListener errorListener() {
		errorListener = new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError result) {
				// TODO Auto-generated method stub
				onError(result);
			}
		};
		return errorListener;
	}
}
