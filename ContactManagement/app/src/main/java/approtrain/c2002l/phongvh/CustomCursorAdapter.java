package approtrain.c2002l.phongvh;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomCursorAdapter extends CursorAdapter {

    public CustomCursorAdapter(Context context,
                               Cursor c,
                               boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context,
                        Cursor cursor,
                        ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @SuppressLint("Range")
    @Override
    public void bindView(View view,
                         Context context,
                         Cursor cursor) {
        ImageView viewImportant = (ImageView) view.findViewById(R.id.view_important);
        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        TextView tvDate = (TextView) view.findViewById(R.id.tv_create_date);

        viewImportant.setBackgroundColor(cursor.getInt(cursor.getColumnIndex(Contract.COLUMN_IMPORTANT)) > 0 ? Color.RED : Color.GREEN);
        tvName.setText(cursor.getString(cursor.getColumnIndex(Contract.COLUMN_NAME)));
        tvDate.setText(cursor.getString(cursor.getColumnIndex(Contract.COLUMN_DATE)));
    }
}
