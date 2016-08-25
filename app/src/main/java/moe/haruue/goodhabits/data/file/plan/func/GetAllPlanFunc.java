package moe.haruue.goodhabits.data.file.plan.func;

import java.util.List;

import moe.haruue.goodhabits.data.file.plan.PlanFileStorage;
import moe.haruue.goodhabits.model.Plan;
import rx.functions.Func1;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class GetAllPlanFunc implements Func1<Plan, List<Plan>> {
    @Override
    public List<Plan> call(Plan plan) {
        return PlanFileStorage.getInstance().getAllPlan();
    }
}
