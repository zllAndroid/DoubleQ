package com.mding.chatfeng.about_chat.chat_group;

import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.mding.chatfeng.about_chat.cus_data_group.CusGroupChatData;
import com.rance.chatui.util.Constants;

public class ChatGroupAdapter extends RecyclerArrayAdapter<CusGroupChatData> {
//    List<CusGroupChatData>
    private onItemClickListener onItemClickListener;
    public Handler handler;

    public ChatGroupAdapter(Context context) {
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
                viewHolder = new ChatGroupAcceptViewHolder(parent, onItemClickListener, handler,isScrolling);
                break;
            case Constants.CHAT_ITEM_TYPE_RIGHT:
                viewHolder = new ChatGroupSendViewHolder(parent, onItemClickListener, handler);
                break;
                default:
                    viewHolder = new ChatGroupAcceptViewHolder(parent, onItemClickListener, handler,isScrolling);
                    break;
        }
        return viewHolder;
    }

    @Override
    public int getViewType(int position) {
        return getAllData().get(position).getUserMessageType();
    }

    public void addItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener {
        void onHeaderClick(int position, int type, String friendId);
        void onConClick( int position, String conText);

        void onImageClick(View view, int position);

        void onVoiceClick(ImageView imageView, int position);
    }
}
