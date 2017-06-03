package com.example.lane.hangman;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by lane on 5/16/17.
 */

public class HangGame extends AppCompatActivity implements Game{
    ThemeSaver themeSaver;
    Colors color;
    RelativeLayout rl;
    HangMan hangman;
    WordView wordView;
    DrawableHolder Drawables;
    RelativeLayout scroll;
    public HangGame(){
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Drawables = new DrawableHolder();
        themeSaver = ThemeSaver.getInstance();
        setDrawable(R.drawable.hangframe, "hangFrame");
        setDrawable(R.drawable.buttonguesser, "buttonGuesser");
        setDrawable(R.drawable.lightbluebar, "lightBlueBar");
        setDrawable(R.drawable.lightredbar, "lightRedBar");
        setDrawable(R.drawable.lightyellowbar, "lightYellowBar");
        setUpHangBackground();
        setContentView(R.layout.activity_game);
        setRelativeLayout();
        getAndSetCategoryName();
        setUpWordView();
        getAndSetScrollBar();
        setUpButtonBar();
        rl.addView(wordView);
        setupBackGround();
        setupHangMan();
    }

    public void setupHangMan(){
        int colorId = Colors.getColorId(color);
        hangman = new HangMan(this, colorId, wordView.getHint());
        updateUI.run();
        setUpHangBackground();
        rl.addView(hangman);

    }
    public void setDrawable(int drawableId, String drawableName){
        GradientDrawable drawable = DrawableFetcher.getGradientDrawable(this, drawableId);
        Drawables.saveDrawable(drawable, drawableName);
    }

    public void setRelativeLayout(){
        rl = (RelativeLayout)findViewById(R.id.foodBackground);
    }

    public void getAndSetScrollBar(){
        scroll = (RelativeLayout) findViewById(R.id.keyboard);
    }

    public void getAndSetCategoryName(){
        Bundle b = getIntent().getExtras();
        color = (Colors)b.getSerializable("color");
    }

    public boolean compareGuessedLetterToSecretWord(Button b){
        boolean match = false;
        Character currentCharacter = Character.toLowerCase(b.getText().charAt(0));
        for(int i= 0; i < wordView.getChildCount(); i++) {
            TextView child = (TextView) wordView.getChildAt(i);
            Character childCharacter = Character.toLowerCase(child.getText().charAt(0));
            if (currentCharacter.compareTo(childCharacter) == 0) {
                wordView.plugCharacterIn(i);
                match = true;
            }
        }
        return match;
    }
    public void setListener(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GradientDrawable blackedBackground= (GradientDrawable)ContextCompat.getDrawable(getApplicationContext(), R.drawable.usedbutton);
                GradientDrawable whiteBackground = (GradientDrawable)ContextCompat.getDrawable(getApplicationContext(), R.drawable.white);
                ButtonSoundService.playButtonSound();
                Button buttonPressed = (Button)v;
                if(!compareGuessedLetterToSecretWord(buttonPressed) && !buttonPressed.getText().equals("HINT")){
                    hangman.countUp();
                    updateUI.run();
                }
                else if(((Button) v).getText().equals("HINT")){
                    hangman.allowHint();
                    updateUI.run();
                }
                if(darkTheme()) {
                    v.setBackground(blackedBackground);
                }
                else{
                    v.setBackground(whiteBackground);
                }
            }
        });
    }

    public void setUpButtonBar(){
        for(int i=0; i < scroll.getChildCount(); i++){
            Button currentButton = (Button)scroll.getChildAt(i);
            setListener(currentButton);
            if(!darkTheme()){
                currentButton.setTextColor(Color.BLACK);
            }
            currentButton.setBackground(buttonBarBackgroundChooser());
        }
    }

    public GradientDrawable buttonBarBackgroundChooser(){
        GradientDrawable buttonBarBackground;
        switch(color) {
            case YELLOW:
                buttonBarBackground = Drawables.getGradientDrawable("lightYellowBar");
                break;
            case BLUE:
                buttonBarBackground = Drawables.getGradientDrawable("lightBlueBar");
                break;
            case RED:
                buttonBarBackground = Drawables.getGradientDrawable("lightRedBar");
                break;
            default:
                buttonBarBackground = Drawables.getGradientDrawable("lightYellowBar");
        }
        return buttonBarBackground;
    }


    public void setUpWordView() {
        wordView = new WordView(this);
        wordView.setX(0);
        wordView.setY(451);
    }

    public void setUpHangBackground(){
        GradientDrawable hangFrame =  Drawables.getGradientDrawable("hangFrame");
        if(darkTheme()){
            hangFrame.setColor(Color.BLACK);
            hangFrame.setStroke(6, Color.WHITE);
        }
        else{
            hangFrame.setColor(Color.WHITE);
            hangFrame.setStroke(6, Color.BLACK);
        }
    }
    
    public Runnable updateUI = new Runnable() {
        public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hangman.invalidate();
                        wordView.invalidate();
                    }
                });
        }
    };

    public boolean darkTheme(){
        if(themeSaver.getTheme().equals("DARK")){
            return true;
        }
        return false;
    }

    public void setupBackGround(){
            if(color.equals(Colors.RED)) {
                if (darkTheme()) {
                    rl.setBackgroundColor(Color.rgb(175, 0, 0));
                } else {
                    rl.setBackgroundColor(Color.RED);
                }
            }
            else if(color.equals(Colors.BLUE)){
                if (darkTheme()) {
                    rl.setBackgroundColor(Color.rgb(0, 38, 99));
                }
                else {
                    rl.setBackgroundColor(Color.BLUE);
                }
            }
            else{
                if (darkTheme()) {
                    rl.setBackgroundColor(Color.rgb(255, 204, 0));
                }
                else {
                    rl.setBackgroundColor(Color.rgb(229, 96, 9));
                }
            }
    }
}
