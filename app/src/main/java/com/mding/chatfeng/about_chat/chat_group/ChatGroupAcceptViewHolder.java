package com.mding.chatfeng.about_chat.chat_group;

import android.graphics.Color;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mding.chatfeng.about_chat.cus_data_group.CusGroupChatData;
import com.mding.chatfeng.about_utils.ImageUtils;
import com.mding.chatfeng.about_utils.TimeUtil;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmFriendUserHelper;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.rance.chatui.R;
import com.rance.chatui.util.Constants;
import com.rance.chatui.widget.BubbleImageView;
import com.rance.chatui.widget.GifTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatGroupAcceptViewHolder extends BaseViewHolder<CusGroupChatData> {
    public static final String TAG = "ChatGroupAcceptViewHolder";
    @BindView(R.id.chat_item_date)
    TextView chatItemDate;
    @BindView(R.id.chat_item_header)
    ImageView chatItemHeader;
    @BindView(R.id.chat_item_content_text)
    GifTextView chatItemContentText;
    @BindView(R.id.chat_item_content_image)
//    @BindView(R.id.chat_item_by_image)
            ImageView chatItemContentImage;
    @BindView(R.id.chat_item_voice)
    ImageView chatItemVoice;
    @BindView(R.id.chat_item_layout_content)
    LinearLayout chatItemLayoutContent;
    @BindView(R.id.chat_item_voice_time)
    TextView chatItemVoiceTime;
    @BindView(R.id.chat_item_name)
    TextView chatName;
    private ChatGroupAdapter.onItemClickListener onItemClickListener;
    private Handler handler;
    MotionEvent event;
    boolean isScrolling;
    //    RealmFriendUserHelper realmGroupChatHeaderHelper;
    RealmFriendUserHelper realmFriendUserHelper;
    public ChatGroupAcceptViewHolder(ViewGroup parent, ChatGroupAdapter.onItemClickListener onItemClickListener, Handler handler,boolean isScrolling) {
        super(parent, R.layout.item_group_chat_accept);
        ButterKnife.bind(this, itemView);
        this.onItemClickListener = onItemClickListener;
        this.handler = handler;
        this.isScrolling = isScrolling;
//        if (realmGroupChatHeaderHelper ==null)
//        realmGroupChatHeaderHelper = new RealmFriendUserHelper(getContext());

        if (realmFriendUserHelper==null)
            realmFriendUserHelper = new RealmFriendUserHelper(getContext());
    }
    @Override
    public void setData(final CusGroupChatData data) {
        if (StrUtils.isEmpty(data.getCreated()))
        {
            chatItemDate.setVisibility(View.GONE);
        }else
        {
            chatItemDate.setText(TimeUtil.formatDisplayTime(data.getCreated(),null));
            chatItemDate.setVisibility(View.VISIBLE);
        }
        chatItemHeader.setVisibility(View.VISIBLE);
        if (!StrUtils.isEmpty(data.getFriendId())) {
            String imgPath = realmFriendUserHelper.queryLinkFriendReturnImgPath(data.getFriendId());
            if (!StrUtils.isEmpty(imgPath))
                ImageUtils.useBase64(getContext(),chatItemHeader,imgPath);
            else
                ImageUtils.useerror(getContext(),chatItemHeader, com.mding.chatfeng.R.drawable.first_head_nor);

            String name = realmFriendUserHelper.queryLinkFriendReturnname(data.getFriendId());
            chatName.setText(name);
        }else
        {
            ImageUtils.useerror(getContext(),chatItemHeader, com.mding.chatfeng.R.drawable.first_head_nor);
        }
        chatItemHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onHeaderClick(getDataPosition(),Constants.CHAT_ITEM_TYPE_LEFT,data.getFriendId());
            }
        });
        chatItemContentText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemClickListener.onConClick(v,getAdapterPosition(),data.getMessage());
                return false;
            }
        });
//        chatItemContentText.setTextIsSelectable(true);
        try {
            switch (data.getMessageType())
            {
                case Constants.CHAT_TEXT:
                    chatItemContentText.setTextColor(Color.BLACK);
//                    chatItemContentText.setTextIsSelectable(true);
                    chatItemContentText.setSpanText(handler, data.getMessage(), false);
                    chatItemVoice.setVisibility(View.GONE);
                    chatItemContentText.setVisibility(View.VISIBLE);
                    chatItemLayoutContent.setVisibility(View.VISIBLE);
//                    好友昵称
                    chatName.setVisibility(View.VISIBLE);


                    chatItemVoiceTime.setVisibility(View.GONE);
                    chatItemContentImage.setVisibility(View.GONE);
                    break;
                case Constants.CHAT_NOTICE:
                    chatItemDate.setText(data.getMessage(),null);
                    chatItemDate.setVisibility(View.VISIBLE);
                    chatItemVoice.setVisibility(View.GONE);
                    chatName.setVisibility(View.GONE);
                    chatItemContentText.setVisibility(View.GONE);
                    chatItemHeader.setVisibility(View.GONE);
                    chatItemLayoutContent.setVisibility(View.GONE);
                    chatItemVoiceTime.setVisibility(View.GONE);
                    chatItemContentImage.setVisibility(View.GONE);
                    break;
                case Constants.CHAT_PICTURE:
                    chatItemVoice.setVisibility(View.GONE);
                    chatItemLayoutContent.setVisibility(View.GONE);
                    chatItemVoiceTime.setVisibility(View.GONE);
                    chatItemContentText.setVisibility(View.GONE);
                    chatItemContentImage.setVisibility(View.VISIBLE);
                    // TODO 显示发送的图片
                    String message = data.getMessage();
                    final String[] split = message.split("_");
//                    ImageLoader.getInstance().displayImage(split[1],chatItemContentImage);
                    ImageUtils.useBase64ToBitmap(getContext(),chatItemContentImage,split[1]);
                    MyLog.e("ChatSendViewHolder","------------------------groupAccept-------------------------"+split[1]);
//                    Glide.with(getContext()).load(data.getMessage()).into(chatItemContentImage);
                    chatItemContentImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onItemClickListener.onImageClick( getDataPosition(), split[1]);
                        }
                    });
                    break;
                case Constants.CHAT_EMOTION:
                    chatItemContentText.setSpanText(handler, data.getMessage(), false);
                    chatItemVoice.setVisibility(View.GONE);
                    chatItemContentText.setVisibility(View.VISIBLE);
                    chatItemLayoutContent.setVisibility(View.VISIBLE);
                    chatItemVoiceTime.setVisibility(View.GONE);
                    chatItemContentImage.setVisibility(View.GONE);
                    break;
                case Constants.CHAT_FILE:
                    chatItemVoice.setVisibility(View.VISIBLE);
                    chatItemLayoutContent.setVisibility(View.VISIBLE);
                    chatItemContentText.setVisibility(View.GONE);
                    chatItemVoiceTime.setVisibility(View.VISIBLE);
                    chatItemContentImage.setVisibility(View.GONE);
                    chatItemVoiceTime.setText("文件");
                    //                chatItemVoiceTime.setText(Utils.formatTime(data.getVoiceTime()));
                    chatItemLayoutContent.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onItemClickListener.onVoiceClick(chatItemVoice, getDataPosition());
                        }
                    });
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
