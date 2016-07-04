package study.android.com.testapp.data.database.tables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import study.android.com.testapp.data.model.Airport;
import study.android.com.testapp.data.model.Geoname;

/**
 * Created by tony on 29.06.16.
 */
public class GeonamesTable {
    private static String TAG = "GeonamesTable";
    public static final Uri URI = SqliteHelper.BASE_CONTENT_URI.buildUpon().appendPath(Requests.TABLE_NAME).build();


    public static void save(Context context, @NonNull Geoname geoname
    ) {
        context.getContentResolver().insert(URI, toContentValues(geoname));
    }

    public static void save(Context context, @NonNull List<Geoname> geoname) {
        ContentValues[] values = new ContentValues[geoname.size()];
        Log.i(TAG, "Save in " + Requests.TABLE_NAME);
        for (int i = 0; i < geoname.size(); i++) {

            values[i] = toContentValues(geoname.get(i));

        }
        context.getContentResolver().bulkInsert(URI, values);
    }

    @NonNull
    public static ContentValues toContentValues(@NonNull Geoname geoname) {
        ContentValues values = new ContentValues();


        if (geoname.getName() != null) {
            values.put(Columns.NAME, geoname.getName());
        }
        if (geoname.getCountrycode() != null) {
            values.put(Columns.CONTRY_CODE, geoname.getCountrycode());
        }
        if (String.valueOf(geoname.getPopulation()) != null) {
            values.put(Columns.POPULATION, String.valueOf(geoname.getPopulation()));
        }
        return values;
    }

    @NonNull
    public static Geoname fromCursor(@NonNull Cursor cursor) {

        String name = cursor.getString(cursor.getColumnIndex(Columns.NAME));
        String country_code = cursor.getString(cursor.getColumnIndex(Columns.CONTRY_CODE));
        String population = cursor.getString(cursor.getColumnIndex(Columns.POPULATION));
        return new Geoname(name, country_code, population);
    }

    @NonNull
    public static List<Geoname> listFromCursor(@NonNull Cursor cursor) {
        List<Geoname> geoname= new ArrayList<>();
        if (!cursor.moveToFirst()) {
            return geoname;
        }
        try {
            do {
                geoname.add(fromCursor(cursor));
            } while (cursor.moveToNext());
            return geoname;
        } finally {
            cursor.close();
        }
    }

    public static void clear(Context context) {
        context.getContentResolver().delete(URI, null, null);
    }


    public interface Columns {
        String ID = "_id";
        String NAME = "geoname";
        String CONTRY_CODE = "country_code";
        String POPULATION = "population";
    }

    public interface Requests {

        String TABLE_NAME = GeonamesTable.class.getSimpleName();


        String CREATION_REQUEST = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                Columns.ID + " integer primary key autoincrement," +
                Columns.NAME + " VARCHAR(200), " +
                Columns.CONTRY_CODE + " VARCHAR(200), " +
                Columns.POPULATION + " VARCHAR(200)" + ");";

        String DROP_REQUEST = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
