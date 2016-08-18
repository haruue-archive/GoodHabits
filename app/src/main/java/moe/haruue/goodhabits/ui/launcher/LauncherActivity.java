package moe.haruue.goodhabits.ui.launcher;

import android.os.Bundle;

import moe.haruue.goodhabits.data.FirstTime;
import moe.haruue.goodhabits.ui.activity.MainActivity;
import moe.haruue.goodhabits.ui.guide.GuideActivity;
import moe.haruue.goodhabits.ui.login.LoginActivity;
import moe.haruue.goodhabits.model.CurrentUser;

/**
 * 启动 Activity ，用于判断启动时显示哪一个页面，不应该在这个 Activity 里显示任何东西
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */
public class LauncherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (FirstTime.isFirstTimeStart()) {
            GuideActivity.start(this);
        } else if (!CurrentUser.getInstance().isLogin()) {
            LoginActivity.start(this);
        } else {
            MainActivity.start(this);
        }
        FirstTime.afterStart();
        finish();
    }
}
