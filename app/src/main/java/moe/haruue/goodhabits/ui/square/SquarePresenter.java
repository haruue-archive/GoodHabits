package moe.haruue.goodhabits.ui.square;

import android.util.Log;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import moe.haruue.goodhabits.App;
import moe.haruue.goodhabits.R;
import moe.haruue.goodhabits.model.Plan;
import moe.haruue.goodhabits.model.Task;
import moe.haruue.goodhabits.model.TaskPlan;
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

/*    @Override
    public void getPlans(SquareContract.Callback callback) {
        ArrayList<Plan> arrayList = new ArrayList<>();
        Plan plan = new Plan();
        plan.title = "感觉更有活力";
        arrayList.add(plan);
        Plan plan1 = new Plan();
        plan1.title = "瘦成一道闪电";
        arrayList.add(plan1);
        Plan plan2 = new Plan();
        plan2.title = "改善睡眠质量";
        arrayList.add(plan2);
        Plan plan3 = new Plan();
        plan3.title = "提高专注力";
        arrayList.add(plan3);
        callback.onSuccess(arrayList);
    }*/
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
        TaskPlan plan = new TaskPlan();
        plan.title = "充足的睡眠";
        plan.hint = "充足的睡眠hint";
        plan.content = "充足的睡眠content";
        plan.planId = "enough_sleep";
        Task metaTask = new Task();
        metaTask.title = "再见手机";
        metaTask.content = ResourceUtils.readStringFromRawResource(App.getContext().getResources(), R.raw.enough_sleep_step_1_content);
        plan.addTask(metaTask, TimeUtils.secondsInDay(22, 0, 0), 8 * 3600, new GregorianCalendar(), 4, 1);
        return plan;
    }

}
