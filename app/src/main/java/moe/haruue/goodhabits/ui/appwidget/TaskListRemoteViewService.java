package moe.haruue.goodhabits.ui.appwidget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class TaskListRemoteViewService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new TaskListRemoteViewFactory(getApplicationContext(), intent);
    }
}
