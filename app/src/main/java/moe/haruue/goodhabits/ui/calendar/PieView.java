package moe.haruue.goodhabits.ui.calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.TextView;

import moe.haruue.goodhabits.R;

/**
 * Created by simonla on 2016/8/23.
 * Have a good day.
 */
public class PieView extends TextView {

    private Paint mPaint;
    private int mPer;
    public static final String TAG = "PieView";
    private Context mContext;

    public int getPer() {
        return mPer;
    }

    public void setPer(int per) {
        mPer = per;
    }

    public PieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PieView(Context context) {
        super(context);
        init(context);
    }

    public PieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST &&
                heightMeasureSpec == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, 200);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(200, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, 200);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        mPaint.reset();
        resetPaint(getResources().getColor(R.color.material_color_pink_600));
        drawArc(canvas, 20, 20, width - 20, height - 20, 0, perToSweep(mPer), true, mPaint);
        resetPaint(R.color.material_color_blue_gray_300);
        drawArc(canvas, 20, 20, width - 20, height - 20, perToSweep(mPer), 360 - perToSweep(mPer), true, mPaint);
        resetPaint(getResources().getColor(R.color.material_color_white));
        canvas.drawCircle(width / 2, height / 2, Math.min(width / 2, height / 2) - width / 6, mPaint);
        mPaint.reset();
        super.onDraw(canvas);
        mPaint.reset();
    }

    private void drawArc(Canvas canvas, float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, Paint paint) {
        RectF oval = new RectF(left, top, right, bottom);
        canvas.drawArc(oval, startAngle, sweepAngle, useCenter, paint);
    }

    private void resetPaint(int color) {
        mPaint.reset();
        mPaint.setColor(color);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    private float perToSweep(int per) {
        return (360 * per) / 100;
    }
}
