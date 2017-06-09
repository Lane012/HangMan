package com.example.lane.hangman;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
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
    RelativeLayout KeyBoard;
    LetterFetcher letterFetcher;
    PopupWindow popupWindow;
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
        setDrawable(R.drawable.usedbutton, "blackBackground");
        setDrawable(R.drawable.white, "whiteBackground");
        setDrawable(R.drawable.popupwindowblue, "bluePopUp");
        setDrawable(R.drawable.popupwindowyellow, "yellowPopUp");
        setDrawable(R.drawable.popupwindowred, "redPopUp");
        setDrawable(R.drawable.popupwindowgreen, "greenPopUp");
        setDrawable(R.drawable.greenbar, "greenBar");
        setDrawable(R.drawable.whitemenu, "white");
        setUpHangBackground();
        getAndSetColor();
        GradientDrawable menuBackground = Drawables.getGradientDrawable("white");
        menuBackground.setColor(Colors.getColorId(color));
        setContentView(R.layout.activity_game);
        setRelativeLayout();
        setMenuListener();
        setUpWordView();
        getAndSetScrollBar();
        setUpKeyBoard();
        rl.addView(wordView);
        letterFetcher = new LetterFetcher();
        letterFetcher.setWord(wordView.getWord());
        setupBackGround();
        setupHangMan();
    }
    @Override
    public void onBackPressed() {
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
        KeyBoard = (RelativeLayout) findViewById(R.id.keyboard);
    }

    public void showEndOfGamePopUp(){
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
        popupWindow = new PopupWindow(inflater.inflate(R.layout.end_of_game, null), 400,400, true);
        popupWindow.setBackgroundDrawable(keyboardBackGroundChooser());
        RelativeLayout rl = (RelativeLayout)popupWindow.getContentView();
        for(int i=2; i< rl.getChildCount(); i++){
            Button popUpButton = (Button)rl.getChildAt(i);
            setPopUpListener(popUpButton);
            popUpButton.setBackground(buttonBackgroundChooser());

        }
        for(int i=0; i < KeyBoard.getChildCount(); i++){
            Button currentButton = (Button)KeyBoard.getChildAt(i);
            currentButton.setOnClickListener(null);
        }
        if(!hangman.gameOver()){
            TextView message = (TextView)rl.getChildAt(0);
            message.setText("NICE JOB!");
            if(darkTheme()){
                message.setTextColor(Color.WHITE);
            }
            message.setX(80);
            message.setY(40);
        }
        else {
            TextView text = (TextView) rl.getChildAt(1);
            text.setText(wordView.getWord().toUpperCase());
        }
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(false);
        popupWindow.update();
        popupWindow.showAtLocation(rl, 0, 100, 400);
    }

    public void showMenuPrompt() {

        LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
        popupWindow = new PopupWindow(inflater.inflate(R.layout.end_of_game, null), 400,400, true);
        popupWindow.setBackgroundDrawable(keyboardBackGroundChooser());
        RelativeLayout rl = (RelativeLayout)popupWindow.getContentView();
        for(int i=2; i< rl.getChildCount(); i++){
            Button popUpButton = (Button)rl.getChildAt(i);
            if(i == 2){
                popUpButton.setText("YES");
            }
            else{
                popUpButton.setText("CANCEL");
            }
            setPopUpListener(popUpButton);
            popUpButton.setBackground(buttonBackgroundChooser());

        }
        for(int i=0; i < KeyBoard.getChildCount(); i++){
            Button currentButton = (Button)KeyBoard.getChildAt(i);
            currentButton.setOnClickListener(null);
        }
        TextView message = (TextView)rl.getChildAt(0);
        message.setText("ARE YOU SURE YOU WANT TO LEAVE THE GAME?");
        if(darkTheme()){
            message.setTextColor(Color.WHITE);
        }
        message.setX(0);
        message.setY(0);
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(false);
        popupWindow.update();
        popupWindow.showAtLocation(rl, 0, 100, 400);
    }




    public void getAndSetColor(){
        Bundle b = getIntent().getExtras();
        color = (Colors)b.getSerializable("color");
    }
    public void setMenuListener(){
        Button menu = (Button)rl.getChildAt(1);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenuPrompt();
            }

        });
    }


    public boolean compareGuessedLetterToSecretWord(Button b){
        boolean match = false;
        Character currentCharacter;
        if(!b.getText().equals("HINT") && !b.getText().equals("REVEAL A LETTER")) {
            currentCharacter = b.getText().charAt(0); // can't convert to character so we just grab the one letter as a character
        }
        else{                        // if hint or reveal a letter button is pressed
            currentCharacter = '1'; // assign a 1 because no word will contain one.
        }
        if (wordView.plugCharacterIn(currentCharacter)) {
            match = true;
        }
        return match;
    }

    public void setListener(final Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonSoundService.playButtonSound();
                Button buttonPressed = (Button)v;
                buttonPressed.setOnClickListener(null);
                if(!compareGuessedLetterToSecretWord(buttonPressed) && !buttonPressed.getText().equals("HINT") && !buttonPressed.getText().equals("REVEAL A LETTER")){
                    hangman.countUp();
                    updateUI.run();
                }
                else if(buttonPressed.getText().equals("REVEAL A LETTER")){
                    Character letter = LetterFetcher.getLetter(wordView);
                    wordView.plugCharacterIn(letter);
                    cancelButtonThatContainsLetteredRevealed(letter);
                    updateUI.run();
                }
                else if(buttonPressed.getText().equals("HINT")){
                    hangman.allowHint();
                    updateUI.run();
                }
                if(wordView.wordGuessed() || hangman.gameOver()){
                    showEndOfGamePopUp();
                }
                if(darkTheme()) {
                    v.setBackground(Drawables.getGradientDrawable("blackBackground"));
                }
                else{
                    v.setBackground(Drawables.getGradientDrawable("whiteBackground"));
                }
            }
        });
    }
    public void cancelButtonThatContainsLetteredRevealed(Character letter){
        for(int i=0; i< KeyBoard.getChildCount(); i++){
            TextView currentButton = (TextView)KeyBoard.getChildAt(i);
            if(letter.compareTo(currentButton.getText().charAt(0)) == 0){
                if(darkTheme()){
                    currentButton.setOnClickListener(null);
                    currentButton.setBackground(Drawables.getGradientDrawable("blackBackground"));
                }
                else{
                    currentButton.setOnClickListener(null);
                    currentButton.setBackground(Drawables.getGradientDrawable("whiteBackground"));
                }
            }
        }

    }
    public void setPopUpListener(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            Intent intent;
            @Override
            public void onClick(View v) {
                Button buttonClicked = (Button)v;
                if(buttonClicked.getText().equals("MAIN MENU") || buttonClicked.getText().equals("YES")){
                    musicPlayer.stop();
                    intent = new Intent(getApplicationContext(), hangMenu.class);
                    startActivity(intent);
                }
                else if(buttonClicked.getText().equals("CANCEL")){
                    for(int i=0; i < KeyBoard.getChildCount(); i++){
                        Button currentButton = (Button)KeyBoard.getChildAt(i);
                        setListener(currentButton);
                    }
                    popupWindow.dismiss();
                }

                else{
                    if(!musicPlayer.playerSilenced()){
                    musicPlayer.startNewSong(getBaseContext(), R.raw.food);
                    }
                    intent = new Intent(getApplicationContext(), HangGame.class);
                    Colors color;
                    while(true) {
                        color = Colors.getRandomColor();
                        int colorId = Colors.getColorId(color);

                        if(colorId != hangman.returnColor() ){
                            break;
                        }
                    }
                    Bundle b = new Bundle();
                    b.putSerializable("color", color);
                    intent.putExtras(b);
                    startActivity(intent);
                }
            }
        });
    }



    public void setUpKeyBoard(){
        for(int i=0; i < KeyBoard.getChildCount(); i++){
            Button currentButton = (Button)KeyBoard.getChildAt(i);
            setListener(currentButton);
            if(!darkTheme()){
                currentButton.setTextColor(Color.BLACK);
            }
            currentButton.setBackground(buttonBackgroundChooser());



        }
    }

    public GradientDrawable keyboardBackGroundChooser(){
        GradientDrawable keyboardBackground;
        switch(color){
            case YELLOW:
                keyboardBackground = Drawables.getGradientDrawable("yellowPopUp");
                break;
            case BLUE:
                keyboardBackground = Drawables.getGradientDrawable("bluePopUp");
                break;
            case RED:
                keyboardBackground = Drawables.getGradientDrawable("redPopUp");
                break;
            case GREEN:
                keyboardBackground = Drawables.getGradientDrawable("greenPopUp");
                break;
            default:
                keyboardBackground= Drawables.getGradientDrawable("yellowPopUp");
        }
        return keyboardBackground;

    }

    public GradientDrawable buttonBackgroundChooser(){
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
            case GREEN:
                buttonBarBackground = Drawables.getGradientDrawable("greenBar");
                break;
             default:
                 buttonBarBackground = Drawables.getGradientDrawable("greenBar");
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
                        rl.invalidate();
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
            else if (color.equals(Colors.YELLOW)){
                if (darkTheme()) {
                    rl.setBackgroundColor(Color.rgb(255, 204, 0));
                }
                else {
                    rl.setBackgroundColor(Color.rgb(229, 96, 9));
                }
            }
            else{
                if(darkTheme()){
                    rl.setBackgroundColor(Color.rgb(11, 76, 5));
                }
                else{
                    rl.setBackgroundColor(Color.GREEN);
                }
            }
    }
}
