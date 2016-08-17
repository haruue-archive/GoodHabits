package moe.haruue.goodhabits.data.database.func;

import moe.haruue.goodhabits.data.database.TaskDataBase;
import moe.haruue.goodhabits.model.Task;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class QueryTasksByIdFunc extends BaseQueryTasksFunc {
    @Override
    protected String querySql() {
        return "SELECT * FROM " + TaskDataBase.TASK_DATABASE_NAME + " WHERE id=?";
    }

    @Override
    protected String[] queryArguments(Task t) {
        return new String[] {
            t.id + ""
        };
    }
}
