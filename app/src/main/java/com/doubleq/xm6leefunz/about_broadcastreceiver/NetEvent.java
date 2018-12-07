package com.doubleq.xm6leefunz.about_broadcastreceiver;

public class NetEvent {
    /**
     * @Description:用于网络的事件
     */
        public boolean isNet;

        public NetEvent(boolean isNet) {

            this.isNet = isNet;
        }

        public boolean isNet() {
            return isNet;
        }
}
