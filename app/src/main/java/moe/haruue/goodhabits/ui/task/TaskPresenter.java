package moe.haruue.goodhabits.ui.task;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

import moe.haruue.goodhabits.App;
import moe.haruue.goodhabits.config.Const;
import moe.haruue.goodhabits.data.database.task.func.TasksByIdQueryFunc;
import moe.haruue.goodhabits.data.database.task.func.TasksByTimeQueryFunc;
import moe.haruue.goodhabits.data.database.task.func.UpdateTasksByIdFunc;
import moe.haruue.goodhabits.data.database.task.hashfunc.IsHashExistFunc;
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
    private HashSet<Task> tasks = new HashSet<>(0);

    public TaskPresenter(TaskContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    private void doGetTasks(Subscriber<List<Task>> subscriber) {
        long todayStartTimeStamp = TimeUtils.timeStampToDayStartCCT(new GregorianCalendar().getTimeInMillis() / 1000);
        Observable.just(Task.newEmptyTaskWithStartTimeAndEndTime(todayStartTimeStamp, todayStartTimeStamp + 86400))
                .map(new TasksByTimeQueryFunc())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    @Override
    public void getTodayTasks() {
                doGetTasks(new Subscriber<List<Task>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TaskPresenter", "getTodayTasksOnError", e);
                        mView.onGetTodayTasks(null, false);
                    }

                    @Override
                    public void onNext(List<Task> tasks) {
                        Collections.sort(tasks);
                        TaskPresenter.this.tasks.addAll(tasks);
                        mView.onGetTodayTasks(new ArrayList<>(tasks), true);
                    }
                });
    }

    @Override
    public void setTaskFinish(int TaskId) {
        Observable.just(Task.newEmptyTaskWithId(TaskId))
                .map(new TasksByIdQueryFunc())
                .map(tasks -> {
                    for (Task t: tasks) {
                        t.isFinish = true;
                    }
                    return tasks;
                })
                .map(new UpdateTasksByIdFunc())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Task>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TaskPresenter", "setTaskFinishOnError", e);
                        mView.onSetTaskFinished(false);
                    }

                    @Override
                    public void onNext(List<Task> tasks) {
                        mView.onSetTaskFinished(true);
                    }
                });
    }

    @Override
    public void refreshTasks() {
        doGetTasks(new Subscriber<List<Task>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("TaskPresenter", "refreshTasksOnError", e);
                mView.onRefresh(false, null);
            }

            @Override
            public void onNext(List<Task> tasks) {
                ArrayList<Task> diff = makeDiff(tasks, TaskPresenter.this.tasks, ArrayList.class);
                if (diff == null) {
                    Log.e("TaskPresenter", "refreshTasksOnError", new NullPointerException("failed to new a ArrayList"));
                    mView.onRefresh(false, null);
                } else if (diff.size() == 0) {
                    mView.onRefresh(false, null);
                } else {
                    mView.onRefresh(true, diff);
                    TaskPresenter.this.tasks.addAll(diff);
                }
            }
        });

    }

    @Override
    public void saveNote(int id, String note) {
        Observable.just(Task.newEmptyTaskWithId(id))
                .map(new TasksByIdQueryFunc())
                .map(tasks1 -> {
                    for (Task t: tasks1) {
                        t.note = note;
                    }
                    return tasks1;
                })
                .map(new UpdateTasksByIdFunc())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Task>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TaskPresenter", "saveNote", e);
                    }

                    @Override
                    public void onNext(List<Task> tasks) {

                    }
                });
    }

    @Override
    public boolean isRead(int hashCode) {
        return new IsHashExistFunc().call(hashCode);
    }

    @Override
    public boolean isFirstTimeTOTHeFragment() {
        boolean result = App.getCommonSharedPreferences().getBoolean(Const.KEY_FIRST_INTO_TASK_FRAGMENT, true);
        if (result) {
            App.getCommonSharedPreferences().edit().putBoolean(Const.KEY_FIRST_INTO_TASK_FRAGMENT, false).apply();
        }
        return result;
    }

    /**
     * 找出 c1 - c2 即 c1 中 c2 没有的部分
     * @param c1 被减集合
     * @param c2 减集合
     * @param <T> 数据类型
     * @param <C1> c1 的集合类型
     * @param <C2> c2 的集合类型
     * @param <CR> 返回值的集合类型
     * @return 包含 c1 - c2 结果的 CR 类型的集合
     */
    private <C1 extends Collection<T>, C2 extends Collection<T>, CR extends Collection<T>, T> CR makeDiff(C1 c1, C2 c2, Class<CR> clazz) {
        CR result;
        try {
            result = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        for (T t: c1) {
            if (!c2.contains(t)) {
                result.add(t);
            }
        }
        return result;
    }
}
