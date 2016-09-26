package moe.haruue.goodhabits.ui.calendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import moe.haruue.goodhabits.R;

/**
 * Created by simonla on 2016/8/22.
 * Have a good day.
 */
public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.MyHolder> {

    private ArrayList<Boolean> mBooleen;
    private Context mContext;
    private HashMap mHashMap;
    private int[] mDate;

    private int mStart;
    private int mEnd;

    public static final String TAG = "MyAdapter";

    public CalendarAdapter(ArrayList<Boolean> boole, Context context, HashMap hashMap) {
        mContext = context;
        mBooleen = boole;
        mStart = getStart();
        mEnd = getEnd();
        mHashMap = hashMap;
        mDate = new int[50];
        for (int i = 0; i < 50; i++) {
            mDate[i] = i + 1;
        }
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(mContext).inflate(R.layout.item_calendal_day, parent, false));
    }

    // TODO: 2016/8/24 太烂得改
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        CalendarTextView tv = holder.mCalendarTextView;
        tv.setTextColor(mContext.getResources().getColor(R.color.material_color_white));
        switch (position) {
            case 0:
                tv.setText("日");
                break;
            case 1:
                tv.setText("一");
                break;
            case 2:
                tv.setText("二");
                break;
            case 3:
                tv.setText("三");
                break;
            case 4:
                tv.setText("四");
                break;
            case 5:
                tv.setText("五");
                break;
            case 6:
                tv.setText("六");
                break;
        }
        if (mHashMap != null) {
            if (position >= 7) {
                int position1 = position - 7 + 1;
                if ((position1 >= 0 && position1 < mStart) || (position1 >= mEnd + mStart)) {
                    tv.setText("");
                } else {
                    if (position1 > mHashMap.size()) {
                        tv.setFinish(false);
                    } else {
                        tv.setFinish((Boolean) mHashMap.get(position1));
                    }
                    Log.d(TAG, "onBindViewHolder: pos=====>>" + (position1 - mStart));
                    tv.setText(mDate[position1 - mStart] + "");
                }
            }
        }
    }

    private int getEnd() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(GregorianCalendar.MONTH) + 1;
        int year = calendar.get(GregorianCalendar.YEAR);
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            return 31;
        } else {
            if (month == 2) {
                if (year % 4 == 0 && year % 100 != 0) {
                    return 29;
                } else {
                    return 28;
                }
            } else {
                return 30;
            }
        }
    }

    private int getStart() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(GregorianCalendar.DAY_OF_WEEK);
        int dayOfMonth = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        if (dayOfWeek - dayOfMonth % 7 <= 0) {
            return dayOfWeek+1;
        } else {
            return dayOfWeek - dayOfMonth % 7+1;
        }
    }

    @Override
    public int getItemCount() {
        return mBooleen.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        public CalendarTextView mCalendarTextView;

        public MyHolder(View itemView) {
            super(itemView);
            mCalendarTextView = (CalendarTextView) itemView.getRootView();
        }
    }
}
