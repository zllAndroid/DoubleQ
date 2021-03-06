package com.mding.chatfeng.about_base.web_base;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.projects.zll.utilslibrarybyzll.aboututils.MyLog;
import com.zll.websocket.ErrorResponse;
import com.zll.websocket.IResponseDispatcher;
import com.zll.websocket.Response;
import com.zll.websocket.ResponseDelivery;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ZhangKe on 2018/6/27.
 */
public class AppResponseDispatcher implements IResponseDispatcher {

    private static final String LOGTAG = "AppResponseDispatcher";

    @Override
    public void onConnected(ResponseDelivery delivery) {
        delivery.onConnected();
    }

    @Override
    public void onConnectError(Throwable cause, ResponseDelivery delivery) {
        delivery.onConnectError(cause);
    }
    @Override
    public void onWantConnect(Throwable cause, ResponseDelivery delivery) {
        delivery.onWantConnect(cause);
    }

    @Override
    public void onDisconnected(ResponseDelivery delivery) {
        delivery.onDisconnected();
    }

    /**
     * 统一处理响应的数据
     */
    @Override
    public void onMessageResponse(Response message, ResponseDelivery delivery) {
//        boolean contains = message.getResponseText().toString().contains("{");
        MyLog.e("message","服务器返回的信息："+message.getResponseText());
//        EventBus.getDefault().post(new MessageEvent(message.getResponseText()));
//        DialogUtils.showDialog(message.getResponseText()+"");
        if (message.getResponseText().toString().contains("{")) {
//            EventBus.getDefault().post(message.getResponseText());
            try {
                EventBus.getDefault().post(new MessageEvent(message.getResponseText()));
            } catch (Exception e) {
                e.printStackTrace();
            }
//            ResultRequest.getInstance().postMessage(message.getResponseText());
            delivery.onMessageResponse(new CommonResponse(message.getResponseText(), JSON.parseObject(message.getResponseText(), new TypeReference<CommonResponseEntity>() {})));
        }else
        {
//            ToastUtil.show(message.getResponseText()+"");
        }
//        try {
//            CommonResponseEntity responseEntity = JSON.parseObject(message.getResponseText(), new TypeReference<CommonResponseEntity>() {
//            });
//            CommonResponse commonResponse = new CommonResponse(message.getResponseText(), responseEntity);
//            if (commonResponse.getResponseEntity().getCode() >= 1000) {
//                delivery.onMessageResponse(commonResponse);
//            } else {
//                ErrorResponse errorResponse = new ErrorResponse();
//                errorResponse.setErrorCode(12);
//                errorResponse.setDescription(commonResponse.getResponseEntity().getMessage());
//                errorResponse.setResponseText(message.getResponseText());
//                //将已经解析好的 CommonResponseEntity 独享保存起来以便后面使用
//                errorResponse.setReserved(responseEntity);
//                onSendMessageError(errorResponse, delivery);
//            }
//        } catch (JSONException e) {
//            ErrorResponse errorResponse = new ErrorResponse();
//            errorResponse.setResponseText(message.getResponseText());
//            errorResponse.setErrorCode(11);
//            errorResponse.setCause(e);
//            onSendMessageError(errorResponse, delivery);
//        }
    }

    /**
     * 统一处理错误信息，
     * 界面上可使用 ErrorResponse#getDescription() 来当做提示语
     */
    @Override
    public void onSendMessageError(ErrorResponse error, ResponseDelivery delivery) {
        switch (error.getErrorCode()) {
            case 1:
                error.setDescription("网络错误");
                break;
            case 2:
                error.setDescription("网络错误");
                break;
            case 3:
                error.setDescription("网络错误");
                break;
            case 11:
                error.setDescription("数据格式异常");
                Log.e(LOGTAG, "数据格式异常", error.getCause());
                break;
        }
        delivery.onSendMessageError(error);
    }
}
