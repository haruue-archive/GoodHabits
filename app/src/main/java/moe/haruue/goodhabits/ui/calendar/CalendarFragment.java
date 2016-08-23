package moe.haruue.goodhabits.ui.calendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textView = (TextView) view.findViewById(R.id.tv_calendar_title);
        Calendar calendar = Calendar.getInstance();
        textView.setText(calendar.get(GregorianCalendar.MONTH)+1+"月"+
                calendar.get(GregorianCalendar.DAY_OF_MONTH)+"日");

        // TODO: 2016/8/22 theckout data
        ArrayList<Boolean> arrayList = new ArrayList<>();
        for (int i = 0; i < 42; i++) {
            if (i % 2 == 0) {
                arrayList.add(i, true);
            } else {
                arrayList.add(i, false);
            }
        }

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_calendar);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 7));
        rv.setAdapter(new CalendarAdapter(arrayList, getContext()));
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
