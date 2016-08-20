package moe.haruue.goodhabits.data.database.plan.func;

import com.squareup.sqlbrite.BriteDatabase;

import moe.haruue.goodhabits.data.database.plan.PlanDataBase;
import moe.haruue.goodhabits.model.Plan;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */
public class InsertPlansOperateFunc extends BasePlansOperateFunc {
    @Override
    protected void onOperateForSingle(BriteDatabase database, Plan p) {
        database.insert(PlanDataBase.PLAN_TABLE_NAME, contentValuesOf(p));
    }

}
