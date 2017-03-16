package com.example.qianyiwang.wearmobilecontrol;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by qianyiwang on 3/6/17.
 */

public class CustomView extends View{

    private Paint paint_cursor, paint_text;

    public CustomView(Context context) {
        super(context);

        // create the Paint and set its color
        paint_cursor = new Paint();
        paint_cursor.setColor(Color.RED);
//        paint_cursor.setAlpha(125);

        paint_text = new Paint();
        paint_text.setColor(Color.BLACK);
        paint_text.setTextSize(40);


        GlobalValues.switchBt = BitmapFactory.decodeResource(getResources(), R.drawable.switchoff);

        // by default a layout does not need to draw, so an optimization is to not call is draw method.
        // By calling setWillNotDraw(false) you tell the UI toolkit that you want to draw.
        //lsetWillNotDraw(false);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
//        canvas.drawRect(700, 700, 900, 900, paint);
        canvas.drawBitmap(GlobalValues.switchBt, 500, 1400, null);
        canvas.drawText("DOUBLE TAP", 650,1450, paint_text);
        // always draw cursor at last so it can on top of the other graphics
        canvas.drawCircle(GlobalValues.x, GlobalValues.y, 20, paint_cursor);

    }
}
