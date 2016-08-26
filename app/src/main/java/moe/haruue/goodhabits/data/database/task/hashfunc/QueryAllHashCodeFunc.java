package moe.haruue.goodhabits.data.database.task.hashfunc;

import android.database.Cursor;

import com.squareup.sqlbrite.BriteDatabase;

import java.util.HashSet;
import java.util.Set;

import moe.haruue.goodhabits.data.database.task.TaskDataBase;
import rx.functions.Func1;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class QueryAllHashCodeFunc implements Func1<Integer, Set<Integer>> {

    @Override
    public Set<Integer> call(Integer integer) {
        BriteDatabase database = TaskDataBase.getInstance().getDatabase();
        Set<Integer> hashCodeSet = new HashSet<>(0);
        String querySql = "SELECT * FROM " + TaskDataBase.HASH_TABLE_NAME;
        Cursor cursor = database.query(querySql);
        if (cursor.moveToFirst()) {
            do {
                hashCodeSet.add(cursor.getInt(cursor.getColumnIndex(TaskDataBase.COLUMN_NAME_HASH)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return hashCodeSet;
    }
}
