package com.example.lane.hangman;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public final class musicPlayer extends Service {
    static MediaPlayer mp;
    static boolean playerPaused;
    static boolean destroy;
    static int currentSong;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flag, int id){
        currentSong = R.raw.hang;
        mp = MediaPlayer.create(getBaseContext(), currentSong);
        mp.setLooping(true);
        mp.start();
        playerPaused = false;
        destroy = false;
        return Service.START_NOT_STICKY;
    }
    public static void resetPlayer() {
        mp.stop();
        mp.reset();
        mp.release();

    }
    public static boolean playerSilenced(){
        return playerPaused;
    }
    public static void startNewSong(Context context, int songId){
        resetPlayer();
        mp = MediaPlayer.create(context, songId);
        mp.setLooping(true);
        mp.start();
    }
    public static int chooseRandomSong(){
        int ms = 600;
        int[] songIds = new int[]{0, 430*ms, 816*ms, 1153*ms, 1656*ms, 2106*ms, 2626*ms, 3209*ms, 3547*ms, 3914*ms, 4258*ms, 4638*ms};
        int songNumber = (int)(Math.random()* 12);
        return songIds[songNumber];
    }
    public static boolean sameSong(int newSong){
        return(currentSong == newSong);
    }


    public static void silencePlayer(){
        playerPaused = true;
    }
    public static void unsilencePlayer(){
        playerPaused = false;
    }


    public static void stop() {
        mp.pause();
    }
    public static void start() {
        mp.start();
    }






    @Override
    public  void onDestroy() {
        super.onDestroy();
    }
}
