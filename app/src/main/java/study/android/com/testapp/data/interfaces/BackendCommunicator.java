package study.android.com.testapp.data.interfaces;

import android.content.Context;
import android.location.LocationManager;
import android.os.Handler;

import study.android.com.testapp.data.model.LocationModel;
import study.android.com.testapp.data.services.LocationService;

/**
 * Created by tony on 21.05.16.
 */
public interface BackendCommunicator {
    String getProvider(LocationService locationService) throws InterruptedException;
    LocationModel getLocationModel(LocationService locationService,String provider) throws InterruptedException;



}
