package phongvh.approtrain.clientapplication;

import android.net.Uri;

public class Contracts {
    public static final String DB_NAME = "user_database";
    public static final int DB_VERSION = 1;

    // Table contract
    public static final String TABLE_NAME = "table_user";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    // Provider contract
    public static final String AUTHORITY = "phongvh.approtrain.sqliteexample.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");

    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.phongvh.user";
    public static final String CONTENT_ITEM_DIR = "vnd.android.cursor.dir/vnd.phongvh.user";
}
