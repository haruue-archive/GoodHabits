package moe.haruue.goodhabits;

import org.junit.Test;

import java.util.GregorianCalendar;

import moe.haruue.goodhabits.util.TimeUtils;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void instanceof_isCorrect() throws Exception {
        System.out.println(null instanceof String);
    }

    @Test
    public void getTodayStart() throws Exception {
        System.out.println(TimeUtils.getTimeStampOf(TimeUtils.getDayStartOf(new GregorianCalendar())));
    }
}