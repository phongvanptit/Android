package phongvh.approtrain.sqliteexample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import phongvh.approtrain.sqliteexample.model.User;

public class DBHelper extends SQLiteOpenHelper {
    private SQLiteDatabase mDatabase;
    private Cursor mCusor;

    public DBHelper(@Nullable Context context,
                    @Nullable String name,
                    @Nullable SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
        mDatabase = getWritableDatabase();
        getCursor();
        addDefaultData();
    }

    public void addDefaultData() {
        if (mCusor.getCount() < 1) {
            insert(new User("Phong", "12412351325"));
            insert(new User("Nam", "abc2435"));
            insert(new User("Nhan", "999999"));
            insert(new User("An", "444444"));
            insert(new User("Quyet", "57457457"));
            insert(new User("Minh", "3473457"));
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Khoi tao bang
        // create table table_user(_id integer primary key autoincrement, username text, password text);
        String script = "CREATE TABLE " + Contracts.TABLE_NAME + "(" + Contracts.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contracts.COLUMN_USERNAME + " TEXT, " +
                Contracts.COLUMN_PASSWORD + " TEXT)";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion,
                          int newVersion) {
        // Xoa bang va tao lai
        db.execSQL("DROP TABLE IF EXISTS " + Contracts.TABLE_NAME);
        onCreate(db);
    }

    public void insert(User user) {
        ContentValues values = new ContentValues();
        values.put(Contracts.COLUMN_USERNAME, user.getUsername());
        values.put(Contracts.COLUMN_PASSWORD, user.getPassword());
        mDatabase.insert(Contracts.TABLE_NAME, null, values);
    }

    public void delete(int id) {
        mDatabase.delete(Contracts.TABLE_NAME, Contracts.COLUMN_ID + " = " + id, null);
    }

    public void update(int id,
                       User user) {
        mDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contracts.COLUMN_USERNAME, user.getUsername());
        values.put(Contracts.COLUMN_PASSWORD, user.getPassword());
        mDatabase.update(Contracts.TABLE_NAME, values, Contracts.COLUMN_ID + " = " + id, null);
    }

    public ArrayList<User> getAllUser() {
        return null;
    }


    public Cursor getCursor() {
        mDatabase = getWritableDatabase();
        mCusor = mDatabase.rawQuery("SELECT * FROM " + Contracts.TABLE_NAME, null);
        return mCusor;
    }

    public Cursor getCursorWithSort(String sort) { // ASC or DESC
        mDatabase = getWritableDatabase();
        mCusor =
                mDatabase.rawQuery("SELECT * FROM " + Contracts.TABLE_NAME + " ORDER BY " + Contracts.COLUMN_USERNAME + " " + sort, null);
        return mCusor;
    }
}
