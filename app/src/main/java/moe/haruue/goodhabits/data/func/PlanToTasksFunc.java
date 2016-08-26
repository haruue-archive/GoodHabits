package moe.haruue.goodhabits.data.func;

import java.util.List;

import moe.haruue.goodhabits.model.Plan;
import moe.haruue.goodhabits.model.StepPlan;
import moe.haruue.goodhabits.model.Task;
import moe.haruue.goodhabits.model.TaskPlan;
import rx.functions.Func1;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class PlanToTasksFunc implements Func1<Plan, List<Task>> {
    @Override
    public List<Task> call(Plan plan) {
        if (plan instanceof StepPlan) {
            return new StepPlanToTasksFunc().call((StepPlan) plan);
        }
        if (plan instanceof TaskPlan) {
            return new TaskPlanToTasksFunc().call((TaskPlan) plan);
        }
        throw new IllegalArgumentException("Unknown Plan Type: " + plan.getClass().getSimpleName() + ", it must be one of StepPlan and TaskPlan, or insert new plan type to PlanToTaskFunc");
    }
}
