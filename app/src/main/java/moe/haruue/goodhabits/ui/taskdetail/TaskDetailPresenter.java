package moe.haruue.goodhabits.ui.taskdetail;

import android.util.Log;

import moe.haruue.goodhabits.data.database.task.hashfunc.InsertHashFunc;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by simonla on 2016/8/26.
 * Have a good day.
 */
public class TaskDetailPresenter implements TaskDetailContract.Presenter {

    private TaskDetailContract.Presenter mPresenter;
    private TaskDetailContract.View mView;

    public TaskDetailPresenter(TaskDetailContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void saveIsRead(int hashCode) {
        Observable.just(hashCode)
                .map(new InsertHashFunc())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TaskDetailPresenter", "saveIsRead", e);
                    }

                    @Override
                    public void onNext(Integer integer) {

                    }
                });
    }


}
