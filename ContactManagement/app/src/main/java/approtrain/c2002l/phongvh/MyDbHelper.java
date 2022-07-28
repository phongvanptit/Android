package approtrain.c2002l.phongvh;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {
    public MyDbHelper(Context context) {
        super(context, Contract.DB_NAME, null, Contract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create table table_contact(_id integer primary key autoincrement, name text, important
        // integer, date text)
        String script =
                "create table " + Contract.TABLE_NAME + "(" + Contract.COLUMN_ID + " integer " +
                        "primary " +
                        "key autoincrement," + Contract.COLUMN_NAME + " text," + Contract.COLUMN_DATE + " text," + Contract.COLUMN_IMPORTANT + " integer" + ")";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contract.TABLE_NAME);
        onCreate(db);
    }
}
