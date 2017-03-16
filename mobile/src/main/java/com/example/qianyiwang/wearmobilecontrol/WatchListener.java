package com.example.qianyiwang.wearmobilecontrol;

import android.content.Intent;
import android.graphics.BitmapFactory;
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
    private static final float mapping_ratio_h = 0.15f;
    private static final float mapping_ratio_v = 0.1f;
    Intent broadCastIntent;
    protected final int MESSAGE_READ = 1;
    android.os.Handler mHandler = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_READ:
                    GlobalValues.customView.invalidate();
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
            String msg_watch = new String(messageEvent.getData());

            if(msg_watch.contains("action:")){
                String[] parts = msg_watch.split(":");
                GlobalValues.action = parts[1];
                Log.e("action",GlobalValues.action);
                switch (GlobalValues.action){
                    case "double tap":
                        if(GlobalValues.x>=500 && GlobalValues.x<=620 && GlobalValues.y>=1400 && GlobalValues.y<=1500){
                            GlobalValues.switch_flag = !GlobalValues.switch_flag;
                            if(GlobalValues.switch_flag){
                                GlobalValues.switchBt = BitmapFactory.decodeResource(getResources(), R.drawable.switchon);
                            }
                            else{
                                GlobalValues.switchBt = BitmapFactory.decodeResource(getResources(), R.drawable.switchoff);
                            }
                        }
                        break;
                }
            }
            else{
                String[] parts = msg_watch.split(",");

                if((GlobalValues.lastX + Float.parseFloat(parts[0])*mapping_ratio_h>=0) && GlobalValues.lastX + Float.parseFloat(parts[0])*mapping_ratio_h<=1100){
                    GlobalValues.x = GlobalValues.lastX + Float.parseFloat(parts[0])*mapping_ratio_h;
                    GlobalValues.lastX = GlobalValues.x;
                }
                if(GlobalValues.lastY + Float.parseFloat(parts[1])*mapping_ratio_v>=0 && GlobalValues.lastY + Float.parseFloat(parts[1])*mapping_ratio_v<=1500){
                    GlobalValues.y = GlobalValues.lastY + Float.parseFloat(parts[1])*mapping_ratio_v;
                    GlobalValues.lastY = GlobalValues.y;
                }
            }

            mHandler.obtainMessage(MESSAGE_READ).sendToTarget();
        }
    }
}
