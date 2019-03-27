package com.mding.chatfeng.about_chat.adapter.holder;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mding.chatfeng.about_chat.adapter.ChatAdapter;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mding.chatfeng.about_utils.TimeUtil;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmLinkFriendHelper;
import com.mding.model.DataJieShou;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.rance.chatui.R;
import com.rance.chatui.util.Constants;
import com.rance.chatui.widget.BubbleImageView;
import com.rance.chatui.widget.GifTextView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
//接收
public class ChatAcceptViewHolder extends BaseViewHolder<DataJieShou.RecordBean> {

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
    private ChatAdapter.onItemClickListener onItemClickListener;
    private Handler handler;
    MotionEvent event;

    Context context;
    protected boolean isScrolling = false;
    RealmLinkFriendHelper realmLinkFriendHelper;
    public ChatAcceptViewHolder(ViewGroup parent, ChatAdapter.onItemClickListener onItemClickListener, Handler handler,boolean isScrolling ,Context context) {
        super(parent, R.layout.item_chat_accept);
        ButterKnife.bind(this,itemView);
        this.onItemClickListener = onItemClickListener;
        this.handler = handler;
        this.isScrolling = isScrolling;
        this.context = context;
        if (realmLinkFriendHelper==null)
        realmLinkFriendHelper = new RealmLinkFriendHelper(getContext());
    }
    @Override
    public void setData(final DataJieShou.RecordBean data) {
        if (StrUtils.isEmpty(data.getRequestTime()))
        {
            chatItemDate.setVisibility(View.GONE);
        }else
        {
            chatItemDate.setText(TimeUtil.formatDisplayTime(data.getRequestTime(),null));
            chatItemDate.setVisibility(View.VISIBLE);
        }

//        Glide.with(getContext())
//                .load(data.getHeadImg())
//                .dontAnimate()
//                .error(com.mding.chatfeng.R.drawable.mine_head)
//                .bitmapTransform(new CropCircleTransformation(getContext()))
//                .into(chatItemHeader);
        String imgPath = realmLinkFriendHelper.queryLinkFriendReturnImgPath(data.getFriendsId());
        if (imgPath!=null) {
            Uri uri = Uri.fromFile(new File(imgPath));
            chatItemHeader.setImageURI(uri);
//            Glide.with(context).load(imgPath)
//                    .error(R.drawable.mine_head)
//                    .bitmapTransform(new CropCircleTransformation(context))
//                    .into(chatItemHeader);


        }else {
//            chatItemHeader.setImageResource(com.mding.chatfeng.R.drawable.mine_head);
            Glide.with(getContext())
                    .load(data.getHeadImg())
                    .dontAnimate()
                    .error(com.mding.chatfeng.R.drawable.mine_head)
                    .bitmapTransform(new CropCircleTransformation(getContext()))
                    .into(chatItemHeader);
        }
        chatItemHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onHeaderClick(getDataPosition(),Constants.CHAT_ITEM_TYPE_LEFT,data.getFriendsId());
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
//                return true;
//            }
//        });
        chatItemContentText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onItemClickListener.onConClick(getAdapterPosition(),data.getMessage());
                return false;
            }
        });
//        chatItemContentText.setTextIsSelectable(true);
        try {
            switch (data.getMessageType())
            {
                case Constants.CHAT_TEXT:
                    chatItemContentText.setSpanText(handler, data.getMessage(), false);
                    chatItemVoice.setVisibility(View.GONE);
                    chatItemContentText.setVisibility(View.VISIBLE);
                    chatItemLayoutContent.setVisibility(View.VISIBLE);

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
