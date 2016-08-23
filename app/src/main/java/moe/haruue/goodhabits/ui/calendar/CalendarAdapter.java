package moe.haruue.goodhabits.ui.calendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import moe.haruue.goodhabits.R;

/**
 * Created by simonla on 2016/8/22.
 * Have a good day.
 */
public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.MyHolder> {

    private ArrayList<Boolean> mBooleen;
    private Context mContext;

    private int statrt;
    private int end;

    public static final String TAG = "MyAdapter";

    public CalendarAdapter(ArrayList<Boolean> boole, Context context) {
        mContext = context;
        mBooleen = boole;
        statrt = getStart();
        end = getEnd();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(mContext).inflate(R.layout.item_calendal_day, parent, false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        CalendarTextView tv = holder.mCalendarTextView;
        switch (position) {
            case 0:
                tv.setText("日");
                tv.setTextColor(mContext.getResources().getColor(R.color.material_color_black));
                break;
            case 1:
                tv.setText("一");
                tv.setTextColor(mContext.getResources().getColor(R.color.material_color_black));
                break;
            case 2:
                tv.setText("二");
                tv.setTextColor(mContext.getResources().getColor(R.color.material_color_black));
                break;
            case 3:
                tv.setText("三");
                tv.setTextColor(mContext.getResources().getColor(R.color.material_color_black));
                break;
            case 4:
                tv.setText("四");
                tv.setTextColor(mContext.getResources().getColor(R.color.material_color_black));
                break;
            case 5:
                tv.setText("五");
                tv.setTextColor(mContext.getResources().getColor(R.color.material_color_black));
                break;
            case 6:
                tv.setText("六");
                tv.setTextColor(mContext.getResources().getColor(R.color.material_color_black));
                break;
        }
        if (position >= 7) {
            int position1 = position - 7;
            if ((position1 >= 0 && position1 < statrt) || (position1 > end)) {
                tv.setText("");
            } else {
                tv.setFinish(mBooleen.get(position1));
                tv.setText(String.valueOf(position1 - statrt+1));
            }
        }
    }

    private int getEnd() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(GregorianCalendar.MONTH);
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
            }
            return 30;
        }
    }

    private int getStart() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(GregorianCalendar.DAY_OF_WEEK);
        int dayOfMonth = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        return dayOfWeek-dayOfMonth%7;
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
