package moe.haruue.goodhabits.data.database.func;

import android.content.ContentValues;

import com.squareup.sqlbrite.BriteDatabase;

import java.util.List;

import moe.haruue.goodhabits.data.database.TaskDataBase;
import moe.haruue.goodhabits.model.Task;
import rx.functions.Func1;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public abstract class BaseTasksOperateFunc implements Func1<List<Task>, List<Task>> {

    @Override
    public List<Task> call(List<Task> tasks) {
        BriteDatabase database = TaskDataBase.getInstance().getDatabase();
        BriteDatabase.Transaction transaction = database.newTransaction();
        try {
            beforeOperate(database, tasks);
            onOperate(database, tasks);
            afterOperate(database, tasks);
            transaction.markSuccessful();
        } finally {
            transaction.end();
        }
        return tasks;
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

    protected abstract void onOperateForSingle(BriteDatabase database, Task t);

    protected void onOperate(BriteDatabase database, List<Task> tasks) {
        for (Task t: tasks) {
            onOperateForSingle(database, t);
        }
    }

    protected void beforeOperate(BriteDatabase database, List<Task> tasks) {

    }

    protected void afterOperate(BriteDatabase database, List<Task> tasks) {

    }
}
