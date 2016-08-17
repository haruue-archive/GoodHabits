package moe.haruue.goodhabits.data.database.action;

import android.content.ContentValues;

import com.squareup.sqlbrite.BriteDatabase;

import java.util.List;

import moe.haruue.goodhabits.data.database.TaskDataBase;
import moe.haruue.goodhabits.model.Task;
import rx.functions.Action1;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public abstract class BaseTasksAction implements Action1<List<Task>> {

    @Override
    public void call(List<Task> tasks) {
        BriteDatabase database = TaskDataBase.getInstance().getDatabase();
        BriteDatabase.Transaction transaction = database.newTransaction();
        try {
            for (Task t: tasks) {
                onOperate(database, t);
            }
        } finally {
            transaction.end();
        }
    }

    protected ContentValues contentValuesOf(Task task) {
        ContentValues values = new ContentValues();
        values.put(TaskDataBase.COLUMN_NAME_TITLE, task.title);
        values.put(TaskDataBase.COLUMN_NAME_CONTENT, task.content);
        values.put(TaskDataBase.COLUMN_NAME_TYPE, task.type);
        values.put(TaskDataBase.COLUMN_NAME_PLAN, task.plan);
        values.put(TaskDataBase.COLUMN_NAME_IMAGE_URL, task.imageUrl);
        values.put(TaskDataBase.COLUMN_NAME_START_TIME, task.startTime);
        values.put(TaskDataBase.COLUMN_NAME_END_TIME, task.endTime);
        return values;
    }

    protected abstract void onOperate(BriteDatabase database, Task t);

}
