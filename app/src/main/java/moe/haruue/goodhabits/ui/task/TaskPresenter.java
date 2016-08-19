package moe.haruue.goodhabits.ui.task;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import moe.haruue.goodhabits.data.database.func.TasksByIdQueryFunc;
import moe.haruue.goodhabits.data.database.func.TasksByTimeQueryFunc;
import moe.haruue.goodhabits.model.Task;
import moe.haruue.goodhabits.util.TimeUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by simonla on 2016/8/18.
 * Have a good day.
 */
public class TaskPresenter implements TaskContract.Presenter {

    private TaskContract.View mView;

    public TaskPresenter(TaskContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getTodayTasks() {
        long todayStartTimeStamp = TimeUtils.timeStampToDayStartCCT(new GregorianCalendar().getTimeInMillis() / 1000);
        Observable.just(Task.newEmptyTaskWithStartTimeAndEndTime(todayStartTimeStamp, todayStartTimeStamp + 86400))
                .map(new TasksByTimeQueryFunc())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Task>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onGetTodayTasks(null, false);
                    }

                    @Override
                    public void onNext(List<Task> tasks) {
                        mView.onGetTodayTasks(new ArrayList<>(tasks), true);
                    }
                });
    }

    @Override
    public void setTaskFinish(int TaskId) {
        Observable.just(Task.newEmptyTaskWithId(TaskId))
                .map(new TasksByIdQueryFunc())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Task>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onSetTaskFinished(false);
                    }

                    @Override
                    public void onNext(List<Task> tasks) {
                        mView.onSetTaskFinished(true);
                    }
                });

    }
}
