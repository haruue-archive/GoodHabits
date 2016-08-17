package moe.haruue.goodhabits.data.database.action;

import com.squareup.sqlbrite.BriteDatabase;

import moe.haruue.goodhabits.data.database.TaskDataBase;
import moe.haruue.goodhabits.model.Task;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */
public class InsertTasksAction extends BaseTasksAction {
    @Override
    protected void onOperate(BriteDatabase database, Task t) {
        database.insert(TaskDataBase.TASK_DATABASE_NAME, contentValuesOf(t));
    }
}
