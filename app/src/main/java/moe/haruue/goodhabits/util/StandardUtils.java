package moe.haruue.goodhabits.util;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class StandardUtils {

    public static <T> T checkNullWithDefaultValue(T object, T defaultValue) {
        return object == null ? defaultValue : object;
    }

}
