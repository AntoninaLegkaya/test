package study.android.com.testapp.data.loaders.custom;

import android.content.Context;
import android.util.Log;

import java.io.IOException;


import retrofit.Call;
import study.android.com.testapp.data.model.Weather;
import study.android.com.testapp.data.response.RequestResult;
import study.android.com.testapp.data.response.Response;
import study.android.com.testapp.data.response.WeatherResponse;
import study.android.com.testapp.data.services.APIService;
import study.android.com.testapp.data.services.ApiFactory;

/**
 * Created by tony on 29.03.16.
 */
public class WeatherLoader extends BaseLoader {
    private String TAG = "WeatherLoader";
    private final String mCity;
    private final String mAK;
    private final Context mContext;
    public WeatherLoader(Context context, String city,String ak) {

        super(context);
        Log.i(TAG, "-----------------WeatherLoader constructor--------------------");
        mCity = city;
        mAK = ak;
        mContext = context;
    }
    @Override
    protected Response apiCall() throws IOException {
        Log.i(TAG, "-----------------Call weather API--------------------");
        APIService service = ApiFactory.getWeatherAPIInstance();
        Call<Weather> call = service.getWhether(getAPIServiceURL(mCity,mAK));
        Weather weather = call.execute().body();
        return new WeatherResponse()
                .setRequestResult(RequestResult.SUCCESS)
                .setAnswer(weather);
    }

    String getAPIServiceURL(String city,String ak){

        String URLQuery=ApiFactory.URL_WEATHER_API+"/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22" +
                city +
                "%2C%20" +
                ak +
                "%22)and%20u%3D'c'&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

        return URLQuery;
    }

    @Override
    protected void onSuccess() {
        Log.i(TAG, "WeatherLoader: Data are loaded ");
    }

    @Override
    protected void onError() {
        Log.i(TAG, "WeatherLoader: Data not loaded ");
    }


}
