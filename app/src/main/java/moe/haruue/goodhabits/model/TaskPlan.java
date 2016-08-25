package moe.haruue.goodhabits.model;

import java.util.Calendar;
import java.util.List;

import moe.haruue.goodhabits.util.TimeUtils;

/**
 * 时间固定的 Plan
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class TaskPlan extends Plan<Task> {

    /**
     * 此 Plan 中拥有的 Task ，兼容性考虑
     */
    public List<Task> tasks = array;

    /**
     * 批量增加 Tasks
     * @param metaTask 提供 task 的元数据（比如 title, content, ...）
     * @param startTime 在一天中的开始时间
     * @param timeLength Task 的长度
     * @param beginCalendar 开始的日期
     * @param count 数量
     * @param dayInterval 后一个 Task 所在天减去前一个 Task 所在天
     */
    public void addTask(Task metaTask, long startTime, long timeLength, Calendar beginCalendar, int count, int dayInterval) {
        beginCalendar = (Calendar) beginCalendar.clone();
        String planIdPostfix = "$" + timeRangeStart + "$" + timeRangeEnd;
        for (int i = 0; i < count; i++) {
            Task task = metaTask.clone();
            task.plan = planId + planIdPostfix;
            task.startTime = TimeUtils.getTimeStampOf(TimeUtils.getDayStartOf(beginCalendar)) + startTime;
            task.endTime = task.startTime + timeLength;
            tasks.add(task);
            beginCalendar.add(Calendar.DAY_OF_MONTH, dayInterval);
        }
    }

    public TaskPlan() {
        super();
    }
}
