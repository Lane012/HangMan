package com.example.lane.hangman;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lane on 5/25/17.
 */

public class DrawableHolder {
    private Map<String, Drawable> drawableDictionary;
    public DrawableHolder(){
        drawableDictionary = new HashMap<>();
    }

    public GradientDrawable getGradientDrawable(String drawableName){
            return (GradientDrawable) drawableDictionary.get(drawableName);
    }

    public void saveDrawable(GradientDrawable drawable, String drawableName){
        drawableDictionary.put(drawableName, drawable);
    }
}
