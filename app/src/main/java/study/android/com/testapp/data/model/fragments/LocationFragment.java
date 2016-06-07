package study.android.com.testapp.data.model.fragments;

import android.app.Fragment;
import android.os.Bundle;

import study.android.com.testapp.data.model.LocationModel;

/**
 * Created by tony on 21.05.16.
 */
public class LocationFragment extends Fragment {

    private final LocationModel locationObject;

    public LocationModel getLocationModel() {
        return locationObject;
    }

    public LocationFragment() {
        this.locationObject = new LocationModel();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


}
