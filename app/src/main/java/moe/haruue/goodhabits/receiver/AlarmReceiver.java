package moe.haruue.goodhabits.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

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
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            /*mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent intent1 = new Intent(context, PushReceiver.class);
            mPendingIntent = PendingIntent.getBroadcast(context, 0, intent1, 0);*/
        }
    }
}
