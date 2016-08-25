package moe.haruue.goodhabits.data.database.plan.func;

import android.database.Cursor;

import com.alibaba.fastjson.JSON;
import com.squareup.sqlbrite.BriteDatabase;

import java.util.ArrayList;
import java.util.List;

import moe.haruue.goodhabits.data.database.plan.PlanDataBase;
import moe.haruue.goodhabits.model.Plan;
import moe.haruue.goodhabits.model.Step;
import rx.functions.Func1;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public abstract class BasePlansQueryFunc implements Func1<Plan, List<Plan>> {
    @Override
    public List<Plan> call(Plan plan) {
        BriteDatabase database = PlanDataBase.getInstance().getDatabase();
        ArrayList<Plan> plans = new ArrayList<>(0);
        Cursor cursor = database.query(querySql(), queryArguments(plan));
        if (cursor.moveToFirst()) {
            do {
                plans.add(onQueryForSinglePlan(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return plans;
    }

    protected abstract String querySql();

    protected abstract String[] queryArguments(Plan p);

    protected Plan onQueryForSinglePlan(Cursor cursor) {
        Plan plan = new Plan();
        plan.title = cursor.getString(cursor.getColumnIndex(PlanDataBase.COLUMN_NAME_TITLE));
        plan.hint = cursor.getString(cursor.getColumnIndex(PlanDataBase.COLUMN_NAME_HINT));
        plan.content = cursor.getString(cursor.getColumnIndex(PlanDataBase.COLUMN_NAME_CONTENT));
        plan.planId = cursor.getString(cursor.getColumnIndex(PlanDataBase.COLUMN_NAME_PLAN_ID));
        plan.steps = new ArrayList<>(JSON.parseArray(cursor.getString(cursor.getColumnIndex(PlanDataBase.COLUMN_NAME_STEPS)), Step.class));
        plan.imageUrl = cursor.getString(cursor.getColumnIndex(PlanDataBase.COLUMN_NAME_IMAGE_URL));
        plan.isDoing = cursor.getInt(cursor.getColumnIndex(PlanDataBase.COLUMN_NAME_IS_DOING)) != 0;
        return plan;
    }

}
