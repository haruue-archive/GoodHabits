package moe.haruue.goodhabits.ui.square;

import android.util.Log;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import moe.haruue.goodhabits.App;
import moe.haruue.goodhabits.R;
import moe.haruue.goodhabits.model.Plan;
import moe.haruue.goodhabits.model.Step;
import moe.haruue.goodhabits.util.ResourceUtils;
import moe.haruue.goodhabits.util.TimeUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by simonla on 2016/8/18.
 * Have a good day.
 */
public class SquarePresenter implements SquareContract.Presenter {

    private SquareContract.View mView;

    public SquarePresenter(SquareContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getPlans(SquareContract.Callback callback) {
        Observable.just(new Object())
                .map(o -> {
                    List<Plan> planList = new ArrayList<>(0);
                    planList.add(generateSleepPlan());
                    return planList;
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Plan>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("SquarePresenter", "getPlans", e);
                        callback.onError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(List<Plan> plen) {
                        callback.onSuccess(plen);
                    }
                });
    }

    private Plan generateSleepPlan() {
        Plan plan = new Plan();
        plan.title = "充足的睡眠";
        plan.hint = "充足的睡眠hint";
        plan.content = "充足的睡眠content";
        plan.planId = "enough_sleep";
        plan.startTime = TimeUtils.getTimeStampOf(new GregorianCalendar());
        plan.endTime = plan.startTime + 86400 * 4;
        plan.steps = generateSleepPlanStep(plan.planId);

//        plan.isDoing = true;
//        plan.startTime = TimeUtils.getTimeStampOf(new GregorianCalendar());
//        plan.endTime = plan.startTime + 86400;
        return plan;
    }

    private List<Step> generateSleepPlanStep(String planId) {
        Step step = new Step();
        step.title = "再见手机";
        step.content = ResourceUtils.readStringFromRawResource(App.getContext().getResources(), R.raw.enough_sleep_step_1_content);
        step.planId = planId;
        step.noEarlyTime = TimeUtils.secondsInDay(22, 0, 0);
        step.noLaterTime = TimeUtils.secondsInDay(23, 59, 59);
        step.minTime = 0;
        step.maxTime = 3600 * 2 - 1;
        List<Step> steps = new ArrayList<>(0);
        steps.add(step);
        steps.add(step.clone());
        steps.add(step.clone());
        steps.add(step.clone());
        return steps;
    }


}
