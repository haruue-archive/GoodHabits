package moe.haruue.goodhabits.data.func;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import moe.haruue.goodhabits.data.database.task.func.TasksByTimeQueryFunc;
import moe.haruue.goodhabits.model.Plan;
import moe.haruue.goodhabits.model.Step;
import moe.haruue.goodhabits.model.Task;
import moe.haruue.goodhabits.util.TimeUtils;
import rx.functions.Func1;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class PlanToTasksFunc implements Func1<Plan, List<Task>> {

    private Plan plan;

    @Override
    public List<Task> call(Plan plan) {
        List<Task> tasks = new ArrayList<>();
        this.plan = plan;
        int period = plan.steps.size();
        long unitTime = (plan.endTime - plan.startTime) / period;
        long stepStartTime = plan.startTime;
        for (Step s: plan.steps) {
            Task t = singleStepToTask(s, stepStartTime, stepStartTime + unitTime);
            if (t != null) {
                tasks.add(t);
            }
            stepStartTime += unitTime;
        }
        return tasks;
    }

    private Task singleStepToTask(Step step, long stepStartTime, long stepEndTime) {
        Task task = new Task();
        // inherit field
        task.title = step.title;
        task.content = step.content;
        task.plan = step.planId + "$" + (plan.startTime + "_" + plan.endTime);
        task.imageUrl = step.imageUrl;
        task.note = step.defaultNote;
        // time arrange
        List<Task> duringTask = new TasksByTimeQueryFunc()
                .call(Task.newEmptyTaskWithStartTimeAndEndTime(stepStartTime, stepEndTime));
        long startTime = step.noEarlyTime;
        long endTime = step.noLaterTime;
        return arrange(task, duringTask, startTime, endTime, stepStartTime, stepEndTime, step.minTime, step.maxTime);
    }

    private Task arrange(Task task, List<Task> duringTask, long noEarlyTime, long noLaterTime, long stepStartTime, long stepEndTime, long minTime, long maxTime) {
        duringTask = tasksInTime(duringTask, noEarlyTime, noLaterTime);
        List<TasksTimeMinus> duringTaskTimesMinus = new ArrayList<>(0);
        if (duringTask.size() == 0) {
            duringTaskTimesMinus.add(new TasksTimeMinus(stepEndTime, stepStartTime));
        } else {
            duringTaskTimesMinus.add(new TasksTimeMinus(duringTask.get(0), stepStartTime));
            for (int i = 0; i < duringTask.size() - 1; i++) {
                duringTaskTimesMinus.add(new TasksTimeMinus(duringTask.get(i + 1), duringTask.get(i)));
            }
            duringTaskTimesMinus.add(new TasksTimeMinus(stepEndTime, duringTask.get(duringTask.size() - 1)));
        }
        Collections.sort(duringTaskTimesMinus);
        TasksTimeMinus match = duringTaskTimesMinus.get(duringTaskTimesMinus.size() - 1);
        task.startTime = match.task1.endTime;
        if (maxTime < match.task2.startTime - match.task1.endTime) {
            task.endTime = task.startTime + maxTime;
        } else if (maxTime > match.task2.startTime - match.task1.endTime){
            task.endTime = match.task2.startTime;
        }
        if (task.endTime - task.startTime > 0) {
            return task;
        } else {
            return null;
        }
    }

    private List<Task> tasksInTime(List<Task> tasks, long noEarlyTime, long noLaterTime) {
        List<Task> newTasks = new ArrayList<>(0);
        for (Task t: tasks) {
            if (inRange(TimeUtils.getSecondOfDayCCT(t.startTime), noEarlyTime, noLaterTime) || inRange(TimeUtils.getSecondOfDayCCT(t.endTime), noEarlyTime, noLaterTime)) {
                newTasks.add(t);
            }
        }
        Collections.sort(newTasks);
        return newTasks;
    }

    private boolean inRange(long num, long min, long max) {
        return num > min && num < max;
    }

    private static class TasksTimeMinus implements Comparable<TasksTimeMinus> {

        Task task1;
        Task task2;
        long minus;

        TasksTimeMinus(Task task2, Task task1) {
            this.task1 = task1;
            this.task2 = task2;
            minus = task2.startTime - task1.endTime;
        }

        TasksTimeMinus(Task taskStart, long timeStart) {
            this(taskStart, generateStartTimeAsTask(timeStart));
        }

        TasksTimeMinus(long timeEnd, Task taskEnd) {
            this(generateEndTimeAsTask(timeEnd), taskEnd);
        }

        TasksTimeMinus(long timeEnd, long timeStart) {
            this(generateEndTimeAsTask(timeEnd), generateStartTimeAsTask(timeStart));
        }

        static Task generateStartTimeAsTask(long timeStart) {
            Task task = new Task();
            task.title = null;
            task.endTime = timeStart;
            return task;
        }

        static Task generateEndTimeAsTask(long timeEnd) {
            Task task = new Task();
            task.content = null;
            task.startTime = timeEnd;
            return task;
        }

        static boolean isStartTimeTask(Task task) {
            return task.title == null;
        }

        static boolean isEndTimeTask(Task task) {
            return task.content == null;
        }

        @Override
        public int compareTo(@NonNull TasksTimeMinus tasksTimeMinus) {
            if (minus < tasksTimeMinus.minus) return -1;
            else if (minus == tasksTimeMinus.minus) return 0;
            else return 1;
        }
    }

}
