package moe.haruue.goodhabits.data.database.task.func;

import android.database.Cursor;

import com.squareup.sqlbrite.BriteDatabase;

import java.util.List;

import moe.haruue.goodhabits.data.database.task.TaskDataBase;
import moe.haruue.goodhabits.model.Task;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */
public class InsertTasksOperateFunc extends BaseTasksOperateFunc {
    @Override
    protected void onOperateForSingle(BriteDatabase database, Task t) {
        database.insert(TaskDataBase.TASK_TABLE_NAME, contentValuesOf(t));
    }

    @Override
    protected void afterOperate(BriteDatabase database, List<Task> tasks) {
        setTaskIdAfterInsert(database, tasks);
        super.afterOperate(database, tasks);
    }

    private void setTaskIdAfterInsert(BriteDatabase database, List<Task> tasks) {
        for (Task t: tasks) {
            Cursor cursor = database.query("SELECT " + TaskDataBase.COLUMN_NAME_ID + " FROM " + TaskDataBase.TASK_TABLE_NAME + " WHERE " +
                    TaskDataBase.COLUMN_NAME_TITLE + "=?" + " AND " +
                    TaskDataBase.COLUMN_NAME_CONTENT + "=?" + " AND " +
                    TaskDataBase.COLUMN_NAME_TYPE + "=?" + " AND " +
                    TaskDataBase.COLUMN_NAME_PLAN + "=?" + " AND " +
                    TaskDataBase.COLUMN_NAME_IMAGE_URL + "=?" + " AND " +
                    TaskDataBase.COLUMN_NAME_START_TIME + "=?" + " AND " +
                    TaskDataBase.COLUMN_NAME_END_TIME + "=?",
                    t.title,
                    t.content,
                    t.type,
                    t.plan,
                    t.imageUrl,
                    t.startTime + "",
                    t.endTime + "");
            try {
                if (cursor.moveToFirst()) {
                    t.id = cursor.getInt(cursor.getColumnIndex(TaskDataBase.COLUMN_NAME_ID));
                }
            } finally {
                cursor.close();
            }
        }
    }
}
