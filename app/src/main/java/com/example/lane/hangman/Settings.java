package com.example.lane.hangman;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class Settings extends AppCompatActivity {
    private CheckBox Dark;
    private CheckBox Light;
    private CheckBox On;
    private CheckBox Off;
    private TextView Theme;
    private TextView Music;
    private ThemeSaver themeSaver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeSaver = getThemeSaver();
        setContentView(R.layout.activity_settings);
        Light = (CheckBox)findViewById(R.id.Light);
        Dark = (CheckBox)findViewById(R.id.Dark);
        On = (CheckBox)findViewById(R.id.On);
        Off = (CheckBox)findViewById(R.id.Off);
        Theme = (TextView)findViewById(R.id.Theme);
        Music = (TextView)findViewById(R.id.Music);
        Theme.setX(200);
        Theme.setY(230);
        Light.setX(135);
        Light.setY(300);
        Dark.setX(330);
        Dark.setY(300);
        Music.setX(200);
        Music.setY(530);
        On.setX(150);
        On.setY(600);
        Off.setX(330);
        Off.setY(600);
        setListeners();
        setMusicListeners();
    }

    private void setListeners(){
        Light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Light.isChecked()) {
                    ThemeSaver.changeTheme("light");
                    Dark.setChecked(false);
                } else {
                    Dark.setChecked(true);
                    ThemeSaver.changeTheme("dark");
                    Light.setChecked(false);
                }
            }
        });
        Dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Dark.isChecked()){
                    themeSaver.changeTheme("dark");
                    Light.setChecked(false);
                }
                else{
                    Light.setChecked(true);
                    themeSaver.changeTheme("light");
                    Dark.setChecked(false);
                }
            }
        });
    }

    private void setMusicListeners(){
        On.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(On.isChecked()) {
                    Off.setChecked(false);
                    musicPlayer.unsilencePlayer();
                    musicPlayer.start();
                } else {
                    Off.setChecked(true);
                    musicPlayer.stop();
                    musicPlayer.silencePlayer();
                    On.setChecked(false);
                }
            }
        });
        Off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Off.isChecked()){
                    musicPlayer.stop();
                    musicPlayer.silencePlayer();
                    On.setChecked(false);
                }
                else{
                    On.setChecked(true);
                    musicPlayer.start();
                    musicPlayer.unsilencePlayer();
                    Off.setChecked(false);
                }
            }
        });
    }

    private ThemeSaver getThemeSaver(){
        return(ThemeSaver.getInstance());
    }
}
