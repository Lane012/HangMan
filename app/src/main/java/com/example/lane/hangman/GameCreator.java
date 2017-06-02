package com.example.lane.hangman;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class GameCreator extends AppCompatActivity{
    Button twoPlayer;
    Button onePlayer;
    ThemeSaver themeSaver;
    TextView Header;
    int color;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_creator);
        setUp();
        checkTheme();
        setListeners();
    }
    @Override
    public void onRestart() {
        super.onRestart();
        if(!musicPlayer.playerSilenced()){
            musicPlayer.startNewSong(getBaseContext(), R.raw.hang);
        }
        checkTheme();
    }
    @Override
    public void onResume(){
        super.onResume();
        if(!musicPlayer.playerSilenced() && !musicPlayer.sameSong(R.raw.hang)){
            musicPlayer.startNewSong(getBaseContext(), R.raw.hang);
        }

    }

    public void setUp() {
        themeSaver = ThemeSaver.getInstance();
        twoPlayer = (Button)findViewById(R.id.twoPlayer);
        onePlayer = (Button)findViewById(R.id.onePlayer);
        Header = (TextView)findViewById(R.id.Header);
    }
    public void checkTheme() {
        if(themeSaver.getTheme().equals("DARK")) {
            RelativeLayout rl = (RelativeLayout) findViewById(R.id.creator);
            rl.setBackgroundColor(Color.rgb(15, 0, 56));
            Header.setTextColor(Color.rgb(72, 0, 255));
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    public void createGameFactory(){
        Colors color = Colors.getRandomColor();
        Intent intent = new Intent(this, HangGame.class);
        Bundle b = new Bundle();
        b.putSerializable("color", color);
        intent.putExtras(b);
        startActivity(intent);

    }

    public void setListeners() {
        onePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonSoundService.playButtonSound();
                if(!musicPlayer.playerSilenced()) {
                    musicPlayer.startNewSong(getBaseContext(), R.raw.food);
                }
                createGameFactory();

            }
        });

        twoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonSoundService.playButtonSound();
                if(!musicPlayer.playerSilenced()) {
                    musicPlayer.startNewSong(getBaseContext(), R.raw.food);
                }
                createGameFactory();
            }
        });


    }
}
