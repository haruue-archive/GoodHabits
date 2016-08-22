package moe.haruue.goodhabits.ui.calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import moe.haruue.goodhabits.R;

/**
 * Created by simonla on 2016/8/21.
 * Have a good day.
 */
public class CalendarTextView extends TextView {

    private boolean mIsFinish;
    private Paint mPaint;
    public static final String TAG = "CalendarTextView";

    public void setFinish(boolean finish) {
        mIsFinish = finish;
    }

    public CalendarTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalendarTextView(Context context) {
        super(context);
        init();
    }

    public CalendarTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mIsFinish) {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setAlpha(200);
            mPaint.setAntiAlias(true);
            mPaint.setColor(getResources().getColor(R.color.material_color_green_A400));
            Log.d(TAG, "CalendarTextView: "+mIsFinish);
            canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, (getMeasuredWidth() / 2 + getMeasuredHeight() / 2) / 2, mPaint);
            canvas.save();
            super.onDraw(canvas);
            canvas.restore();
        } else {
            super.onDraw(canvas);
        }
    }
}
