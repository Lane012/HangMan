package com.example.lane.hangman;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lane on 5/17/17.
 */

public class WordView extends RelativeLayout{
    ThemeSaver themeSaver;
    GradientDrawable g;
    GradientDrawable d;
    TextView characterView;
    int lettersGuessed;
    String word;
    String hint;
    ArrayList<Character> guessedLetters;

    public WordView(Context context){
        super(context);
        guessedLetters = new ArrayList<>();
        word = getRandomWord();
        hint = getHint();
        themeSaver = ThemeSaver.getInstance();
        g = (GradientDrawable) ContextCompat.getDrawable(context, R.drawable.wordview);
        d = (GradientDrawable) ContextCompat.getDrawable(context, R.drawable.darkwordview);
        this.setMinimumWidth(Resources.getSystem().getDisplayMetrics().widthPixels);
        this.setMinimumHeight(500);
        this.setClickable(false);
        this.setFocusable(false);
        this.setFocusableInTouchMode(false);
        this.setBackground(null);
        this.setX(0);
        this.setY(451);
        int x_axis;
        int y_axis = 31;
        x_axis = 0;
        for(int i = 0; i < word.length(); i++){
            characterView = new TextView(context);
            characterView.setX(x_axis);
            characterView.setY(y_axis);
            characterView.setTextSize(28);
            characterView.setText(Character.toString(Character.toUpperCase(word.charAt(i))));
            characterView.setTextAlignment(characterView.TEXT_ALIGNMENT_CENTER);
            characterView.setTextColor(Color.TRANSPARENT);
            characterView.setClickable(false);
            characterView.setFocusable(false);
            if(word.length() >= 10) {
                x_axis += 55;
                characterView.setHeight(25);
                characterView.setWidth(30);
            }
            else{
                x_axis += 65;
                characterView.setHeight(45);
                characterView.setWidth(40);
            }
            characterView.setFocusableInTouchMode(false);
            if(themeSaver.getTheme().equals("DARK")){
                characterView.setBackground(d);
            }
            else{
                characterView.setBackground(g);
            }
            this.addView(characterView);

        }
    }

    public boolean letterNotGuessed(Character letter){
        for(int i=0; i< guessedLetters.size(); i++){
            Character guessedLetter = guessedLetters.get(i);
            if(letter.compareTo(guessedLetter) == 0){
                return false;
            }
        }
        return true;

    }

    public String getRandomWord(){
        WordBank.createWordBank();
        return WordBank.getRandomWord();
    }
    public String getHint(){
        return WordBank.getHint(word);
    }

    public String getWord(){
        return this.word;
    }

    public boolean plugCharacterIn(Character letter){
        boolean match = false;
        guessedLetters.add(letter);
        for(int i=0; i < this.getChildCount(); i++) {
            TextView currentBox = (TextView) this.getChildAt(i);
            Character currentLetter = currentBox.getText().charAt(0);
            System.out.println(currentLetter + " " + letter);
            if(currentLetter.compareTo(letter) == 0) {
                match = true;
                countUpLettersGuessed();
                if (themeSaver.getTheme().equals("DARK")) {
                    currentBox.setTextColor(Color.WHITE);
                } else {
                    currentBox.setTextColor(Color.BLACK);
                }
            }
        }
        return match;

    }
    public WordView(Context context, AttributeSet a){
        super(context, a);
    }

    public void countUpLettersGuessed(){
        this.lettersGuessed++;
    }
    public boolean wordGuessed(){
        return lettersGuessed == this.getChildCount();
    }

}
