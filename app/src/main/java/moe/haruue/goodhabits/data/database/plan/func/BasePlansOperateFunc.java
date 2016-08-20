package moe.haruue.goodhabits.data.database.plan.func;

import android.content.ContentValues;

import com.alibaba.fastjson.JSON;
import com.squareup.sqlbrite.BriteDatabase;

import java.util.List;

import moe.haruue.goodhabits.data.database.plan.PlanDataBase;
import moe.haruue.goodhabits.model.Plan;
import rx.functions.Func1;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public abstract class BasePlansOperateFunc implements Func1<List<Plan>, List<Plan>> {

    @Override
    public List<Plan> call(List<Plan> plans) {
        BriteDatabase database = PlanDataBase.getInstance().getDatabase();
        BriteDatabase.Transaction transaction = database.newTransaction();
        try {
            beforeOperate(database, plans);
            onOperate(database, plans);
            afterOperate(database, plans);
            transaction.markSuccessful();
        } finally {
            transaction.end();
        }
        return plans;
    }

    protected ContentValues contentValuesOf(Plan plan) {
        ContentValues values = new ContentValues();
        values.put(PlanDataBase.COLUMN_NAME_TITLE, plan.title);
        values.put(PlanDataBase.COLUMN_NAME_HINT, plan.hint);
        values.put(PlanDataBase.COLUMN_NAME_CONTENT, plan.content);
        values.put(PlanDataBase.COLUMN_NAME_PLAN_ID, plan.planId);
        values.put(PlanDataBase.COLUMN_NAME_STEPS, JSON.toJSONString(plan.steps.toArray()));
        values.put(PlanDataBase.COLUMN_NAME_IMAGE_URL, plan.imageUrl);
        values.put(PlanDataBase.COLUMN_NAME_AUTHOR, plan.author);
        values.put(PlanDataBase.COLUMN_NAME_IS_DOING, plan.isDoing ? 1 : 0);
        return values;
    }

    protected abstract void onOperateForSingle(BriteDatabase database, Plan p);

    protected void onOperate(BriteDatabase database, List<Plan> plans) {
        for (Plan t: plans) {
            onOperateForSingle(database, t);
        }
    }

    protected void beforeOperate(BriteDatabase database, List<Plan> plans) {

    }

    protected void afterOperate(BriteDatabase database, List<Plan> plans) {

    }
}
