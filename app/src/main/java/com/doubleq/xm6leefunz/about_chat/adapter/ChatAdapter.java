package com.doubleq.xm6leefunz.about_chat.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.doubleq.model.DataJieShou;
import com.doubleq.xm6leefunz.about_chat.adapter.holder.ChatAcceptViewHolder;
import com.doubleq.xm6leefunz.about_chat.adapter.holder.ChatSendViewHolder;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.rance.chatui.enity.MessageInfo;
import com.rance.chatui.util.Constants;

public class ChatAdapter extends RecyclerArrayAdapter<DataJieShou.RecordBean> {

    private onItemClickListener onItemClickListener;
    public Handler handler;

    public ChatAdapter(Context context) {
        super(context);
        handler = new Handler();
    }
    protected boolean isScrolling = false;
    public void setScrolling(boolean scrolling) {
        isScrolling = scrolling;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = null;
        switch (viewType) {
            case Constants.CHAT_ITEM_TYPE_LEFT:
                viewHolder = new ChatAcceptViewHolder(parent, onItemClickListener, handler,isScrolling);
                break;
            case Constants.CHAT_ITEM_TYPE_RIGHT:
                viewHolder = new ChatSendViewHolder(parent, onItemClickListener, handler,isScrolling);
                break;
                default:
                    viewHolder = new ChatAcceptViewHolder(parent, onItemClickListener, handler,isScrolling);
                    break;
        }
        return viewHolder;
    }

    @Override
    public int getViewType(int position) {
        return getAllData().get(position).getType();
    }

    public void addItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener {
        void onHeaderClick(int position,int type,String friendId);
        void onConClick(View view, MotionEvent event, int position,String conText);

        void onImageClick(View view, int position);

        void onVoiceClick(ImageView imageView, int position);
    }
}
