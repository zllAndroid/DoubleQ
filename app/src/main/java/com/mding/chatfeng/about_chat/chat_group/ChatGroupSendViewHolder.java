package com.mding.chatfeng.about_chat.chat_group;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mding.chatfeng.about_application.BaseApplication;
import com.mding.chatfeng.about_chat.cus_data_group.CusGroupChatData;
import com.mding.chatfeng.about_utils.ImageUtils;
import com.mding.chatfeng.about_utils.TimeUtil;
import com.mding.chatfeng.main_code.mains.PersonalFragment;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.rance.chatui.R;
import com.rance.chatui.util.Constants;
import com.rance.chatui.widget.BubbleImageView;
import com.rance.chatui.widget.GifTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatGroupSendViewHolder extends BaseViewHolder<CusGroupChatData> {

    @BindView(R.id.chat_item_date)
    TextView chatItemDate;
    @BindView(R.id.chat_item_header)
    ImageView chatItemHeader;
    @BindView(R.id.chat_item_content_text)
    GifTextView chatItemContentText;
    @BindView(R.id.chat_item_content_image)
//    @BindView(R.id.chat_item_by_image)
    BubbleImageView chatItemContentImage;
    @BindView(R.id.chat_item_fail)
    ImageView chatItemFail;
    @BindView (R.id.chat_item_progress)
    ProgressBar chatItemProgress;
    @BindView (R.id.chat_item_voice)
    ImageView chatItemVoice;
    @BindView(R.id.chat_item_layout_content)
    LinearLayout chatItemLayoutContent;
    @BindView(R.id.chat_item_voice_time)
    TextView chatItemVoiceTime;
    private ChatGroupAdapter.onItemClickListener onItemClickListener;
    private Handler handler;
    MotionEvent event;
    public ChatGroupSendViewHolder(ViewGroup parent, ChatGroupAdapter.onItemClickListener onItemClickListener, Handler handler) {
        super(parent, R.layout.item_chat_send);
        ButterKnife.bind(this, itemView);
        this.onItemClickListener = onItemClickListener;
        this.handler = handler;
    }
    @Override
    public void setData(final  CusGroupChatData data) {
//        chatItemDate.setText("2018-08-08");
        if (StrUtils.isEmpty(data.getCreated()))
        {
            chatItemDate.setVisibility(View.GONE);
        }else
        {
            chatItemDate.setText(TimeUtil.formatDisplayTime(data.getCreated(),null));
            chatItemDate.setVisibility(View.VISIBLE);
        }
        String asString = BaseApplication.getaCache().getAsString(PersonalFragment.IMAGE_BASE64);
        if (!StrUtils.isEmpty(asString))
        {
            ImageUtils.useBase64(getContext(),chatItemHeader,asString);
        }

        chatItemHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onHeaderClick(getDataPosition(),Constants.CHAT_ITEM_TYPE_RIGHT,data.getFriendId());
            }
        });


//        chatItemContentText.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent e) {
//                switch (e.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        event = e;
//                        break;
//                    default:
//                        break;
//                }
//                // 如果onTouch返回false,首先是onTouch事件的down事件发生，此时，如果长按，触发onLongClick事件；
//                // 然后是onTouch事件的up事件发生，up完毕，最后触发onClick事件。
//                return false;
//            }
//        });
        chatItemContentText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemClickListener.onConClick(getAdapterPosition(),data.getMessage());
                return true;
            }
        });
//        chatItemContentText.setTextIsSelectable(true);
        try {
            switch (data.getMessageType())
            {
                case Constants.CHAT_TEXT:
                    chatItemContentText.setTextColor(Color.BLACK);
                    chatItemContentText.setSpanText(handler, data.getMessage(), false);
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
                    // TODO 显示发送的图片
                    String message = data.getMessage();
                    final String[] split = message.split("_");
                    ImageUtils.useBase64ToBitmap(getContext(),chatItemContentImage,split[0]);
                    MyLog.e("ChatSendViewHolder","------------------------groupSend-------------------------"+split[1]);
//                    Glide.with(getContext()).load(data.getMessage()).into(chatItemContentImage);
                    chatItemContentImage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onItemClickListener.onImageClick(chatItemContentImage, getDataPosition(), split[1]);
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
        switch (data.getSendState()) {
            case Constants.CHAT_ITEM_SENDING:
                chatItemProgress.setVisibility(View.VISIBLE);
                chatItemFail.setVisibility(View.GONE);
                break;
            case Constants.CHAT_ITEM_SEND_ERROR:
                chatItemProgress.setVisibility(View.GONE);
                chatItemFail.setVisibility(View.VISIBLE);
                break;
            case Constants.CHAT_ITEM_SEND_SUCCESS:
                chatItemProgress.setVisibility(View.GONE);
                chatItemFail.setVisibility(View.GONE);
                break;
        }
    }
}
