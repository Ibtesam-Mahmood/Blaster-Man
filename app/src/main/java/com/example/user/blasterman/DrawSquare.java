package com.example.user.blasterman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by User on 6/3/2018.
 */

public class DrawSquare extends View {

    private float x, y;
    private float w, h;
    private int color;

    private Paint paint;

    //Basic no coordinates no color constructor
    public DrawSquare(Context context) {
        super(context);
        x = 0;
        y = 0;
        w = 10;
        h = 10;
        color = Color.RED;
        paint = new Paint();
    }

    //Basic no color constructor with coordinates
    public DrawSquare(Context context, float x, float y, float h, float w){
        super(context);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        color = Color.RED;
        paint = new Paint();
    }

    //Basic constructor with color and coordinates
    public DrawSquare(Context context, float x, float y, float h, float w, int color){
        super(context);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(color);
        paint.setStrokeWidth(3);
        canvas.drawRect(x, y, x+w, y+h, paint);
    }

    public void setColor(int color) {
        this.color = color;
    }
}
