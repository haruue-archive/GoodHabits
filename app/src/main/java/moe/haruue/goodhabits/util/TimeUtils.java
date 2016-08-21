package moe.haruue.goodhabits.util;

import java.util.Calendar;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class TimeUtils {

    /**
     * 取北京时间当天 0 点的时间戳
     * @param timeStamp 北京时间某一天任意时间的时间戳
     * @return 当天 0 点的时间戳
     */
    public static long timeStampToDayStartCCT(long timeStamp) {
        timeStamp += 28800;
        timeStamp = timeStamp / 86400 * 86400;
        return timeStamp - 28800;
    }

    /**
     * 取北京时间当天 0 点到给定时间戳的秒数
     * @param timeStamp 给定时间戳
     * @return 结果
     */
    public static long getSecondOfDayCCT(long timeStamp) {
        return timeStamp - timeStampToDayStartCCT(timeStamp);
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

}
