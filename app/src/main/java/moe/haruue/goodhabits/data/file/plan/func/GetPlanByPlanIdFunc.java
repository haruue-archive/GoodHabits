package moe.haruue.goodhabits.data.file.plan.func;

import java.util.ArrayList;
import java.util.List;

import moe.haruue.goodhabits.data.file.plan.PlanFileStorage;
import moe.haruue.goodhabits.model.Plan;
import rx.functions.Func1;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class GetPlanByPlanIdFunc implements Func1<Plan, List<Plan>> {
    @Override
    public List<Plan> call(Plan plan) {
        List<Plan> planList = new ArrayList<>(0);
        planList.add(PlanFileStorage.getInstance().getPlan(plan.planId));
        return planList;
    }
}
