package moe.haruue.goodhabits.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import moe.haruue.goodhabits.R;
import moe.haruue.goodhabits.ui.fragment.CalendarFragment;
import moe.haruue.goodhabits.ui.fragment.SquareFragment;
import moe.haruue.goodhabits.ui.task.TaskFragment;
import moe.haruue.goodhabits.util.ResourcesLoader;

/**
 * MainActivity 中的 ViewPager 使用的 Adapter
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    public final static int TAB_ID_TASK = 0;
    public final static int TAB_ID_CALENDAR = 1;
    public final static int TAB_ID_SQUARE = 2;



    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case TAB_ID_TASK:
                return new TaskFragment();
            case TAB_ID_CALENDAR:
                return new CalendarFragment();
            case TAB_ID_SQUARE:
                return new SquareFragment();
            default:
                return new Fragment();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case TAB_ID_TASK:
                return ResourcesLoader.getString(R.string.task);
            case TAB_ID_CALENDAR:
                return ResourcesLoader.getString(R.string.calendar);
            case TAB_ID_SQUARE:
                return ResourcesLoader.getString(R.string.square);
            default:
                return "";
        }
    }
}
