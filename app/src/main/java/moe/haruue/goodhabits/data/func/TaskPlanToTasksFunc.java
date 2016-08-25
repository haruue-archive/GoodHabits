package moe.haruue.goodhabits.data.func;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import moe.haruue.goodhabits.model.Task;
import moe.haruue.goodhabits.model.TaskCreator;
import moe.haruue.goodhabits.model.TaskPlan;
import moe.haruue.goodhabits.util.TimeUtils;
import rx.functions.Func1;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class TaskPlanToTasksFunc implements Func1<TaskPlan, List<Task>> {

    long planStart;
    long planEnd;
    long taskCreatorStart;
    long taskCreatorEnd;
    String planId;

    @Override
    public List<Task> call(TaskPlan taskPlan) {
        planStart = taskPlan.timeRangeStart;
        planEnd = taskPlan.timeRangeEnd;
        taskCreatorStart = taskPlan.timeRangeStart;
        taskCreatorEnd = taskPlan.timeRangeEnd;
        planId = taskPlan.planId;
        List<Task> tasks = new ArrayList<>(0);
        for (TaskCreator c: taskPlan.taskCreators) {
            Calendar startDay = new GregorianCalendar();
            startDay.setTimeInMillis(taskCreatorStart * 1000);
            addTask(tasks, c, startDay);
            taskCreatorStart += 86400 * c.count;
        }
        return tasks;
    }

    /**
     * 批量增加 Tasks
     * @param metaTask 提供 task 的元数据（比如 title, content, ...）
     * @param startTime 在一天中的开始时间
     * @param timeLength Task 的长度
     * @param beginCalendar 开始的日期
     * @param count 数量
     * @param dayInterval 后一个 Task 所在天减去前一个 Task 所在天
     */
    public void addTask(List<Task> tasks, Task metaTask, long startTime, long timeLength, Calendar beginCalendar, int count, int dayInterval) {
        beginCalendar = (Calendar) beginCalendar.clone();
        String planIdPostfix = "$" + planStart + "$" + planEnd;
        for (int i = 0; i < count; i++) {
            Task task = metaTask.clone();
            task.plan = planId + planIdPostfix;
            task.startTime = TimeUtils.getTimeStampOf(TimeUtils.getDayStartOf(beginCalendar)) + startTime;
            task.endTime = task.startTime + timeLength;
            tasks.add(task);
            beginCalendar.add(Calendar.DAY_OF_MONTH, dayInterval);
        }
    }

    public void addTask(List<Task> tasks, TaskCreator creator, Calendar beginCalendar) {
        addTask(tasks, creator.metaTask, creator.startTime, creator.timeLength, beginCalendar, creator.count, creator.dayInterval);
    }

}
