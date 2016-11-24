package moe.haruue.goodhabits.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jude.utils.JTimeTransform;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import moe.haruue.goodhabits.App;
import moe.haruue.goodhabits.R;
import moe.haruue.goodhabits.config.Const;
import moe.haruue.goodhabits.model.Task;
import moe.haruue.goodhabits.util.FileUtils;

/**
 * ListView Remote Adapter of {@link moe.haruue.goodhabits.R.id#lv_app_widget_course_list} in {@link CourseListAppWidget}
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class CourseListRemoteViewsService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.d("RemoteFactory", "Service onGetViewFactory");
        return new Factory(this, intent);
    }

    private class Factory implements RemoteViewsFactory {

        Context context;
        Intent intent;
        int factoryType;
        List<RemoteViews> views = new ArrayList<>();
        List<Task> items = new ArrayList<>(0);

        static final int FACTORY_TYPE_NORMAL = 0;
        static final int FACTORY_TYPE_ERROR = -1;
        static final int FACTORY_TYPE_LOADING = -2;

        Factory(Context context, Intent intent) {
            this.context = context;
            this.intent = intent;
        }

        private void refresh() {
            items.clear();
            try {
                items = getTaskList();
            } catch (Exception e) {
                items = null;
            }
            if (items == null) {
                setError("你还没有登录");
                return;
            }
            if (items.size() == 0) {
                setError("今日无安排");
            }
            setNormal();
        }

        private void setError(String message) {
            Log.i("RemoteFactory", "setError: " + message);
            this.factoryType = FACTORY_TYPE_ERROR;
            this.views.clear();
            RemoteViews errorViews = new RemoteViews(context.getPackageName(), R.layout.app_widget_course_list_item_error);
            errorViews.setTextViewText(R.id.tv_app_widget_course_item_error, message);
            this.views.add(errorViews);
        }

        private void setNormal() {
            Log.i("RemoteFactory", "setNormal");
            this.factoryType = FACTORY_TYPE_NORMAL;
            this.views.clear();
            for (Task item: items) {
                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget_course_list_item);
                // initialize the view first to prevent views repetition
                views.setInt(R.id.rl_app_widget_course_item_content, "setBackgroundColor", 0x00000000);
                views.setTextViewText(R.id.tv_app_widget_course_item_name, "");
                views.setTextViewText(R.id.tv_app_widget_course_item_room, "");
                views.setBoolean(R.id.rl_app_widget_course_item_content, "setEnabled", false);
                // fill item
                views.setTextViewText(R.id.tv_app_widget_course_item_name, item.title);
                views.setTextViewText(R.id.tv_app_widget_course_item_room, new JTimeTransform(item.startTime).toString(new JTimeTransform.RecentDateFormat()));
                views.setInt(R.id.rl_app_widget_course_item_content, "setBackgroundColor", getColor(R.color.material_color_blue_800));
                Intent coursesIntent = new Intent();
                coursesIntent.putExtra(CourseListAppWidget.EXTRA_COURSES, (Parcelable) item);
                views.setOnClickFillInIntent(R.id.rl_app_widget_course_item_content, coursesIntent);
                views.setBoolean(R.id.rl_app_widget_course_item_content, "setEnabled", true);
                this.views.add(views);
            }
        }

        private List<Task> getTaskList() {
            String json = FileUtils.readStringFromFile(new File(App.getContext().getFilesDir().getAbsolutePath() + "/" + Const.APP_WIDGET_CACHE_FILE_NAME));
            return new Gson().fromJson(json, new TypeToken<List<Task>>() {}.getType());
        }

        @Override
        public void onCreate() {
            Log.d("RemoteFactory", "onCreate");
        }

        @Override
        public void onDataSetChanged() {
            Log.d("RemoteFactory", "onDataSetChanged");
            refresh();
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return this.views.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            Log.i("RemoteViews", "getViewAt: " + position + " of " + views.size() + ", views: " + views.get(position) + ", data: " + items.get(position));
            return this.views.get(position);
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

    }

}
