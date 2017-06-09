package com.example.lane.hangman;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class hangMenu extends AppCompatActivity {
    TextView H;
    String[] colors;
    TextView A;
    TextView N;
    TextView G;
    TextView M;
    TextView A2;
    TextView N2;
    hangPole pole;
    Button settings;
    TextView base;
    Button newGame;
    private AsyncTask task;
    RelativeLayout rl;
    int screenWidth;
    private static ThemeSaver themeSaver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        if(screenWidth > 500) {
            setContentView(R.layout.activity_hang_menu_large);
        }
        else {
            setContentView(R.layout.activity_hang_menu);
        }
        if(!ButtonSoundService.validPlayer()){
            ButtonSoundService.createPlayer(getBaseContext());
        }
        themeSaver = getThemeSaver();
        colors = new String[3];
        colors[0] = "#00b200";
        colors[1] = "#0000FF";
        colors[2] = "#FF0000";
        H = (TextView) findViewById(R.id.H);
        H.setTextColor(Color.parseColor(colors[0]));
        settings = (Button) findViewById(R.id.Settings);
        settings.setX(185);
        settings.setY(100);
        base = (TextView) findViewById(R.id.base);
        base.setX(35);
        base.setY(875);
        newGame = (Button) findViewById(R.id.New);
        A = (TextView) findViewById(R.id.A);
        N = (TextView) findViewById(R.id.N);
        G = (TextView) findViewById(R.id.G);
        M = (TextView) findViewById(R.id.M);
        A2 = (TextView) findViewById(R.id.A2);
        N2 = (TextView) findViewById(R.id.N2);
        A.setTextColor(Color.parseColor(colors[1]));
        N.setTextColor(Color.parseColor(colors[2]));
        G.setTextColor(Color.parseColor(colors[1]));
        M.setTextColor(Color.parseColor(colors[0]));
        A2.setTextColor(Color.parseColor(colors[2]));
        N2.setTextColor(Color.parseColor(colors[0]));
        startService();
        rl = (RelativeLayout) findViewById(R.id.main);
        createListeners();
        pole = new hangPole(this, screenWidth);
        if(themeSaver.getTheme().equals("DARK")){
            pole.purplePole();
            GradientDrawable shape = (GradientDrawable) base.getBackground();
            shape.setColor(Color.rgb(139,0,139));
            rl.setBackgroundColor(Color.BLACK);
        }
        else {
            pole.blackPole();
        }
        rl.addView(pole);
        task = new ColorChanger().execute();



    }
    @Override
    public void onBackPressed() {
    }

    @Override
    public void onRestart() {
        super.onRestart();
        task = new ColorChanger().execute();
        if (themeSaver.getTheme().equals("DARK")) {
            pole.purplePole();
            GradientDrawable shape = (GradientDrawable) base.getBackground();
            shape.setColor(Color.rgb(139,0,139));
            rl.setBackgroundColor(Color.BLACK);
        } else {
            pole.blackPole();
            GradientDrawable shape = (GradientDrawable) base.getBackground();
            shape.setColor(Color.BLACK);
            rl.setBackgroundColor(Color.YELLOW);
        }
    }



    public void createListeners() {
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonSoundService.playButtonSound();
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                task.cancel(true);
                startActivity(intent);
            }
        });
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonSoundService.playButtonSound();
                if(!musicPlayer.playerSilenced()) {
                    musicPlayer.startNewSong(getBaseContext(), R.raw.food);
                }
                task.cancel(true);
                createGameFactory();
            }
        });
    }
    public void createGameFactory(){
        Colors color = Colors.getRandomColor();
        Intent intent = new Intent(this, HangGame.class);
        Bundle b = new Bundle();
        b.putSerializable("color", color);
        intent.putExtras(b);
        startActivity(intent);

    }


    public Runnable animator = new Runnable() {
        public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("Thread Stopped");
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pole.invalidate();
                    }
                });
        }
    };

    public ThemeSaver getThemeSaver() {
        return (ThemeSaver.getInstance());
    }

    public void startService() {
        startService(new Intent(getBaseContext(), musicPlayer.class));
    }

    private class ColorChanger extends AsyncTask<Runnable, Void, Void> {
        @Override
        public Void doInBackground(Runnable... run) {
            while(!isCancelled()){
                animator.run();
            }
            return null;
        }



    }

}