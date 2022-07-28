package approtrain.c2002l.phongvh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DBController {
    private MyDbHelper mDbHelper;
    private SQLiteDatabase mDatabase;
    private Cursor mCursor;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DBController(Context context) {
        mDbHelper = new MyDbHelper(context);
        mDatabase = mDbHelper.getWritableDatabase();
        mCursor = getCursor();
        addDefaultList();
    }

    public void insert(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(Contract.COLUMN_NAME, contact.getName());
        values.put(Contract.COLUMN_DATE, simpleDateFormat.format(contact.getDate()));
        values.put(Contract.COLUMN_IMPORTANT, contact.isImporttant());

        mDatabase.insert(Contract.TABLE_NAME, null, values);
    }

    public void delete(int id) {

    }

    public void update(int id,
                       Contact contact) {

    }

    public void addDefaultList() {
        if (getItemCount() < 1) {
            insert(new Contact("Vợ lớn", true, new Date()));
            insert(new Contact("Vợ bé", false, new Date()));
            insert(new Contact("Thầy Luận", true, new Date()));
            insert(new Contact("Bác bán than", true, new Date()));
            insert(new Contact("Vợ cũ", false, new Date()));
        }
    }

    public Cursor getCursor() {
        return mDatabase.rawQuery("select * from " + Contract.TABLE_NAME, null);
    }

    public int getItemCount() {
        return mCursor.getCount();
    }
}
