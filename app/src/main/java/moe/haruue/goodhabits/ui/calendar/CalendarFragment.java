package moe.haruue.goodhabits.ui.calendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import moe.haruue.goodhabits.R;
import moe.haruue.goodhabits.ui.BaseFragment;

/**
 * MainActivity 的第 2 个 tab
 * TODO: 20160805 - tab2 日历，显示坚持计划的时间，荣誉列表（当前计划进行情况）
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class CalendarFragment extends BaseFragment implements CalendarContract.View {

    private CalendarContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(CalendarContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
