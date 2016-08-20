package moe.haruue.goodhabits.data.database.task.func;

import moe.haruue.goodhabits.data.database.task.TaskDataBase;
import moe.haruue.goodhabits.data.database.task.func.BaseTasksQueryFunc;
import moe.haruue.goodhabits.model.Task;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class TasksByIdQueryFunc extends BaseTasksQueryFunc {
    @Override
    protected String querySql() {
        return "SELECT * FROM " + TaskDataBase.TASK_TABLE_NAME + " WHERE id=?";
    }

    @Override
    protected String[] queryArguments(Task t) {
        return new String[] {
            t.id + ""
        };
    }
}
