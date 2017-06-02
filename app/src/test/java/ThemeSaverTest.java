import com.example.lane.hangman.ThemeSaver;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
/**
 * Created by lane on 5/28/17.
 */


public class ThemeSaverTest {
    ThemeSaver themeSaver;
    @Before
    public void setup(){
        themeSaver = ThemeSaver.getInstance();
    }


    @Test
    public void TestThatThemeSaverChangeThemeMethodWorks(){
        themeSaver.changeTheme("dark");
        String expectedTheme = "DARK";
        assertEquals(expectedTheme, themeSaver.getTheme());
    }

    @Test
    public void TestThatThemeSaverReturnsSameInstanceWithSameTheme(){
        themeSaver.changeTheme("dark");
        ThemeSaver theme_one = ThemeSaver.getInstance();
        String expected = "DARK";
        assertEquals(expected, theme_one.getTheme());

        theme_one.changeTheme("light");
        expected = "LIGHT";
        assertEquals(expected, themeSaver.getTheme());
    }







}
