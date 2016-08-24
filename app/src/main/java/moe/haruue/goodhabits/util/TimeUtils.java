package moe.haruue.goodhabits.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class TimeUtils {

    /**
     * 取北京时间当天 0 点的时间戳
     * @param timeStamp 北京时间某一天任意时间的时间戳
     * @return 当天 0 点的时间戳
     * @deprecated 用更加国际化的 {@link #timeStampToDayStart(long)} 取而代之
     */
    @Deprecated
    public static long timeStampToDayStartCCT(long timeStamp) {
        timeStamp += 28800;
        timeStamp = timeStamp / 86400 * 86400;
        return timeStamp - 28800;
    }

    /**
     * 取当地时区当天 0 点的时间戳
     * @param timeStamp 某一天的任意时间的时间戳
     * @return 当天 0 点的时间戳
     */
    public static long timeStampToDayStart(long timeStamp) {
        TimeUtils.getTimeStampOf(TimeUtils.getDayStartOf(timeStampToCalendar(timeStamp)));
    }

    /**
     * 构造对应时间戳的 Calendar 对象
     * @param timeStamp 时间戳
     * @return 对应时间戳的 Calendar 对象
     */
    public static Calendar timeStampToCalendar(long timeStamp) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(timeStamp * 1000);
        return calendar;
    }

    /**
     * 取北京时间当天 0 点到给定时间戳的秒数
     * @param timeStamp 给定时间戳
     * @return 结果
     * @deprecated 用更加国际化的 {@link #getSecondOfDay(long)} 取而代之
     */
    @Deprecated
    public static long getSecondOfDayCCT(long timeStamp) {
        return timeStamp - timeStampToDayStartCCT(timeStamp);
    }

    /**
     * 取当天 0 点到给定时间戳的秒数
     * @param timeStamp 给定时间戳
     * @return 结果
     */
    public static long getSecondOfDay(long timeStamp) {
        return timeStamp - timeStampToDayStart(timeStamp);
    }

    /**
     * 取以秒为单位的时间戳<br>
     *     <del>让我用以毫秒为单位的时间戳我是拒绝的</del>
     * @param calendar 需要取值的 {@link Calendar} 对象
     * @return 以秒为单位的时间戳
     */
    public static long getTimeStampOf(Calendar calendar) {
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获取当天 0 点的时间
     * @param origCalendar 当前时间
     * @return 当天 0 点的时间
     */
    public static Calendar getDayStartOf(Calendar origCalendar) {
        Calendar calendar = (Calendar) origCalendar.clone();
        return resetDayTimeToStart(calendar);
    }

    /**
     * 将传入的 calendar 对象的时间重设到当天的 0 点
     * @param calendar 传入的 calendar 对象
     * @return 被设置为当天 0 点的 calendar 对象
     */
    public static Calendar resetDayTimeToStart(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }


}
