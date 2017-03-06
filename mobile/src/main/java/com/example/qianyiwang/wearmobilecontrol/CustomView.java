package com.example.qianyiwang.wearmobilecontrol;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by qianyiwang on 3/6/17.
 */

public class CustomView extends View{

    private Paint paint;

    public CustomView(Context context) {
        super(context);
        // create the Paint and set its color
        paint = new Paint();
        paint.setColor(Color.GRAY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLUE);
        canvas.drawCircle(GlobalValues.x, GlobalValues.y, 100, paint);
    }
}
