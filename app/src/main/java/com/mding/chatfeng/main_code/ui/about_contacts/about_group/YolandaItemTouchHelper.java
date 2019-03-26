package com.mding.chatfeng.main_code.ui.about_contacts.about_group;

import android.support.v7.widget.helper.ItemTouchHelper;

public class YolandaItemTouchHelper extends ItemTouchHelper {
    Callback mCallback;

    public YolandaItemTouchHelper(Callback callback) {
        super(callback);
        mCallback= callback;
    }

    public Callback getCallback() {
        return mCallback;
    }
}