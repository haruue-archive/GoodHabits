package moe.haruue.goodhabits.data;

import android.content.SharedPreferences;

import java.util.GregorianCalendar;

import moe.haruue.goodhabits.App;
import moe.haruue.goodhabits.util.TimeUtils;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class FirstTime {

    private final static String SHARED_PREFERENCES_KEY_IS_FIRST_TIME = "is_first_time_login";
    private final static String SHARED_PREFERENCES_KEY_FIRST_TIME_TIMESTAMP = "first_time_timestamp";

    public static boolean isFirstTimeStart() {
        SharedPreferences sharedPreferences = App.getCommonSharedPreferences();
        return sharedPreferences.getBoolean(SHARED_PREFERENCES_KEY_IS_FIRST_TIME, true);
    }

    public static void afterStart() {
        SharedPreferences sharedPreferences = App.getCommonSharedPreferences();
        if (isFirstTimeStart()) {
            sharedPreferences.edit().putBoolean(SHARED_PREFERENCES_KEY_IS_FIRST_TIME, false).apply();
            sharedPreferences.edit().putLong(SHARED_PREFERENCES_KEY_FIRST_TIME_TIMESTAMP, TimeUtils.getTimeStampOf(new GregorianCalendar())).apply();
        }
    }

    public static long getFirstTimeLoginTimeStamp() {
        SharedPreferences sharedPreferences = App.getCommonSharedPreferences();
        return sharedPreferences.getLong(SHARED_PREFERENCES_KEY_FIRST_TIME_TIMESTAMP, 0);
    }

}
