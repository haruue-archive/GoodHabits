package moe.haruue.goodhabits.ui.calendar;

import android.util.Log;

import java.util.GregorianCalendar;

import moe.haruue.goodhabits.data.database.task.func.TasksByTimeQueryFunc;
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
public class CalendarPresneter implements CalendarContract.Presenter {

    private CalendarContract.View mView;

    public CalendarPresneter(CalendarContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getFinishedPer(CalendarContract.Callback callback) {
        Observable.just(Task.newEmptyTaskWithStartTimeAndEndTime(0, TimeUtils.getTimeStampOf(new GregorianCalendar())))
                .map(new TasksByTimeQueryFunc())
                .map(tasks -> {
                    int finishedTastCount = 0;
                    for (Task t: tasks) {
                        if (t.isFinish) {
                            finishedTastCount++;
                        }
                    }
                    return (int) (((double) finishedTastCount) / ((double) tasks.size()) * 100);
                })
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("CalendarPresneter", "getFinishPer", e);
                        callback.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        callback.onFinish(integer);
                    }
                });

    }
}
