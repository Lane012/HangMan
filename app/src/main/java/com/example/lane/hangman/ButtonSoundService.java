package com.example.lane.hangman;

import android.content.Context;
import android.media.MediaPlayer;


/**
 * Created by lane on 5/23/17.
 */

public final class ButtonSoundService{
    private static MediaPlayer mp;

    private ButtonSoundService(){
    }

    public static void createPlayer(Context context){
        if(mp == null){
            mp = MediaPlayer.create(context, R.raw.button_click);
        }
    }
    public static boolean validPlayer(){
        return(mp != null);
    }

    public static void playButtonSound(){
        mp.start();
        mp.seekTo(0);
    }

}
