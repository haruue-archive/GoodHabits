package moe.haruue.goodhabits.ui.widget;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import moe.haruue.goodhabits.data.CurrentUser;
import moe.haruue.goodhabits.data.database.task.func.TasksByTimeQueryFunc;
import moe.haruue.goodhabits.data.func.AppWidgetCacheAndUpdateFunc;
import moe.haruue.goodhabits.model.Task;
import moe.haruue.goodhabits.util.TimeUtils;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class CourseListAppWidgetUpdateService extends Service {

    public static final String EXTRA_UPDATE = "update";

    public CourseListAppWidgetUpdateService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        load(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    private void load(Intent intent){
        if (!CurrentUser.getInstance().isLogin()) {
            Log.i("AppWidgetUpdateService", "not login, stop here");
            return;
        }
        long todayStartTimeStamp = TimeUtils.timeStampToDayStart(System.currentTimeMillis() / 1000);
        Observable.just(Task.newEmptyTaskWithStartTimeAndEndTime(todayStartTimeStamp, todayStartTimeStamp + 86400))
                .map(new TasksByTimeQueryFunc())
                .map(new AppWidgetCacheAndUpdateFunc())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Task>>() {
                    @Override
                    public void onNext(List<Task> affairs) {
                        Log.d("UpdateSuccess", affairs.toString());
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("AppWidgetUpdateService", "load: onError", e);
                    }
                });
    }

    public static void start(Context context, boolean updateFromNetwork) {
        Intent starter = new Intent(context, CourseListAppWidgetUpdateService.class);
        context.startService(starter);
    }

}
