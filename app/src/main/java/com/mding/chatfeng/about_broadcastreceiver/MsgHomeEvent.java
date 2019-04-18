package com.mding.chatfeng.about_broadcastreceiver;

public class MsgHomeEvent {
    /**
     * @Description:用于网络的事件
     */
        public String msg;
        public boolean isNet;

        public MsgHomeEvent(boolean isNet) {

            this.isNet = isNet;
        }

        public boolean isNet() {
            return isNet;
        }
}
