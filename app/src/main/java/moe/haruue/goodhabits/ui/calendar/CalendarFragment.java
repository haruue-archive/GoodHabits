package moe.haruue.goodhabits.ui.calendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import moe.haruue.goodhabits.R;
import moe.haruue.goodhabits.ui.BaseFragment;

/**
 * MainActivity 的第 2 个 tab
 * TODO: 20160805 - tab2 日历，显示坚持计划的时间，荣誉列表（当前计划进行情况）
 *
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class CalendarFragment extends BaseFragment implements CalendarContract.View {

    @BindView(R.id.tv_calendar_title)
    TextView mTvCalendarTitle;
    @BindView(R.id.rv_calendar)
    RecyclerView mRvCalendar;
    @BindView(R.id.tv_pie_title)
    TextView mTvPieTitle;
    @BindView(R.id.pv_per_finish)
    PieView mPvPerFinish;
    @BindView(R.id.tv_pie_skip)
    TextView mTvPieSkip;
    @BindView(R.id.pv_per_skip)
    PieView mPvPerSkip;
    @BindView(R.id.tv_calendar_per_more_than)
    TextView mTvCalendarPerMoreThan;
    private CalendarContract.Presenter mPresenter;
    public static final String TAG = "CalendarFragment";
    private HashMap mHashMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserEvent(TaskFinishEvent event) {
        getFinisherPer();
        getSkipPer();
        getSkipMoreThanOthers();
        bindCalendarDate();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindCalendarDate();
        EventBus.getDefault().register(this);
        getFinisherPer();
        getSkipPer();
        getSkipMoreThanOthers();
    }

    private void getFinisherPer() {
        mPresenter.getFinishedPer(new CalendarContract.Callback() {
            @Override
            public void onFinish(int per) {
                mPvPerFinish.setPer(per);
                mPvPerFinish.setText(per + "%");
                mPvPerFinish.invalidate();
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "onError: " + error);
            }
        });
    }

    private void getSkipPer() {
        mPresenter.getSkipPer(new CalendarContract.Callback() {
            @Override
            public void onFinish(int per) {
                mPvPerSkip.setPer(per);
                mPvPerSkip.setText(per + "%");
                mPvPerSkip.invalidate();
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "onError: " + error);
            }
        });
    }

    private void getSkipMoreThanOthers() {
        mPresenter.getSkipMoreThanOthers(new CalendarContract.Callback() {
            @Override
            public void onFinish(int per) {
                if (per >= 50) {
                    mTvCalendarPerMoreThan.setText("好气啊，你居然超过了" + per + "%的人");
                } else {
                    mTvCalendarPerMoreThan.setText("可以的，有" + (100 - per) + "%的人逃得比你多");
                }
                mTvCalendarPerMoreThan.invalidate();
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "onError: " + error);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void bindCalendarDate() {
        Calendar calendar = Calendar.getInstance();
        mTvCalendarTitle.setText(calendar.get(GregorianCalendar.MONTH) + 1 + "月" +
                calendar.get(GregorianCalendar.DAY_OF_MONTH) + "日");

        ArrayList<Boolean> arrayList = new ArrayList<>();
        for (int i = 0; i < 49; i++) {
            if (i % 2 == 0) {
                arrayList.add(i, true);
            } else {
                arrayList.add(i, false);
            }
        }
        mRvCalendar.setLayoutManager(new GridLayoutManager(getContext(), 7));
        mPresenter.getFinishOfMonth(new CalendarContract.FinishDayCallback() {
            @Override
            public void onFinish(HashMap<Integer, Boolean> hashMap) {
                mHashMap = hashMap;
                mRvCalendar.setAdapter(new CalendarAdapter(arrayList, getContext(), mHashMap));
            }

            @Override
            public void onError(String error) {
                Log.d(TAG, "onError: " + error);
                mRvCalendar.setAdapter(new CalendarAdapter(arrayList, getContext(), null));
            }
        });

        mRvCalendar.invalidate();
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
