package moe.haruue.goodhabits.ui.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import moe.haruue.goodhabits.R;
import moe.haruue.goodhabits.ui.launcher.LauncherActivity;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class TaskAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int i = 0; i < appWidgetIds.length; i++) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.appwidget_task);

            Intent serviceIntent = new Intent(context, TaskListRemoteViewService.class);
            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
            serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));
            views.setRemoteAdapter(R.id.lv_appwidget_task, serviceIntent);

            Intent clickIntent = new Intent(context, LauncherActivity.class);
            PendingIntent clickPendingIntent = PendingIntent.getActivity(context, 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setPendingIntentTemplate(R.id.tv_appwidget_toolbar, clickPendingIntent);

            appWidgetManager.updateAppWidget(appWidgetIds[i], views);

        }
    }

}
