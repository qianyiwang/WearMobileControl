package com.example.qianyiwang.wearmobilecontrol;

import android.content.Intent;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;

/**
 * Created by qianyiwang on 2/7/17.
 */

public class WatchListener extends WearableListenerService {
    public static String START_ACTIVITY_PATH = "/from-watch";
    public static final String BROADCAST_ACTION = "message_from_watch";
    Intent broadCastIntent;
    protected final int MESSAGE_READ = 1;
    String msg_watch;
    android.os.Handler mHandler = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_READ:

                    if(msg_watch.equals("RIGHT")){
                        GlobalValues.x += 300;
                        GlobalValues.customView.invalidate();
                    }
                    else if(msg_watch.equals("LEFT")){
                        GlobalValues.x -= 300;
                        GlobalValues.customView.invalidate();
                    }
                    else if(msg_watch.equals("UP")){
                        GlobalValues.y -= 300;
                        GlobalValues.customView.invalidate();
                    }
                    else if(msg_watch.equals("DOWN")){
                        GlobalValues.y += 300;
                        GlobalValues.customView.invalidate();
                    }
                    break;
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        broadCastIntent = new Intent(BROADCAST_ACTION);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        if (messageEvent.getPath().equalsIgnoreCase(START_ACTIVITY_PATH)){
            msg_watch = new String(messageEvent.getData());
            mHandler.obtainMessage(MESSAGE_READ).sendToTarget();
//            broadCastIntent.putExtra("msg_watch", msg_watch);
//            sendBroadcast(broadCastIntent);
            Log.e("WatchListener",msg_watch);
        }
    }
}
