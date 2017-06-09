package com.example.lane.hangman;

import android.graphics.Color;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by lane on 5/30/17.
 */

public enum Colors {
        YELLOW, BLUE, RED, GREEN;

        private static List<Colors> COLORS = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = COLORS.size();
        private static final Random RANDOM = new Random();
        public static Colors getRandomColor(){
            return COLORS.get(RANDOM.nextInt(SIZE));
        }

        public static int getColorId(Colors color){
            switch (color){
                case YELLOW:
                    return Color.rgb(255, 182, 0);
                case BLUE:
                    return Color.BLUE;
                case RED:
                    return Color.RED;
                case GREEN:
                    return Color.GREEN;
                default:
                    return Color.BLUE;
            }
        }

}
