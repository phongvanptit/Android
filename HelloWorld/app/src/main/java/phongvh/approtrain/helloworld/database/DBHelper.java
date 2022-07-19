package phongvh.approtrain.helloworld.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import phongvh.approtrain.helloworld.modals.User;

public class DBHelper extends SQLiteOpenHelper {
    private SQLiteDatabase mDatabase;
    private Cursor mCusor;

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mDatabase = getWritableDatabase();
        getCursor();
        addDefaultData();
    }

    public void addDefaultData() {
        if (mCusor.getCount() < 1) {
            insert(new User("phong.vh", "123454", "Vu Hong Phong", "76237567832", "37865832", "Ha Noi"));
            insert(new User("phong.vh", "123454", "Nguyen Van Minh", "76237567832", "37865832", "Ha Noi"));
            insert(new User("phong.vh", "123454", "Tran Trong Dat", "76237567832", "37865832", "Ha Noi"));
            insert(new User("phong.vh", "123454", "Dao Manh Cuong", "76237567832", "37865832", "Ha Noi"));
            insert(new User("phong.vh", "123454", "Le Trung Hieu", "76237567832", "37865832", "Ha Noi"));
            insert(new User("phong.vh", "123454", "Ngo Bao Son", "76237567832", "37865832", "Ha Noi"));
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Khoi tao bang
        // create table table_user(_id integer primary key autoincrement, username text, password text);
        String script = "CREATE TABLE " + Contracts.TABLE_NAME + "(" + Contracts.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contracts.COLUMN_USERNAME + " TEXT, " +
                Contracts.COLUMN_FULL_NAME + " TEXT, " +
                Contracts.COLUMN_BIRTHDAY + " TEXT, " +
                Contracts.COLUMN_PHONE + " TEXT, " +
                Contracts.COLUMN_ADDRESS + " TEXT, " +
                Contracts.COLUMN_PASSWORD + " TEXT)";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xoa bang va tao lai
        db.execSQL("DROP TABLE IF EXISTS " + Contracts.TABLE_NAME);
        onCreate(db);
    }

    public void insert(User user) {
        ContentValues values = new ContentValues();
        values.put(Contracts.COLUMN_USERNAME, user.getUserName());
        values.put(Contracts.COLUMN_PASSWORD, user.getPassWord());
        values.put(Contracts.COLUMN_FULL_NAME, user.getFullName());
        values.put(Contracts.COLUMN_BIRTHDAY, user.getDateOfBirth());
        values.put(Contracts.COLUMN_PHONE, user.getPhoneNumber());
        values.put(Contracts.COLUMN_ADDRESS, user.getAddress());


        mDatabase.insert(Contracts.TABLE_NAME, null, values);
    }

    public void delete(int id) {
        mDatabase.delete(Contracts.TABLE_NAME, Contracts.COLUMN_ID + " = " + id, null);
    }


    public Cursor getCursor() {
        mDatabase = getWritableDatabase();
        mCusor = mDatabase.rawQuery("SELECT * FROM " + Contracts.TABLE_NAME, null);
        return mCusor;
    }

}
