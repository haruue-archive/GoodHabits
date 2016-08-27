package moe.haruue.goodhabits.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.GregorianCalendar;
import java.util.List;

import moe.haruue.goodhabits.data.database.task.func.TasksByTimeQueryFunc;
import moe.haruue.goodhabits.model.Task;
import moe.haruue.goodhabits.util.TimeUtils;

/**
 * Created by simonla on 2016/8/27.
 * Have a good day.
 */
public class AlarmReceiver extends BroadcastReceiver {

    public static final String TAG = "AlarmReceiver";
    private AlarmManager mAlarmManager;
    private PendingIntent mPendingIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
        //需要List<Task> mTasks
        long todayStartTimeStamp = TimeUtils.timeStampToDayStart(new GregorianCalendar().getTimeInMillis() / 1000);
        List<Task> mTasks = new TasksByTimeQueryFunc().call(Task.newEmptyTaskWithStartTimeAndEndTime(todayStartTimeStamp, todayStartTimeStamp + 86400));
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            /*mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent intent1 = new Intent(context, PushReceiver.class);
            mPendingIntent = PendingIntent.getBroadcast(context, 0, intent1, 0);*/
        }
    }
}
