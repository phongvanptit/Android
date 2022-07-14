package phongvh.approtrain.sqliteexample.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import phongvh.approtrain.sqliteexample.R;
import phongvh.approtrain.sqliteexample.database.Contracts;
import phongvh.approtrain.sqliteexample.database.DBHelper;

public class MainActivity extends Activity {
    private ListView mLvUser;
    private Cursor mCursor;
    private DBHelper mHelper;
    private CursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mLvUser = (ListView) findViewById(R.id.lv_user);
        mHelper = new DBHelper(this, Contracts.DB_NAME, null, Contracts.DB_VERSION);
        mCursor = mHelper.getCursor();
        mCursorAdapter = new CursorAdapter(this, mCursor) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.item_listview, parent, false);
            }

            @SuppressLint("Range")
            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView username = view.findViewById(R.id.tv_username);
                TextView password = view.findViewById(R.id.tv_password);
                String strUserName = cursor.getString(cursor.getColumnIndex(Contracts.COLUMN_USERNAME));
                String strPassword = cursor.getString(cursor.getColumnIndex(Contracts.COLUMN_PASSWORD));

                username.setText(strUserName);
                password.setText(strPassword);
            }
        };
        mLvUser.setAdapter(mCursorAdapter);
        mLvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("Range")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mHelper.delete(mCursor.getInt(mCursor.getColumnIndex(Contracts.COLUMN_ID)));
                mCursor.requery();
                mCursorAdapter.notifyDataSetChanged();
            }
        });
    }
}
