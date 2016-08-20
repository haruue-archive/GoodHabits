package moe.haruue.goodhabits.data.database.plan.func;

import moe.haruue.goodhabits.data.database.plan.PlanDataBase;
import moe.haruue.goodhabits.model.Plan;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class PlansByIdQueryFunc extends BasePlansQueryFunc {
    @Override
    protected String querySql() {
        return "SELECT * FROM " + PlanDataBase.PLAN_TABLE_NAME + " WHERE " + PlanDataBase.COLUMN_NAME_PLAN_ID + "=?";
    }

    @Override
    protected String[] queryArguments(Plan p) {
        return new String[] {
            p.planId
        };
    }
}
