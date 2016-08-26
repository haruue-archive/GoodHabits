package moe.haruue.goodhabits.data.database.task.hashfunc;

import android.content.ContentValues;
import android.util.Log;

import com.squareup.sqlbrite.BriteDatabase;

import moe.haruue.goodhabits.data.database.task.TaskDataBase;
import rx.functions.Func1;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class InsertHashFunc implements Func1<Integer, Integer> {
    @Override
    public Integer call(Integer integer) {
        BriteDatabase database = TaskDataBase.getInstance().getDatabase();
        BriteDatabase.Transaction transaction = database.newTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(TaskDataBase.COLUMN_NAME_HASH, integer);
            database.insert(TaskDataBase.HASH_TABLE_NAME, values);
            transaction.markSuccessful();
        } finally {
            transaction.end();
        }
        return integer;
    }
}
