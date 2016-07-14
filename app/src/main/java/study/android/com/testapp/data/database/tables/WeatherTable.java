package study.android.com.testapp.data.database.tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import study.android.com.testapp.data.model.WeatherModel;

/**
 * Created by tony on 07.07.16.
 */
public class WeatherTable {
    private static String TAG = "WeatherTable";
    public static final Uri URI = SqliteWeatherHelper.BASE_CONTENT_URI.buildUpon().appendPath(Requests.TABLE_NAME).build();


    public static void save(Context context, @NonNull WeatherModel weatherModel) {
        context.getContentResolver().insert(URI, toContentValues(weatherModel));
    }


    @NonNull
    public static ContentValues toContentValues(@NonNull WeatherModel weatherModel) {
        ContentValues values = new ContentValues();


        if (weatherModel.getGeoname() != null) {
            values.put(Columns.GEONAME, weatherModel.getGeoname());
        }
        if (weatherModel.getCondition() != null) {
            values.put(Columns.CONDITION, weatherModel.getCondition());
        }
        if (String.valueOf(weatherModel.getDateinf()) != null) {
            values.put(Columns.DATE_INF, weatherModel.getDateinf());
        }
        if (weatherModel.getTempInf() != null) {
            values.put(Columns.TEMP, weatherModel.getTempInf());
        }

        return values;
    }

    @NonNull
    public static WeatherModel fromCursor(@NonNull Cursor cursor) {

        String geoname = cursor.getString(cursor.getColumnIndex(Columns.GEONAME));
        String condition = cursor.getString(cursor.getColumnIndex(Columns.CONDITION));
        String dateInf = cursor.getString(cursor.getColumnIndex(Columns.DATE_INF));
        String temp = cursor.getString(cursor.getColumnIndex(Columns.TEMP));
        return new WeatherModel(geoname, dateInf, condition, temp);
    }


    public static void clear(Context context) {
        context.getContentResolver().delete(URI, null, null);
    }


    public interface Columns {
        String ID = "_id";
        String TEMP = "temp";
        String GEONAME = "geoname";
        String CONDITION = "condition";
        String DATE_INF = "dateInf";


    }

    public interface Requests {

        String TABLE_NAME = WeatherTable.class.getSimpleName();


        String CREATION_REQUEST = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                Columns.ID + " integer primary key autoincrement," +
                Columns.GEONAME + " VARCHAR(200), " +
                Columns.TEMP + " VARCHAR(200), " +
                Columns.CONDITION + " VARCHAR(200)," +
                Columns.DATE_INF + " VARCHAR(200)" + ");";

        String DROP_REQUEST = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}