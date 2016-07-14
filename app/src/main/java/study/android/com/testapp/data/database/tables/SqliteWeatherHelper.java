package study.android.com.testapp.data.database.tables;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/**
 * Created by tony on 07.07.16.
 */
public class SqliteWeatherHelper extends SQLiteOpenHelper {

    public static final String CONTENT_AUTHORITY = "study.android.com.testapp";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final String DATABASE_NAME = "study.android.weather.db";

    private static final int DATABASE_VERSION = 1;


    public SqliteWeatherHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WeatherTable.Requests.CREATION_REQUEST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(WeatherTable.Requests.DROP_REQUEST);
        onCreate(db);
    }
}

