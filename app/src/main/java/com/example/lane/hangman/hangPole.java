package com.example.lane.hangman;

/**
 * Created by lane on 4/26/17.
 */

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.concurrent.TimeUnit;

public class hangPole extends View {
    int headColor;
    boolean white;
    boolean black;
    Paint paint;
    Path path;
    Path b;
    RectF r;
    String[] colors;
    int screenWidth;
    public hangPole(Context context, int screenSize){
        super(context);
        path = new Path();
        paint = new Paint();
        this.screenWidth = screenSize;
        init();
    }
    public hangPole(Context context, AttributeSet a){
        super(context, a);
        path = new Path();
        paint = new Paint();
        init();
    }
    public void init() {
        black = true;
        b = new Path();
        colors = new String[3];
        colors[0] = "#00b200";
        colors[1] = "#0000FF";
        colors[2] = "#FF0000";
        setWillNotDraw(false);
        if(screenWidth > 500){
            drawLargePole();
        }
        else{
            drawSmallPole();
        }

    }
    public void drawLargePole(){
        r = new RectF(205, 255, 235, 290);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        path.moveTo(300, 412);
        path.lineTo(300, 220);
        path.lineTo(220, 220);
        path.lineTo(220, 255);
        path.moveTo(300, 540);
        path.lineTo(300, 660);
        path.moveTo(300, 740);
        path.lineTo(300, 890);
    }
    public void drawSmallPole(){
        r = new RectF(165, 215, 195, 250);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        path.moveTo(250, 360);
        path.lineTo(250, 170);
        path.lineTo(180, 170);
        path.lineTo(180, 220);
        path.moveTo(250, 300);
        path.lineTo(250, 520);
        path.moveTo(250, 590);
        path.lineTo(250, 700);
    }
    public void purplePole() {
        paint.setColor(Color.rgb(139,0,139));
        white = true;
        black = false;
    }
    public void blackPole() {
        paint.setColor(Color.BLACK);
        black = true;
        white = false;
    }
    public void hangLarge(){
        b.moveTo(220, 300);
        b.lineTo(270, 290);
        b.moveTo(220, 300);
        b.lineTo(180, 290);
        b.moveTo(220, 300);
        b.lineTo(220, 340);
        b.lineTo(263, 365);
        b.moveTo(220, 340);
        b.lineTo(177, 365);
    }
    public void hangSmall(){
        b.moveTo(180, 260);
        b.lineTo(230, 250);
        b.moveTo(180, 260);
        b.lineTo(140, 250);
        b.moveTo(180, 260);
        b.lineTo(180, 300);
        b.lineTo(223, 325);
        b.moveTo(180, 300);
        b.lineTo(137, 325);
    }

    @Override
    public void onDraw(Canvas canvas) {
        int currentHeadColor;
        if(black){
            blackPole();
        }
        if(white){
            purplePole();
        }
        canvas.drawPath(this.path, this.paint);
        currentHeadColor = Color.parseColor(colors[(int)Math.floor(Math.random()*3)]);
        while(headColor == currentHeadColor){
            currentHeadColor = Color.parseColor(colors[(int)Math.floor(Math.random()*3)]);
        }
        headColor = currentHeadColor;
        paint.setColor(headColor);
        canvas.drawOval(r, paint);
        if(screenWidth > 500){
            hangLarge();
        }
        else{
            hangSmall();
        }
        canvas.drawPath(this.b, this.paint);
        super.onDraw(canvas);
    }

}

