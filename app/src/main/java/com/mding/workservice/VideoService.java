package com.mding.workservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class VideoService extends Service {
    public VideoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
