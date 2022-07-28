package approtrain.c2002l.phongvh;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {
    private ListView mLvContact;
    private CustomCursorAdapter mCursorAdapter;
    private DBController mController;
    private Cursor mCursor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mLvContact = (ListView) findViewById(R.id.lv_contact);
        mController = new DBController(this);
        mCursor = mController.getCursor();
        mCursorAdapter = new CustomCursorAdapter(this, mCursor, false);
        mLvContact.setAdapter(mCursorAdapter);
    }
}
