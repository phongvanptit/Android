package phongvh.approtrain.clientapplication;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

public class MainActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        LoaderManager.getInstance(this).initLoader(100, null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id,
                                         @Nullable Bundle args) {
        CursorLoader cursorLoader = new CursorLoader(this, Contracts.CONTENT_URI, null, null,
                null, null);
        return cursorLoader;
    }

    @SuppressLint("Range")
    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader,
                               Cursor cursor) {
        if (null != cursor) {
            while (cursor.moveToNext()) {
                String username = cursor.getString(cursor.getColumnIndex(Contracts.COLUMN_USERNAME));
                String password = cursor.getString(cursor.getColumnIndex(Contracts.COLUMN_PASSWORD));
                Log.i(MainActivity.class.getSimpleName(),
                        "Username: " + username + ", Password: " + password);
            }
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
