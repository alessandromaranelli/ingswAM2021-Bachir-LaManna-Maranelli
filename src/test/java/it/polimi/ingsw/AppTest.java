package it.polimi.ingsw;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Locale;
import java.util.Random;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void nextString() {
        final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        final String lower = upper.toLowerCase(Locale.ROOT);

        final String digits = "0123456789";

        final String alphanum = upper + lower + digits;

        final char []symbols =alphanum.toCharArray();
        final char[] buf=new char[10];
        final Random random=new Random();
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        System.out.println(new String(buf));
    }
}
