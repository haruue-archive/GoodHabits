package moe.haruue.goodhabits.data.func;

import com.google.gson.Gson;

import java.io.File;
import java.util.List;

import moe.haruue.goodhabits.App;
import moe.haruue.goodhabits.config.Const;
import moe.haruue.goodhabits.model.Task;
import moe.haruue.goodhabits.ui.widget.CourseListAppWidget;
import moe.haruue.goodhabits.util.FileUtils;
import rx.functions.Func1;

/**
 * Manage cache file for {@link moe.haruue.goodhabits.ui.widget.CourseListAppWidget} and call its update
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class AppWidgetCacheAndUpdateFunc implements Func1<List<Task>, List<Task>> {

    @Override
    public List<Task> call(List<Task> courses) {
        // List<Task> dayTasks = new UserTaskFilterByWeekDayFunc(Calendar.THURSDAY).call(weekTasks);
        FileUtils.writeStringToFile(new Gson().toJson(courses), new File(App.getContext().getFilesDir().getAbsolutePath() + "/" + Const.APP_WIDGET_CACHE_FILE_NAME));
        CourseListAppWidget.updateNow(App.getContext());
        return courses;
    }

    public static void deleteCache() {
        try {
            //noinspection ResultOfMethodCallIgnored
            new File(App.getContext().getFilesDir().getAbsolutePath() + "/" + Const.APP_WIDGET_CACHE_FILE_NAME).delete();
            CourseListAppWidget.updateNow(App.getContext());
        } catch (Exception ignored) {

        }
    }

}
