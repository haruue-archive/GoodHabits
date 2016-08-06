package moe.haruue.goodhabits;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.jude.utils.JActivityManager;
import com.jude.utils.JUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import im.fir.sdk.FIR;
import moe.haruue.goodhabits.util.ResourcesLoader;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // JUtils
        JUtils.initialize(this);
        JUtils.setDebug(BuildConfig.DEBUG, BuildConfig.APPLICATION_ID);
        registerActivityLifecycleCallbacks(JActivityManager.getActivityLifecycleCallbacks());
        // ResourceLoader
        ResourcesLoader.initialize(this);
        // BugHD
        FIR.init(this);
        // LeanCloud
        AVOSCloud.initialize(this, "OLR6WcgAdRG09PKf2dnkN4ab-gzGzoHsz", "IxmvxbCw28dGnQNpbLFQsjHt");
        // Universal-Image-Loader
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
