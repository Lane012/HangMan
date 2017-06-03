package com.example.lane.hangman;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by lane on 5/16/17.
 */

public class HangMan extends View {
    String hint;
    Path path;
    Paint paint;
    RectF headSize;
    boolean drawHint;
    int color;
    ThemeSaver themeSaver;
    int missedCounter;
    int turns = -1;


    public HangMan(Context context, int color, String hint){
        super(context);
        this.hint = hint;
        turns = 6;
        drawHint = false;
        missedCounter = 0;
        setWillNotDraw(false);
        setColor(color);
        setupPathAndPaint();
        themeSaver = ThemeSaver.getInstance();

    }
    public HangMan(Context context, AttributeSet set){
        super(context, set);
        path = new Path();
        paint = new Paint();
    }

    public void setupPathAndPaint(){
        path = new Path();
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setColor(color);
    }

    public void countUp() {
        missedCounter++;
        turns--;
    }
    public void allowHint(){
        drawHint = true;
    }


    public void drawHead(Canvas canvas){
        headSize = new RectF(255, 72, 285, 132);
        canvas.drawOval(headSize, paint);
    }

    public void drawBody(){
        path.moveTo(270, 132);
        path.lineTo(270, 230);
    }

    public void drawLeftArm() {
        path.moveTo(270, 165);
        path.lineTo(225, 115);
    }

    public void drawRightArm() {
        path.moveTo(272, 165);
        path.lineTo(325, 115);
    }

    public void drawLeftLeg() {
        path.moveTo(270, 228);
        path.lineTo(225, 280);
    }

    public void drawRightLeg() {
        path.moveTo(272, 228);
        path.lineTo(317, 280);
    }
    public void setPaintDefaultWidth(){
        paint.setStrokeWidth(10);
    }

    public void setColor(int color){
        this.color = color;
    }

    public void revealHint(Canvas canvas){
        String HINT = "HINT: ";
        canvas.drawText(HINT + hint, 30, 400, paint);
    }
    public void displayTurns(Canvas canvas){
        switch (turns){
            case 6:
                canvas.drawText("TURNS LEFT 6", 30, 40, paint);
                break;
            case 5:
                canvas.drawText("TURNS LEFT 5", 30, 40, paint);
                break;
            case 4:
                canvas.drawText("TURNS LEFT 4", 30, 40, paint);
                break;
            case 3:
                canvas.drawText("TURNS LEFT 3", 30, 40, paint);
                break;
            case 2:
                canvas.drawText("TURNS LEFT 2", 30, 40, paint);
                break;
            case 1:
                canvas.drawText("TURNS LEFT 1", 30, 40, paint);
                break;
            case 0:
                paint.setTextSize(35);
                canvas.drawText("GAME OVER!", 30, 80, paint);
                break;
        }
    }





    @Override
    public void onDraw(Canvas canvas) {
        paint.setTextSize(25);
        paint.setStrokeWidth(2);
        displayTurns(canvas);
        if(drawHint){
            revealHint(canvas);
        }
        if(missedCounter == 0) {
            setPaintDefaultWidth();
        }
        if(missedCounter >= 1){
            setPaintDefaultWidth();
            drawHead(canvas);
        }
        if(missedCounter >= 2){
            drawBody();
        }
        if(missedCounter >= 3){
            drawLeftArm();
        }
        if(missedCounter >=4){
            drawRightArm();
        }
        if(missedCounter >=5){
            drawLeftLeg();
        }
        if(missedCounter >= 6){
            drawRightLeg();
        }
        path.moveTo(400, 350);
        path.lineTo(400, 30);
        path.moveTo(401, 30);
        path.lineTo(269, 30);
        path.moveTo(269, 30);
        path.lineTo(269, 70);
        canvas.drawPath(path, paint);
        super.onDraw(canvas);
    }
}
