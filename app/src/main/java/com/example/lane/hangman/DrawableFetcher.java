package com.example.lane.hangman;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;

/**
 * Created by lane on 5/25/17.
 */

public class DrawableFetcher {
    private DrawableFetcher(){

    }
    public static GradientDrawable getGradientDrawable(Context context, int id){
        GradientDrawable drawable = (GradientDrawable)ContextCompat.getDrawable(context, id);
        return drawable;
    }
}
