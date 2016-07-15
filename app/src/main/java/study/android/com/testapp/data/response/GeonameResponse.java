package study.android.com.testapp.data.response;

import android.content.Context;
import android.util.Log;


import study.android.com.testapp.data.database.tables.GeonamesTable;
import study.android.com.testapp.data.model.Geonames;


/**
 * Created by tony on 31.03.16.
 */
public class GeonameResponse extends Response {


    @Override
    public void save(Context context) {
        Geonames geonames = getTypedAnswer();
        if (geonames != null) {
            GeonamesTable.clear(context);
            GeonamesTable.save(context, geonames.getGeonames());
        }
    }
}