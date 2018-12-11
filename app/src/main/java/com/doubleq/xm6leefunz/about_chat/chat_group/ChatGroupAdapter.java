package com.doubleq.xm6leefunz.about_chat.chat_group;

import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.doubleq.model.DataGroupChatResult;
import com.doubleq.model.DataJieShou;
import com.doubleq.xm6leefunz.about_chat.adapter.holder.ChatAcceptViewHolder;
import com.doubleq.xm6leefunz.about_chat.adapter.holder.ChatSendViewHolder;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.rance.chatui.util.Constants;

public class ChatGroupAdapter extends RecyclerArrayAdapter<DataGroupChatResult.RecordBean> {

    private onItemClickListener onItemClickListener;
    public Handler handler;

    public ChatGroupAdapter(Context context) {
        super(context);
        handler = new Handler();
    }
    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = null;
        switch (viewType) {
            case Constants.CHAT_ITEM_TYPE_LEFT:
                viewHolder = new ChatGroupAcceptViewHolder(parent, onItemClickListener, handler);
                break;
            case Constants.CHAT_ITEM_TYPE_RIGHT:
                viewHolder = new ChatGroupSendViewHolder(parent, onItemClickListener, handler);
                break;
                default:
                    viewHolder = new ChatGroupAcceptViewHolder(parent, onItemClickListener, handler);
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
        void onHeaderClick(int position, int type, String friendId);
        void onConClick(View view, MotionEvent event, int position, String conText);

        void onImageClick(View view, int position);

        void onVoiceClick(ImageView imageView, int position);
    }
}
