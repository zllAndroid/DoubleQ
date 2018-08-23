package com.doubleq.xm6leefunz.about_base;


import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View;

import com.doubleq.xm6leefunz.R;
import com.projects.zll.utilslibrarybyzll.aboutsystem.AppManager;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;

/**
 * 公共基类
 *   zll
 * @Time 2017-11-01
 */
public class BaseFragment extends Fragment  {
	public Handler mHandlers = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg != null)
				onFragmentHandleMessage(msg);
		}
	};
	public ACache mFragCache;
    String simpleName;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mFragCache = ACache.get(getActivity());
        simpleName = getClass().getSimpleName();
    }
	@Override
	public void onStart() {
		super.onStart();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
				View viewById = AppManager.getAppManager().currentActivity().findViewById(R.id.main_view_top);
				if (viewById!=null)
				viewById.setVisibility(View.VISIBLE);
//				else
//					viewById.setVisibility(View.GONE);
		}
	}
    protected String GetsimpleName() {
        return getClass().getSimpleName().toString();
    }
	protected boolean FragUseCaChe() {
		return false;
	}
	protected void onFragmentHandleMessage(Message msg) {
	}
//	@Override
//	public void onNetSuccess(String msg) {
//		Message message = new Message();
//		if (!StrUtils.isEmpty(msg)&&!msg.equals(AppAllKey.NO_RESULT)) {
//			message.what = AppAllKey.HANDLE_MSG_SUCCESS;
//			if (FragUseCaChe())
//				mFragCache.put(simpleName, msg);
//		}
//		else
//			message.what = AppAllKey.HANDLE_MSG_FAILED;
//		message.obj = msg;
//		mHandlers.sendMessage(message);
//	}
}
