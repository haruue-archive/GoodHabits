package moe.haruue.goodhabits.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Activity 公用方法
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 替代 findViewById 更方便地 find View
     * @param res 需要 find 的 Id
     * @param <T> View 类型，自动强制类型转换
     * @return 强制类型转换好的 View
     */
    protected <T extends View> T $(@IdRes int res) {
        return (T) findViewById(res);
    }

    /**
     * 获取 Content View
     * @return ContentView
     */
    public View getContentView() {
        return getWindow().getDecorView().findViewById(android.R.id.content);
    }

}
