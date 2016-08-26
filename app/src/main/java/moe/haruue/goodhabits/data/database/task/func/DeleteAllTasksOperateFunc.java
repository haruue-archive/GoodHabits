package moe.haruue.goodhabits.data.database.task.func;

import com.squareup.sqlbrite.BriteDatabase;

import moe.haruue.goodhabits.data.database.task.TaskDataBase;
import moe.haruue.goodhabits.model.Task;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class DeleteAllTasksOperateFunc extends BaseTasksOperateFunc {
    @Override
    protected void onOperateForSingle(BriteDatabase database, Task t) {
        database.execute("DELETE FROM " + TaskDataBase.TASK_TABLE_NAME);
    }
}
