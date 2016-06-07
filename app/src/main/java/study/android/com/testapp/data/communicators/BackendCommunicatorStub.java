package study.android.com.testapp.data.communicators;

import android.content.Context;
import android.location.LocationManager;
import android.os.Handler;

import study.android.com.testapp.data.interfaces.BackendCommunicator;
import study.android.com.testapp.data.model.LocationModel;
import study.android.com.testapp.data.services.LocationService;

/**
 * Created by tony on 21.05.16.
 */
public class BackendCommunicatorStub implements BackendCommunicator {


    @Override
    public String getProvider(LocationService locationService) throws InterruptedException {


        return locationService.marshmallowGPSPremissionCheck();
    }

    @Override
    public LocationModel getLocationModel(LocationService locationService, String provider) throws InterruptedException {
        return locationService.callUIThreadForLocation(provider);
    }


}
