package moe.haruue.goodhabits.util;

import android.content.res.Resources;
import android.support.annotation.RawRes;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class ResourceUtils {
    public static String readStringFromRawResource(Resources resources, @RawRes int resId) {
        InputStream in = resources.openRawResource(resId);
        InputStreamReader reader = new InputStreamReader(in);
        char[] flush = new char[10];
        int length;
        StringBuilder sb = new StringBuilder();
        try {
            while (-1 != (length = reader.read(flush))) {
                sb.append(flush, 0, length);
            }
        } catch (Throwable t) {
            Log.e("ResourceUtils", "readStringFromRawResource", t);
        }
        return sb.toString();
    }
}
