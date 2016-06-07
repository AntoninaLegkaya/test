package study.android.com.testapp.data.model.fragments;

import android.app.Fragment;
import android.os.Bundle;

import study.android.com.testapp.data.model.Geonames;
import study.android.com.testapp.data.model.LocationModel;

/**
 * Created by tony on 26.05.16.
 */
public class GeonamesFragment extends Fragment {

    public void setGeonames(Geonames geonames) {
        this.geonames = geonames;
    }

    private  Geonames geonames;

    public Geonames getGeonames() {
        return geonames;
    }

    public GeonamesFragment() {
        this.geonames = new Geonames();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


}

