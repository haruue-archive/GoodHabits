package moe.haruue.goodhabits.data.func;

import java.util.List;

import moe.haruue.goodhabits.model.Task;
import moe.haruue.goodhabits.model.TaskPlan;
import rx.functions.Func1;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class TaskPlanToTasksFunc implements Func1<TaskPlan, List<Task>> {
    @Override
    public List<Task> call(TaskPlan taskPlan) {
        return taskPlan.tasks;
    }
}
