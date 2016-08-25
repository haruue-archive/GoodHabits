package moe.haruue.goodhabits.model;

import android.util.Log;

import java.io.Serializable;

import moe.haruue.goodhabits.util.TimeUtils;
import moe.haruue.goodhabits.util.WeatherUtils;

/**
 * Created by simonla on 2016/8/13.
 * Have a good day.
 */

public class Step extends BaseStep implements Cloneable, Serializable {

    // 完成这一步需要的最少时间，以秒计
    public long minTime;
    // 完成这一步需要的最多时间，以秒计
    public long maxTime;
    // 该活动的开始不能早于这个时间（一天中的秒数），设置成 0 不考虑（非常不推荐）
    public long noEarlyTime = 0;
    // 该活动的开始不能晚于这个时间（一天中的秒数），设置成 86400 不考虑（非常不推荐）
    public long noLaterTime = 86400;
    // 该活动必须是晴天
    public boolean requireSunnyDay;
    // 这一步骤需要进行的次数
    public int period;
    // 安排完这一步骤之后需要过多久才能安排下一次
    public long skipTime;

    public boolean isFitStartTime(long startTime, long timeLength) {
        if (timeLength < minTime) {
            return false;
        }
        long secondInDay = TimeUtils.getSecondOfDay(startTime);
        if (secondInDay < noEarlyTime || secondInDay > noLaterTime) {
            return false;
        }
        if (requireSunnyDay && !WeatherUtils.isSunny(startTime)) {
            return false;
        }
        return true;
    }

    @Override
    public Step clone() {
        Step step;
        try {
            step = (Step) super.clone();
        } catch (Throwable t) {
            Log.w("Step", "clone", t);
            step = new Step();
        }
        step.title = title;
        step.content = content;
        step.type = type;
        step.imageUrl = imageUrl;
        step.defaultNote = defaultNote;
        step.minTime = minTime;
        step.maxTime = maxTime;
        step.noEarlyTime = noEarlyTime;
        step.noLaterTime = noLaterTime;
        step.requireSunnyDay = requireSunnyDay;
        step.period = period;
        return step;
    }
}
