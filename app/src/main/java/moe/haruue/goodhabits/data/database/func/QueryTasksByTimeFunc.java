package moe.haruue.goodhabits.data.database.func;

import moe.haruue.goodhabits.data.database.TaskDataBase;
import moe.haruue.goodhabits.model.Task;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class QueryTasksByTimeFunc extends BaseQueryTasksFunc {

    @Override
    protected String querySql() {
        return "SELECT * FROM " + TaskDataBase.TASK_DATABASE_NAME + " WHERE " +
                "(" +
                TaskDataBase.COLUMN_NAME_START_TIME + ">?" + " AND " +
                TaskDataBase.COLUMN_NAME_START_TIME + "<?" +
                ") OR (" +
                TaskDataBase.COLUMN_NAME_END_TIME + ">?" + " AND " +
                TaskDataBase.COLUMN_NAME_END_TIME + "<?" +
                ")";
    }

    @Override
    protected String[] queryArguments(Task t) {
        return new String[] {
                t.startTime + "",
                t.endTime + "",
                t.startTime + "",
                t.endTime + ""
        };
    }
}
