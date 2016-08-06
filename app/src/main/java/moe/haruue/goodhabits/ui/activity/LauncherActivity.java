package moe.haruue.goodhabits.ui.activity;

import android.os.Bundle;

/**
 * 启动 Activity ，用于判断启动时显示哪一个页面，不应该在这个 Activity 里显示任何东西
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */
public class LauncherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 此处进行逻辑判断，第一次启动显示介绍页面，后面启动，如果未登录显示登录页面，如果已登录显示 MainActivity
        MainActivity.start(this);
        finish();
    }
}
