package com.example.qianyiwang.wearmobilecontrol;

import android.app.Activity;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class MainApp extends AppCompatActivity{

    // Called when the activity is first created.
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GlobalValues.customView = new CustomView(this);
        setContentView(GlobalValues.customView);
    }
}
