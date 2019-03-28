package com.mding.chatfeng.about_base;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mding.chatfeng.R;
import com.mding.chatfeng.about_base.web_base.MessageEvent;
import com.mding.chatfeng.about_custom.about_top_bar.FragmentTopBarLayout;
import com.mding.chatfeng.about_utils.HelpUtils;
import com.mding.chatfeng.about_utils.IntentUtils;
import com.mding.chatfeng.main_code.mains.LoadDataActivity;
import com.mding.chatfeng.main_code.mains.top_pop.ConfirmPopWindow;
import com.projects.zll.utilslibrarybyzll.about_key.AppAllKey;
import com.projects.zll.utilslibrarybyzll.aboututils.ACache;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

		EventBus.getDefault().register(this);


//		initTopBarEvent();
	}

	View view =null;
	boolean mIsPrepare = false;		//视图还没准备好
	boolean mIsVisible= false;		//不可见
	boolean mIsFirstLoad = true;	//第一次加载
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (view == null)
			view = inflater.inflate(setFragmentLayout(), container, false);
		initBaseUI(view);
		initTopBarEvent();
		return view;
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mIsPrepare = true;
		lazyLoad();
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			mIsVisible = true;
			lazyLoad();
		} else {
			mIsVisible = false;
		}
	}
	private void lazyLoad() {
		//这里进行三个条件的判断，如果有一个不满足，都将不进行加载
		if (!mIsPrepare || !mIsVisible||!mIsFirstLoad) {
			return;
		}
		loadData();
		//数据加载完毕,恢复标记,防止重复加载
		mIsFirstLoad = false;
	}

	private void loadData() {
		//这里进行网络请求和数据装载
	}
	protected void initBaseUI(View view) {

	}
	ConfirmPopWindow confirmPopWindow=null;
	private void initTopBarEvent() {
		FragmentTopBarLayout mTopBar = view.findViewById(R.id.fg_top_bar);
		mTopBar.setTitle(setFragmentTital());
		mTopBar.setOnRightClick(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				if (confirmPopWindow==null)
					confirmPopWindow = new ConfirmPopWindow(getActivity());
				confirmPopWindow.showAtBottom(view.findViewById(R.id.include_frag_img_add));
			}
		});
		mTopBar.setOnSearchClick(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				searchClickEvent();
			}
		});

		mTopBar.setTopLinBackground(setTopBarBackground());
	}

	protected View getTopBarView() {
		return view;
	}
	protected int setTopBarBackground() {
		return 0;
	}
	protected int setFragmentLayout() {
		return R.layout.fragment_msg;
	}

	protected void searchClickEvent() {
		IntentUtils.JumpTo(LoadDataActivity.class);
	}

	protected String setFragmentTital() {
		return "123";
	}

	protected void sendWeb(String text) {
		MyApplication.getmConnectManager().sendText(text);
	}
	@Override
	public void onStart() {
		super.onStart();

//		BaseFragment bs=new MsgFragment();
//		bs.
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//				View viewById = getActivity().findViewById(R.id.main_view_top);
//			// 设置状态栏高度
//			int statusBarHeight = WindowBugDeal.getStatusBarHeight(getActivity());
//			//这里我用RelativeLayout布局为列，其他布局设置方法一样，只需改变布局名就行
//			LinearLayout.LayoutParams layout=(LinearLayout.LayoutParams)viewById.getLayoutParams();
//			//获得button控件的位置属性，需要注意的是，可以将button换成想变化位置的其它控件
////        layout.setMargins(0,-statusBarHeight,0,0);
//			layout.height=statusBarHeight;
//			//设置button的新位置属性,left，top，right，bottom
//			viewById.setLayoutParams(layout);
//				if (viewById!=null)
//				viewById.setVisibility(View.VISIBLE);
////				else
////					viewById.setVisibility(View.GONE);
//		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		mIsFirstLoad=true;
		mIsPrepare=false;
		mIsVisible = false;
		if (view != null) {
			((ViewGroup) view.getParent()).removeView(view);
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	protected String GetsimpleName() {
		return getClass().getSimpleName().toString();
	}
	protected boolean FragUseCaChe() {
		return false;
	}
	protected void onFragmentHandleMessage(Message msg) {
	}
	//订阅方法，当接收到事件的时候，会调用该方法
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onEvent(MessageEvent messageEvent){
		String isSucess = HelpUtils.HttpIsSucess(messageEvent.getMessage());
//		Log.e("onEvent","fragment"+messageEvent.getMessage());
		if (isSucess.equals(AppAllKey.CODE_OK))
			receiveResultMsg(messageEvent.getMessage());
//        textView.setText(messageEvent.getMessage());
	}
	public void receiveResultMsg(String responseText) {

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
