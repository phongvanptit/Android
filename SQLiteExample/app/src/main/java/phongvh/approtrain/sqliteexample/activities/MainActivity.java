package phongvh.approtrain.sqliteexample.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import phongvh.approtrain.sqliteexample.R;
import phongvh.approtrain.sqliteexample.database.Contracts;
import phongvh.approtrain.sqliteexample.database.DBHelper;
import phongvh.approtrain.sqliteexample.model.User;

public class MainActivity extends Activity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private ListView mLvUser;
    private Cursor mCursor;
    private DBHelper mHelper;
    private CursorAdapter mCursorAdapter;
    private static final int REQUEST_EDIT = 100000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mLvUser = (ListView) findViewById(R.id.lv_user);
        mHelper = new DBHelper(this, Contracts.DB_NAME, null, Contracts.DB_VERSION);
        mCursor = mHelper.getCursorWithSort("DESC");
        mCursorAdapter = new CursorAdapter(this, mCursor) {
            @Override
            public View newView(Context context,
                                Cursor cursor,
                                ViewGroup parent) {
                return LayoutInflater.from(context).inflate(R.layout.item_listview, parent, false);
            }

            @SuppressLint("Range")
            @Override
            public void bindView(View view,
                                 Context context,
                                 Cursor cursor) {
                TextView username = view.findViewById(R.id.tv_username);
                TextView password = view.findViewById(R.id.tv_password);
                ImageView delete = view.findViewById(R.id.btn_delete);
                String strUserName = cursor.getString(cursor.getColumnIndex(Contracts.COLUMN_USERNAME));
                String strPassword = cursor.getString(cursor.getColumnIndex(Contracts.COLUMN_PASSWORD));
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mHelper.delete(mCursor.getInt(mCursor.getColumnIndex(Contracts.COLUMN_ID)));
                        mCursor.requery();
                        mCursorAdapter.notifyDataSetChanged();
                    }
                });
                username.setText(strUserName);
                password.setText(strPassword);
            }
        };
        mLvUser.setAdapter(mCursorAdapter);
        mLvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("Range")
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                mCursor.moveToPosition(position);
                intent.putExtra("username",
                        mCursor.getString(mCursor.getColumnIndex(Contracts.COLUMN_USERNAME)));
                intent.putExtra("password",
                        mCursor.getString(mCursor.getColumnIndex(Contracts.COLUMN_PASSWORD)));
                intent.putExtra("id", mCursor.getInt(mCursor.getColumnIndex(Contracts.COLUMN_ID)));
                startActivityForResult(intent, REQUEST_EDIT);
            }
        });
        readFromContentProvider();
    }

    @SuppressLint("Range")
    public void readFromContentProvider() {
        Cursor cursor = getContentResolver().query(Contracts.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Log.i(TAG,
                        "username: " + cursor.getString(cursor.getColumnIndex(Contracts.COLUMN_USERNAME)) +
                                ",password: " + cursor.getString(cursor.getColumnIndex(Contracts.COLUMN_PASSWORD)));
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_EDIT && resultCode == RESULT_OK) {
            User user = new User(data.getStringExtra("username"), data.getStringExtra("password"));
            int id = data.getIntExtra("id", 0);
            mHelper.update(id, user);
            mCursor.requery();
            mCursorAdapter.notifyDataSetChanged();
        }
    }
}
