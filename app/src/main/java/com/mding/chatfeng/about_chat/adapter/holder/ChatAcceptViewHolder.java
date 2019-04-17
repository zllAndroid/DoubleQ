package com.mding.chatfeng.about_chat.adapter.holder;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mding.chatfeng.about_chat.adapter.ChatAdapter;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.mding.chatfeng.about_utils.ImageUtils;
import com.mding.chatfeng.about_utils.TimeUtil;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmFriendRelationHelper;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmFriendUserHelper;
import com.mding.chatfeng.main_code.ui.about_contacts.about_link_realm.RealmMsgInfoTotalHelper;
import com.mding.model.DataJieShou;
import com.projects.zll.utilslibrarybyzll.aboututils.StrUtils;
import com.rance.chatui.R;
import com.rance.chatui.util.Constants;
import com.rance.chatui.widget.BubbleImageView;
import com.rance.chatui.widget.GifTextView;

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
    RealmMsgInfoTotalHelper realmMsgInfoTotalHelper;
    RealmFriendUserHelper realmFriendUserHelper;
    RealmFriendRelationHelper friendHelper;
    public ChatAcceptViewHolder(ViewGroup parent, ChatAdapter.onItemClickListener onItemClickListener, Handler handler,boolean isScrolling ,Context context) {
        super(parent, R.layout.item_chat_accept);
        ButterKnife.bind(this,itemView);
        this.onItemClickListener = onItemClickListener;
        this.handler = handler;
        this.isScrolling = isScrolling;
        this.context = context;
        if (realmMsgInfoTotalHelper ==null)
            realmMsgInfoTotalHelper = new RealmMsgInfoTotalHelper(getContext());
        if (friendHelper==null)
            friendHelper = new RealmFriendRelationHelper(getContext());
        if (realmFriendUserHelper==null)
            realmFriendUserHelper = new RealmFriendUserHelper(getContext());
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
//        if (!StrUtils.isEmpty(data.getFriendsId())) {
//            String imgPath = realmMsgInfoTotalHelper.queryLinkFriendReturnImgPath(data.getFriendsId());
//            ImageUtils.useBase64(context,chatItemHeader,imgPath);
//        }

        if (!StrUtils.isEmpty(data.getFriendsId())) {
            String imgPath = realmFriendUserHelper.queryLinkFriendReturnImgPath(data.getFriendsId());
            ImageUtils.useBase64(getContext(),chatItemHeader,imgPath);
        }
//        ImageUtils.useBase64(getContext(),chatItemHeader,data.getHeadImg());
        chatItemHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onHeaderClick(getDataPosition(),Constants.CHAT_ITEM_TYPE_LEFT,data.getFriendsId());
            }
        });
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
    }
}
