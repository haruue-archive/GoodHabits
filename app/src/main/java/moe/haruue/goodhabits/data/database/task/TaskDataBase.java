package moe.haruue.goodhabits.data.database.task;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import rx.schedulers.Schedulers;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public enum  TaskDataBase {

    INSTANCE;

    private BriteDatabase database;

    public static TaskDataBase getInstance() {
        return INSTANCE;
    }

    public void initialize(Context context) {
        OpenHelper helper = new OpenHelper(context);
        SqlBrite brite = SqlBrite.create();
        database = brite.wrapDatabaseHelper(helper, Schedulers.io());
    }

    public BriteDatabase getDatabase() {
        return database;
    }

    public final static String TASK_DATABASE_NAME = "task.db";
    public final static String TASK_TABLE_NAME = "task";
    public final static int TASK_DATABASE_VERSION = 1;

    public final static String COLUMN_NAME_ID = "id";
    public final static String COLUMN_NAME_TITLE = "title";
    public final static String COLUMN_NAME_CONTENT = "content";
    public final static String COLUMN_NAME_TYPE = "type";
    public final static String COLUMN_NAME_PLAN = "plan";
    public final static String COLUMN_NAME_IMAGE_URL = "image_url";
    public final static String COLUMN_NAME_START_TIME = "start_time";
    public final static String COLUMN_NAME_END_TIME = "end_time";
    public final static String COLUMN_NAME_IS_FINISH = "is_finish";
    public final static String COLUMN_NAME_MD5 = "md5";

    public class OpenHelper extends SQLiteOpenHelper {

        private final static String TASK_DATABASE_CREATE_SQL = "CREATE TABLE " + TASK_TABLE_NAME + " (" +
                COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME_TITLE + " TEXT, " +
                COLUMN_NAME_CONTENT + " TEXT, " +
                COLUMN_NAME_TYPE + " TEXT, " +
                COLUMN_NAME_PLAN + " TEXT, " +
                COLUMN_NAME_IMAGE_URL + " TEXT, " +
                COLUMN_NAME_START_TIME + " INTEGER, " +
                COLUMN_NAME_END_TIME + " INTEGER, " +
                COLUMN_NAME_IS_FINISH + " INTEGER, " +
                COLUMN_NAME_MD5 + " TEXT UNIQUE" +
                ")";

        public OpenHelper(Context context) {
            super(context, TASK_DATABASE_NAME, null, TASK_DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(TASK_DATABASE_CREATE_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

}
