package study.android.com.testapp.data.database.tables;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.FileNotFoundException;

/**
 * Created by tony on 29.06.16.
 */
public class CustomContentProvider extends ContentProvider {
    private static final int GEONAMES_TABLE = 1;
    private static final int WEATHER_TABLE = 2;
    private static String TAG = "CustomContentProvider";
    private static final UriMatcher URI_MATCHER;
    private SQLiteDatabase database;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(SqliteGeonameHelper.CONTENT_AUTHORITY, GeonamesTable.Requests.TABLE_NAME, GEONAMES_TABLE);
        URI_MATCHER.addURI(SqliteWeatherHelper.CONTENT_AUTHORITY, WeatherTable.Requests.TABLE_NAME, WEATHER_TABLE);
    }

    private SqliteGeonameHelper sqliteGeonameHelper;
    private SqliteWeatherHelper sqliteWeatherHelper;

    @Override
    public boolean onCreate() {
        sqliteGeonameHelper = new SqliteGeonameHelper(getContext());
        sqliteWeatherHelper = new SqliteWeatherHelper(getContext());
        return true;
    }

    @Override
    @NonNull
    public String getType(@NonNull Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case GEONAMES_TABLE:
                database = sqliteGeonameHelper.getWritableDatabase();
                return GeonamesTable.Requests.TABLE_NAME;
            case WEATHER_TABLE:
                database = sqliteWeatherHelper.getWritableDatabase();
                return WeatherTable.Requests.TABLE_NAME;
            default:
                return "";
        }
    }

    @Nullable
    @Override
    public AssetFileDescriptor openAssetFile(Uri uri, String mode) throws FileNotFoundException {
        return super.openAssetFile(uri, mode);
    }

    @Override
    @NonNull
    public Cursor query(@NonNull Uri uri, String[] projection,
                        String selection, String[] selectionArgs, String sortOrder) {
        String table = getType(uri);


        if (TextUtils.isEmpty(table)) {
            throw new UnsupportedOperationException("No such table to query");
        } else {
            return database.query(table,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder);
        }
    }

    @Override
    @NonNull
    public Uri insert(@NonNull Uri uri, @NonNull ContentValues values) {
        String table = getType(uri);
        if (TextUtils.isEmpty(table)) {
            throw new UnsupportedOperationException("No such table to query");
        } else {
            long id = database.insertWithOnConflict(table, null, values, SQLiteDatabase.CONFLICT_REPLACE);
            return ContentUris.withAppendedId(uri, id);
        }
    }

    @Override
    public int bulkInsert(Uri uri, @NonNull ContentValues[] values) {

        String table = getType(uri);
        if (TextUtils.isEmpty(table)) {
            throw new UnsupportedOperationException("No such table to query");
        } else {
            int numInserted = 0;
            database.beginTransaction();
            try {
                for (ContentValues contentValues : values) {
                    long id = database.insertWithOnConflict(table, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
                    if (id > 0) {
                        numInserted++;
                    }
                }
                database.setTransactionSuccessful();
            } finally {
                database.endTransaction();
            }
            return numInserted;
        }
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        String table = getType(uri);
        if (TextUtils.isEmpty(table)) {
            throw new UnsupportedOperationException("No such table to query");
        } else {
            return database.delete(table, selection, selectionArgs);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @NonNull ContentValues values,
                      String selection, String[] selectionArgs) {
        String table = getType(uri);
        if (TextUtils.isEmpty(table)) {
            throw new UnsupportedOperationException("No such table to query");
        } else {
            return database.update(table, values, selection, selectionArgs);
        }
    }

}
