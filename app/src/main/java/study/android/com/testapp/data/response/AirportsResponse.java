package study.android.com.testapp.data.response;

import android.content.Context;

import java.util.List;

import study.android.com.testapp.data.database.tables.AirportsTable;
import study.android.com.testapp.data.model.Airport;


/**
 * Created by tony on 28.03.16.
 */
public class AirportsResponse extends Response {



    @Override
    public void save(Context context) {
        List<Airport> airports = getTypedAnswer();
        if (airports != null) {
            AirportsTable.save(context, airports);
        }
    }

}
