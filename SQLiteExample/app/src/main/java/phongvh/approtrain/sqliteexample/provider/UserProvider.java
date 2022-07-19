package phongvh.approtrain.sqliteexample.provider;

import static phongvh.approtrain.sqliteexample.database.Contracts.AUTHORITY;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import phongvh.approtrain.sqliteexample.database.Contracts;
import phongvh.approtrain.sqliteexample.database.DBHelper;

public class UserProvider extends ContentProvider {
    private DBHelper dbHelper;
    public static final UriMatcher URI_MATCHER = buildUriMathcer();
    public static final String PATH = "user";
    public static final int PATH_TOKEN = 100;
    public static final String PATH_FOR_ID = "user/*";
    public static final int PATH_FOR_ID_TOKEN = 200;

    private static UriMatcher buildUriMathcer() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = AUTHORITY;
        matcher.addURI(authority, PATH, PATH_TOKEN);
        matcher.addURI(authority, PATH_FOR_ID, PATH_FOR_ID_TOKEN);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext(), Contracts.DB_NAME, null, Contracts.DB_VERSION);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri,
                        @Nullable String[] projection,
                        @Nullable String selection,
                        @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        final int match = URI_MATCHER.match(uri);
        switch (match) {
            case PATH_TOKEN:
                SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
                builder.setTables(Contracts.TABLE_NAME);
                return builder.query(database, projection, selection, selectionArgs, null, null,
                        sortOrder);
            case PATH_FOR_ID_TOKEN:
                int userId = (int) ContentUris.parseId(uri);
                SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
                queryBuilder.setTables(Contracts.TABLE_NAME);
                queryBuilder.appendWhere(Contracts.COLUMN_ID + " = " + userId);
                return queryBuilder.query(database, projection, selection, selectionArgs, null, null,
                        sortOrder);
            default:
                return null;
        }

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = URI_MATCHER.match(uri);
        switch (match) {
            case PATH_TOKEN:
                return Contracts.CONTENT_ITEM_DIR;
            case PATH_FOR_ID_TOKEN:
                return Contracts.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Uri " + uri + " is not supported!");
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri,
                      @Nullable ContentValues values) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int token = URI_MATCHER.match(uri);
        switch (token) {
            case PATH_TOKEN:
                long id = database.insert(Contracts.TABLE_NAME, null, values);
                if (id != -1) {
                    getContext().getContentResolver().notifyChange(uri, null);
                }
            default:
                throw new UnsupportedOperationException("Uri " + uri + " is not supported!");
        }
    }

    @Override
    public int delete(@NonNull Uri uri,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri,
                      @Nullable ContentValues values,
                      @Nullable String selection,
                      @Nullable String[] selectionArgs) {
        return 0;
    }
}
