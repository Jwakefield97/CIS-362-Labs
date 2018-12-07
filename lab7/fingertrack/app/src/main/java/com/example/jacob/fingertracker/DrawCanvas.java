package com.example.jacob.fingertracker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class DrawCanvas extends View {
    private int primaryX;
    private int primaryY;
    private int secondaryX;
    private int secondaryY;
    private boolean isPrimary;
    private boolean isSeconday;

    Paint paint = new Paint();

    public DrawCanvas(Context context) {
        super(context);
        isPrimary = false;
        isSeconday = false;
        primaryX = 0;
        primaryY = 0;
        secondaryX = 0;
        secondaryY = 0;
    }

    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.FILL);
        if(isPrimary){
            canvas.drawRect(new Rect(primaryX, primaryY, primaryX+250, primaryY+250), paint);
        }
        if(isSeconday){
            canvas.drawRect(new Rect(secondaryX, secondaryY, secondaryX+150, secondaryY+150), paint);

        }
    }

    public void removePrimary(){
        isPrimary = false;
        primaryX = 0;
        primaryY = 0;
    }

    public void removeSecondary() {
        isSeconday = false;
        secondaryX = 0;
        secondaryY = 0;
    }

    public void setPrimary(int x, int y){
        isPrimary = true;
        primaryX = x;
        primaryY = y;
    }

    public void setSecondary(int x, int y){
        isSeconday = true;
        secondaryX = x;
        secondaryY = y;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int touchAction = event.getActionMasked();

        switch (touchAction){
            case MotionEvent.ACTION_DOWN: //primary finger
                setPrimary((int)event.getX(),(int)event.getY());
                break;
            case MotionEvent.ACTION_POINTER_DOWN: //secondary finger
                setSecondary((int)event.getX(),(int)event.getY());
                break;
            case MotionEvent.ACTION_UP:
                removePrimary();
                break;
            case MotionEvent.ACTION_POINTER_UP:
                removeSecondary();
                break;
            case MotionEvent.ACTION_MOVE: //fingers moved
                setPrimary((int)event.getX(),(int)event.getY());
                break;
            case MotionEvent.ACTION_POINTER_INDEX_SHIFT:
                setSecondary((int)event.getX(),(int)event.getY());
                break;
        }
        this.invalidate();
        return true;
    }

}
