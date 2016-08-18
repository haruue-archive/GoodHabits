package moe.haruue.goodhabits.data.database.action;

import com.squareup.sqlbrite.BriteDatabase;

import moe.haruue.goodhabits.data.database.TaskDataBase;
import moe.haruue.goodhabits.model.Task;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class DeleteTasksByTaskPlanAction extends BaseTasksAction {
    @Override
    protected void onOperateForSingle(BriteDatabase database, Task t) {
        database.delete(TaskDataBase.TASK_DATABASE_NAME, TaskDataBase.COLUMN_NAME_PLAN + "=?", t.plan);
    }
}
