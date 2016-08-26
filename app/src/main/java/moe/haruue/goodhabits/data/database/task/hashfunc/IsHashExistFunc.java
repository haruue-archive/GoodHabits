package moe.haruue.goodhabits.data.database.task.hashfunc;

import android.database.Cursor;

import com.squareup.sqlbrite.BriteDatabase;

import moe.haruue.goodhabits.data.database.task.TaskDataBase;
import rx.functions.Func1;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class IsHashExistFunc implements Func1<Integer, Boolean> {
    @Override
    public Boolean call(Integer integer) {
        BriteDatabase database = TaskDataBase.getInstance().getDatabase();
        String querySql = "SELECT * FROM " + TaskDataBase.HASH_TABLE_NAME + " WHERE " + TaskDataBase.COLUMN_NAME_HASH + "=?";
        Cursor cursor = database.query(querySql, integer + "");
        try {
            return cursor.moveToFirst();
        } finally {
            cursor.close();
        }
    }

}
