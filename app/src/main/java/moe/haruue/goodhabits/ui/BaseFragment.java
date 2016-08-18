package moe.haruue.goodhabits.ui;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Fragment 公用方法
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class BaseFragment extends Fragment {


    /**
     * 替代 findViewById 更方便地 find View
     * @param res 需要 find 的 Id
     * @param <T> View 类型，自动强制类型转换
     * @return 强制类型转换好的 View
     */
    protected <T extends View> T $(@IdRes int res) {
        return (T) getView().findViewById(res);
    }


}
