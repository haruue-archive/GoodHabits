package moe.haruue.goodhabits.data.func;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import moe.haruue.goodhabits.config.Const;
import moe.haruue.goodhabits.data.database.task.func.TaskAllQueryFunc;
import moe.haruue.goodhabits.model.Step;
import moe.haruue.goodhabits.model.StepPlan;
import moe.haruue.goodhabits.model.Task;
import rx.functions.Func1;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class StepPlanToTasksFunc implements Func1<StepPlan, List<Task>> {

    // time range
    private long planStart;
    private long planEnd;
    private long stepStart;
    private long stepEnd;
    private String planId;

    @Override
    public List<Task> call(StepPlan stepPlan) {
        planStart = stepPlan.timeRangeStart;
        planEnd = stepPlan.timeRangeEnd;
        stepStart = stepPlan.timeRangeStart;
        stepEnd = stepPlan.timeRangeEnd;
        planId = stepPlan.planId;
        List<Task> origTasks = new TaskAllQueryFunc().call(null);
        Collections.sort(origTasks);
        List<Task> workTasks = new ArrayList<>(origTasks);
        for (Step s: stepPlan.steps) {
            singleStepToTask(s, workTasks);
        }
        return makeDiff(workTasks, origTasks, ArrayList.class);
    }

    private void singleStepToTask(Step step, List<Task> tasks) {
        for (int i = 0; i < step.period; i++) {
            arrangeTask(step, tasks);
        }
    }

    private void arrangeTask(Step step, List<Task> tasks) {
        Task task = copyValueFrom(step);
        // getFreeTimeList
        List<FreeTime> freeTimeList = getFreeTimeListOf(tasks);
        // time arrange
        for (FreeTime f: freeTimeList) {
            if (f.isValid && step.isFitStartTime(f.start, f.getLength())) {
                task.startTime = f.start;
                if (f.getLength() > step.maxTime) {
                    task.endTime = task.startTime + step.maxTime;
                } else {
                    task.endTime = task.startTime + f.getLength();
                }
                tasks.add(task);
                planStart += step.skipTime;
                break;
            }
        }

    }

    private Task copyValueFrom(Step step) {
        Task task = new Task();
        task.title = step.title;
        task.content = step.content;
        task.type = Const.TASK_TYPE_AUTO_PLAN;
        task.plan = planId + "$" + planStart + "$" + planEnd;
        task.imageUrl = step.imageUrl;
        task.note = step.defaultNote;
        return task;
    }

    private List<FreeTime> getFreeTimeListOf(List<Task> tasks) {
        List<Task> workTasks = new ArrayList<>(tasks);
        for (Task t: tasks) {
            if (t.endTime < stepStart || t.startTime > stepEnd) {
                workTasks.remove(t);
            }
        }
        List<FreeTime> times = new ArrayList<>(0);
        if (workTasks.isEmpty()) {
            times.add(new FreeTime(stepStart, stepEnd));
            return times;
        }
        times.add(new FreeTime(stepStart, tasks.get(0).startTime));
        if (workTasks.size() == 1) {
            times.add(new FreeTime(tasks.get(0).endTime, stepEnd));
            return times;
        }
        for (int i = 1; i < workTasks.size(); i++) {
            times.add(new FreeTime(tasks.get(i - 1).endTime, tasks.get(i).startTime));
        }
        times.add(new FreeTime(tasks.get(tasks.size() - 1).endTime, stepEnd));
        return times;
    }

    private class FreeTime {

        FreeTime(long start, long end) {
            this.start = start;
            this.end = end;
            isValid = start < end;
        }

        boolean isValid;
        long start;
        long end;

        long getLength() {
            return end - start;
        }
    }

    private static <T> T getTheLast(List<T> c) {
        return c.get(c.size() - 1);
    }

    /**
     * 找出 c1 - c2 即 c1 中 c2 没有的部分
     * @param c1 被减集合
     * @param c2 减集合
     * @param <T> 数据类型
     * @param <C1> c1 的集合类型
     * @param <C2> c2 的集合类型
     * @param <CR> 返回值的集合类型
     * @return 包含 c1 - c2 结果的 CR 类型的集合
     */
    private <C1 extends Collection<T>, C2 extends Collection<T>, CR extends Collection<T>, T> CR makeDiff(C1 c1, C2 c2, Class<CR> clazz) {
        CR result;
        try {
            result = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        for (T t: c1) {
            if (!c2.contains(t)) {
                result.add(t);
            }
        }
        return result;
    }

}
