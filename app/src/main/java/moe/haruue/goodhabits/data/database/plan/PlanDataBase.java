package moe.haruue.goodhabits.data.database.plan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import rx.schedulers.Schedulers;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public enum PlanDataBase {

    INSTANCE;

    private BriteDatabase database;

    public static PlanDataBase getInstance() {
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
    public final static int TASK_DATABASE_VERSION = 1;

    public final static String PLAN_TABLE_NAME = "plan";

    public final static String COLUMN_NAME_ID = "id";
    public final static String COLUMN_NAME_TITLE = "title";
    public final static String COLUMN_NAME_HINT = "hint";
    public final static String COLUMN_NAME_CONTENT = "content";
    public final static String COLUMN_NAME_PLAN_ID = "plan_id";
    public final static String COLUMN_NAME_STEPS = "steps";
    public final static String COLUMN_NAME_IMAGE_URL = "image_url";
    public final static String COLUMN_NAME_AUTHOR = "author";
    public final static String COLUMN_NAME_IS_DOING = "is_doing";

    public class OpenHelper extends SQLiteOpenHelper {

        private final static String PLAN_TABLE_CREATE_SQL = "CREATE TABLE " + PLAN_TABLE_NAME + " (" +
                COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME_TITLE + " TEXT, " +
                COLUMN_NAME_HINT + " TEXT, " +
                COLUMN_NAME_CONTENT + " TEXT, " +
                COLUMN_NAME_PLAN_ID + " TEXT UNIQUE, " +
                COLUMN_NAME_STEPS + " TEXT, " +
                COLUMN_NAME_IMAGE_URL + " TEXT, " +
                COLUMN_NAME_AUTHOR + " TEXT, " +
                COLUMN_NAME_IS_DOING + " INTEGER" +
                ")";

        public OpenHelper(Context context) {
            super(context, TASK_DATABASE_NAME, null, TASK_DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(PLAN_TABLE_CREATE_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

}
