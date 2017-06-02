package com.example.lane.hangman;


public class ThemeSaver {
    private static ThemeSaver themeSaver;
    private static boolean Light;
    private final static String LIGHT = "LIGHT";
    private final static String DARK = "DARK";
    private ThemeSaver(){
        Light = true;
    }
    public static void changeTheme(String theme) {
        if (theme.equals("light")) {
            Light = true;
        } else if (theme.equals("dark")) {
            Light = false;
        }

    }

    public static String getTheme() {
        if(Light){
            return LIGHT;
        }
        else{
            return DARK;
        }
    }
    public static void releaseThemeSaverInstance(){
        themeSaver = null;
    }
    public static ThemeSaver checkInstance(){
        return themeSaver;
    }

    public static ThemeSaver getInstance() {
        if(themeSaver == null){
            themeSaver = new ThemeSaver();
        }
        return(themeSaver);
    }

}
