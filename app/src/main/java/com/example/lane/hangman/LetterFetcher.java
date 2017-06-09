package com.example.lane.hangman;

/**
 * Created by lane on 6/8/17.
 */

public class LetterFetcher {
    private static String secretWord;
    public LetterFetcher(){

    }
    public static void setWord(String word){
        secretWord = word.toUpperCase();

    }

    public static Character getLetter(WordView wordView){
        Character letter = '1';
        boolean done = false;
        while(!done) {
            letter = secretWord.charAt((int) (Math.random() * secretWord.length()));
            if (wordView.letterNotGuessed(letter)) {
                done = true;
            }
        }
        return letter;
    }

}
