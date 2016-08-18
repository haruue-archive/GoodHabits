package moe.haruue.goodhabits.ui.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.jude.utils.JUtils;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class NavigationBarMarginView extends View {
    public NavigationBarMarginView(Context context) {
        super(context);
    }

    public NavigationBarMarginView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NavigationBarMarginView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec), JUtils.getNavigationBarHeight());
        } else {
            setMeasuredDimension(0, 0);
        }
    }
}
