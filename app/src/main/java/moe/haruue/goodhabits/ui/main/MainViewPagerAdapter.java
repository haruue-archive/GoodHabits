package moe.haruue.goodhabits.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import moe.haruue.goodhabits.ui.calendar.CalendarFragment;
import moe.haruue.goodhabits.ui.calendar.CalendarPresneter;
import moe.haruue.goodhabits.ui.square.SquareFragment;
import moe.haruue.goodhabits.ui.square.SquarePresenter;
import moe.haruue.goodhabits.ui.task.TaskFragment;
import moe.haruue.goodhabits.ui.task.TaskPresenter;

/**
 * MainActivity 中的 ViewPager 使用的 Adapter
 *
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
                TaskFragment taskFragment = new TaskFragment();
                new TaskPresenter(taskFragment);
                return taskFragment;
            case TAB_ID_CALENDAR:
                CalendarFragment calendarFragment = new CalendarFragment();
                new CalendarPresneter(calendarFragment);
                return calendarFragment;
            case TAB_ID_SQUARE:
                SquareFragment squareFragment = new SquareFragment();
                new SquarePresenter(squareFragment);
                return squareFragment;
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
                return "今天";
            case TAB_ID_CALENDAR:
                return "统计";
            case TAB_ID_SQUARE:
                return "旅程";
            default:
                return "";
        }
    }
}
