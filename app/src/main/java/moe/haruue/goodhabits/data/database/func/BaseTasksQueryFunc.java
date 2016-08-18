package moe.haruue.goodhabits.data.database.func;

import android.database.Cursor;
import android.util.Log;

import com.squareup.sqlbrite.BriteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import moe.haruue.goodhabits.data.database.TaskDataBase;
import moe.haruue.goodhabits.model.Task;
import rx.functions.Func1;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public abstract class BaseTasksQueryFunc implements Func1<Task, List<Task>> {
    @Override
    public List<Task> call(Task task) {
        BriteDatabase database = TaskDataBase.getInstance().getDatabase();
        ArrayList<Task> tasks = new ArrayList<>(0);
        Log.d("sql", querySql());
        Log.d("sqlarg================\n", Arrays.toString(queryArguments(task)));
        Cursor cursor = database.query(querySql(), queryArguments(task));
        if (cursor.moveToFirst()) {
            do {
                tasks.add(onQueryForSingleTask(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return tasks;
    }

    protected abstract String querySql();

    protected abstract String[] queryArguments(Task t);

    protected Task onQueryForSingleTask(Cursor cursor) {
        Task task = new Task();
        task.id = cursor.getInt(cursor.getColumnIndex(TaskDataBase.COLUMN_NAME_ID));
        task.title = cursor.getString(cursor.getColumnIndex(TaskDataBase.COLUMN_NAME_TITLE));
        task.content = cursor.getString(cursor.getColumnIndex(TaskDataBase.COLUMN_NAME_CONTENT));
        task.type = cursor.getString(cursor.getColumnIndex(TaskDataBase.COLUMN_NAME_TYPE));
        task.plan = cursor.getString(cursor.getColumnIndex(TaskDataBase.COLUMN_NAME_PLAN));
        task.imageUrl = cursor.getString(cursor.getColumnIndex(TaskDataBase.COLUMN_NAME_IMAGE_URL));
        task.startTime = cursor.getLong(cursor.getColumnIndex(TaskDataBase.COLUMN_NAME_START_TIME));
        task.endTime = cursor.getLong(cursor.getColumnIndex(TaskDataBase.COLUMN_NAME_END_TIME));
        return task;
    }

}
