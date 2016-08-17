package moe.haruue.goodhabits.data;

import android.content.SharedPreferences;

import moe.haruue.goodhabits.App;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class FirstTime {

    private final static String SHARED_PREFERENCES_KEY = "is_first_time_login";

    public static boolean isFirstTimeStart() {
        SharedPreferences sharedPreferences = App.getCommonSharedPreferences();
        return sharedPreferences.getBoolean(SHARED_PREFERENCES_KEY, true);
    }

    public static void afterStart() {
        SharedPreferences sharedPreferences = App.getCommonSharedPreferences();
        sharedPreferences.edit().putBoolean(SHARED_PREFERENCES_KEY, false).apply();
    }

}
