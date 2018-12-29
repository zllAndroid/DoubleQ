package com.doubleq.xm6leefunz.about_chat.chat_group;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doubleq.model.DataGroupChatResult;
import com.doubleq.model.DataJieShou;
import com.doubleq.xm6leefunz.about_chat.ChatActivity;
import com.doubleq.xm6leefunz.about_chat.cus_data_group.CusGroupChatData;
import com.doubleq.xm6leefunz.about_utils.TimeUtil;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.rance.chatui.R;
import com.rance.chatui.util.Constants;
import com.rance.chatui.widget.BubbleImageView;
import com.rance.chatui.widget.GifTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * 作者：Rance on 2016/11/29 10:47
 * 邮箱：rance935@163.com
 */
public class ChatGroupAcceptViewHolder extends BaseViewHolder<CusGroupChatData> {

    @BindView(R.id.chat_item_date)
    TextView chatItemDate;
    @BindView(R.id.chat_item_header)
    ImageView chatItemHeader;
    @BindView(R.id.chat_item_content_text)
    GifTextView chatItemContentText;
    @BindView(R.id.chat_item_content_image)
    BubbleImageView chatItemContentImage;
    @BindView(R.id.chat_item_voice)
    ImageView chatItemVoice;
    @BindView(R.id.chat_item_layout_content)
    LinearLayout chatItemLayoutContent;
    @BindView(R.id.chat_item_voice_time)
    TextView chatItemVoiceTime;
    private ChatGroupAdapter.onItemClickListener onItemClickListener;
    private Handler handler;
    MotionEvent event;
    public ChatGroupAcceptViewHolder(ViewGroup parent, ChatGroupAdapter.onItemClickListener onItemClickListener, Handler handler) {
        super(parent, R.layout.item_chat_accept);
        ButterKnife.bind(this, itemView);
        this.onItemClickListener = onItemClickListener;
        this.handler = handler;
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
//        chatItemDate.setText("上午 9:00"+data.getRequestTime());
//        Glide.with(getContext()).load(ChatActivity.friendHeader).crossFade(1000).into(chatItemHeader);
        Glide.with(getContext()).load(data.getImgHead())
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .crossFade(1000).into(chatItemHeader);
        chatItemHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onHeaderClick(getDataPosition(),Constants.CHAT_ITEM_TYPE_LEFT,data.getGroupId());
            }
        });
        chatItemContentText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent e) {
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        event = e;
                        break;
                    default:
                        break;
                }
                // 如果onTouch返回false,首先是onTouch事件的down事件发生，此时，如果长按，触发onLongClick事件；
                // 然后是onTouch事件的up事件发生，up完毕，最后触发onClick事件。
                return true;
            }
        });
        chatItemContentText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemClickListener.onConClick(v,event,getAdapterPosition(),data.getMessage());
                return false;
            }
        });
//        chatItemContentText.setTextIsSelectable(true);
        try {
            switch (data.getMessageType())
            {
                case Constants.CHAT_TEXT:
                    chatItemContentText.setSpanText(handler, data.getMessage(), true);
                    chatItemVoice.setVisibility(View.GONE);
                    chatItemContentText.setVisibility(View.VISIBLE);
                    chatItemLayoutContent.setVisibility(View.VISIBLE);

                    chatItemVoiceTime.setVisibility(View.GONE);
                    chatItemContentImage.setVisibility(View.GONE);
                    break;
                case Constants.CHAT_NOTICE:
                    chatItemDate.setText(data.getMessage(),null);
                    chatItemDate.setVisibility(View.VISIBLE);
                    chatItemVoice.setVisibility(View.GONE);
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
                    Glide.with(getContext()).load(data.getMessage()).into(chatItemContentImage);
                    chatItemContentImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onItemClickListener.onImageClick(chatItemContentImage, getDataPosition());
                        }
                    });
                    break;
                case Constants.CHAT_EMOTION:
                    chatItemContentText.setSpanText(handler, data.getMessage(), true);
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
//        if (data.getContent() != null) {
//            chatItemContentText.setSpanText(handler, data.getContent(), true);
//            chatItemVoice.setVisibility(View.GONE);
//            chatItemContentText.setVisibility(View.VISIBLE);
//            chatItemLayoutContent.setVisibility(View.VISIBLE);
//            chatItemVoiceTime.setVisibility(View.GONE);
//            chatItemContentImage.setVisibility(View.GONE);
//        } else if (data.getImageUrl() != null) {
//            chatItemVoice.setVisibility(View.GONE);
//            chatItemLayoutContent.setVisibility(View.GONE);
//            chatItemVoiceTime.setVisibility(View.GONE);
//            chatItemContentText.setVisibility(View.GONE);
//            chatItemContentImage.setVisibility(View.VISIBLE);
//            Glide.with(getContext()).load(data.getImageUrl()).into(chatItemContentImage);
//            chatItemContentImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onItemClickListener.onImageClick(chatItemContentImage, getDataPosition());
//                }
//            });
//        } else if (data.getFilepath() != null) {
//            chatItemVoice.setVisibility(View.VISIBLE);
//            chatItemLayoutContent.setVisibility(View.VISIBLE);
//            chatItemContentText.setVisibility(View.GONE);
//            chatItemVoiceTime.setVisibility(View.VISIBLE);
//            chatItemContentImage.setVisibility(View.GONE);
//            chatItemVoiceTime.setText(Utils.formatTime(data.getVoiceTime()));
//            chatItemLayoutContent.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onItemClickListener.onVoiceClick(chatItemVoice, getDataPosition());
//                }
//            });
//        }
    }
}
