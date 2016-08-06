package moe.haruue.goodhabits.util;

import android.app.Application;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.view.View;

/**
 * 资源加载器
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class ResourcesLoader {

    private static Application application;

    public static void initialize(Application application) {
        ResourcesLoader.application = application;
    }

    public static Application getApplication() {
        return application;
    }

    public static String getString(@StringRes int id) {
        return application.getString(id);
    }

    public static <T extends View> T findViewById(View parent, @IdRes int resId) {
        return (T) parent.findViewById(resId);
    }
}
