package moe.haruue.goodhabits.ui.appwidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.jude.utils.JTimeTransform;

import java.util.List;

import moe.haruue.goodhabits.R;
import moe.haruue.goodhabits.data.database.task.func.TaskAllQueryFunc;
import moe.haruue.goodhabits.model.Task;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class TaskListRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private int appWidgetId;

    private List<Task> tasks;

    public TaskListRemoteViewFactory(Context context, Intent intent) {
        this.context = context;
        appWidgetId=intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {
        tasks = new TaskAllQueryFunc().call(null);
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {
        context = null;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Task data = tasks.get(position);
        RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.item_appwidget_task);
        view.setTextViewText(R.id.tv_appwidget_task_title, data.title);
        view.setTextViewText(R.id.tv_appwidget_task_time, new JTimeTransform(data.startTime).toString("H:m"));
        return view;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
