package moe.haruue.goodhabits.data.database.func;

import com.squareup.sqlbrite.BriteDatabase;

import moe.haruue.goodhabits.data.database.TaskDataBase;
import moe.haruue.goodhabits.model.Task;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class UpdateTasksByIdFunc extends BaseTasksOperateFunc {
    @Override
    protected void onOperateForSingle(BriteDatabase database, Task t) {
        database.update(TaskDataBase.TASK_TABLE_NAME, contentValuesOf(t), TaskDataBase.COLUMN_NAME_ID + "=?", t.id + "");
    }
}
