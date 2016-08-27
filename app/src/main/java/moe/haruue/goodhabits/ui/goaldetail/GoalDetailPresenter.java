package moe.haruue.goodhabits.ui.goaldetail;

import android.util.Log;

import java.util.GregorianCalendar;
import java.util.List;

import moe.haruue.goodhabits.data.database.task.func.InsertTasksOperateFunc;
import moe.haruue.goodhabits.data.file.plan.func.GetAllPlanFunc;
import moe.haruue.goodhabits.data.file.plan.func.GetPlanByPlanIdFunc;
import moe.haruue.goodhabits.data.file.plan.func.StoragePlanFunc;
import moe.haruue.goodhabits.data.func.PlanToTasksFunc;
import moe.haruue.goodhabits.model.Plan;
import moe.haruue.goodhabits.model.Task;
import moe.haruue.goodhabits.util.TimeUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by simonla on 2016/8/25.
 * Have a good day.
 */
public class GoalDetailPresenter implements GoalDetailContract.Presenter {

    private GoalDetailContract.View mView;

    public GoalDetailPresenter(GoalDetailContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getSteps(String planId, GoalDetailContract.Callback callback) {
        Observable.just(Plan.newEmptyPlanWithPlanId(planId))
                .map(new GetPlanByPlanIdFunc())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Plan>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("GoalDetailPresenter", "getSteps", e);
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(Plan plan) {
                        callback.onFinish(plan.array);
                    }
                });
    }

    @Override
    public void saveThePlan(String planId) {
        Observable.just(Plan.newEmptyPlanWithPlanId(planId))
                .map(new GetPlanByPlanIdFunc())
                .map(plan -> {
                    plan.timeRangeStart = TimeUtils.getTimeStampOf(new GregorianCalendar());
                    plan.timeRangeEnd = Long.MAX_VALUE;
                    plan.isDoing = true;
                    return plan;
                })
                .map(new StoragePlanFunc(true))
                .subscribeOn(Schedulers.io())
                .map(new PlanToTasksFunc())
                .subscribeOn(Schedulers.computation())
                .map(new InsertTasksOperateFunc())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Task>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("GoalDetailPresenter", "saveThePlan", e);
                    }

                    @Override
                    public void onNext(List<Task> tasks) {

                    }
                });

    }

    @Override
    public String getNowPlan() {
        List<Plan> plen = new GetAllPlanFunc().call(null);
        for (Plan p: plen) {
            if (p.isDoing) {
                return p.planId;
            }
        }
        return "";
    }

}
