package moe.haruue.goodhabits.data.database.task.hashfunc;

import com.squareup.sqlbrite.BriteDatabase;

import moe.haruue.goodhabits.data.database.task.TaskDataBase;
import rx.functions.Func1;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class DeleteHashFunc implements Func1<Integer, Integer> {
    @Override
    public Integer call(Integer integer) {
        BriteDatabase database = TaskDataBase.getInstance().getDatabase();
        database.delete(TaskDataBase.HASH_TABLE_NAME, TaskDataBase.COLUMN_NAME_HASH + "=?", integer + "");
        return integer;
    }
}
