package moe.haruue.goodhabits.receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import moe.haruue.goodhabits.R;
import moe.haruue.goodhabits.ui.main.MainActivity;
import moe.haruue.goodhabits.ui.task.TaskFragment;

/**
 * Created by simonla on 2016/8/27.
 * Have a good day.
 */
public class PushReceiver extends BroadcastReceiver {

    public static final String TAG = "PushReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent openMain = new Intent(context, MainActivity.class);
        long lastTime = intent.getLongExtra(TaskFragment.EXTRA_LAST_TIME,0)-(System.currentTimeMillis()/1000);
        String remindContext = intent.getStringExtra(TaskFragment.EXTRA_PUSH_CONTEXT);

        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(intent.getStringExtra(TaskFragment.EXTRA_PUSH))
                .setContentText("还剩"+lastTime/60+"分钟"+"  "+remindContext);

        //哎我视频看得好好的，开了这个通知，怎么又要重新看广告了卧槽
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(openMain);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, openMain,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1,builder.build());
    }
}
