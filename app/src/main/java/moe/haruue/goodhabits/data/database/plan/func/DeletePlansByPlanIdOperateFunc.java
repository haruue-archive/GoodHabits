package moe.haruue.goodhabits.data.database.plan.func;

import com.squareup.sqlbrite.BriteDatabase;

import moe.haruue.goodhabits.data.database.plan.PlanDataBase;
import moe.haruue.goodhabits.model.Plan;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class DeletePlansByPlanIdOperateFunc extends BasePlansOperateFunc {
    @Override
    protected void onOperateForSingle(BriteDatabase database, Plan p) {
        database.delete(PlanDataBase.PLAN_TABLE_NAME, PlanDataBase.COLUMN_NAME_PLAN_ID + "=?", p.planId + "");
    }
}
