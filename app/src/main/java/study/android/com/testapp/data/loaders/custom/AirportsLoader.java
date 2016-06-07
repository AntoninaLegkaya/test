package study.android.com.testapp.data.loaders.custom;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import retrofit.Call;
import study.android.com.testapp.data.model.Airport;
import study.android.com.testapp.data.response.AirportsResponse;
import study.android.com.testapp.data.response.RequestResult;
import study.android.com.testapp.data.response.Response;
import study.android.com.testapp.data.services.APIService;
import study.android.com.testapp.data.services.ApiFactory;

/**
 * Created by tony on 28.03.16.
 */
public class AirportsLoader  extends BaseLoader {

    private final String mGps;

    public AirportsLoader(Context context, String gps) {
        super(context);
        mGps = gps;
    }

    @Override
    protected Response apiCall() throws IOException {
        Log.i("Main", "Call aeroport API");
        APIService service = ApiFactory.getAirportsAPIInstance();
        Call<List<Airport>> call = service.airports(mGps);
        List<Airport> airports = call.execute().body();
        return new AirportsResponse()
                .setRequestResult(RequestResult.SUCCESS)
                .setAnswer(airports);
    }
}
