package study.android.com.testapp.ui;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

import study.android.com.testapp.R;
import study.android.com.testapp.data.model.WeatherModel;
import study.android.com.testapp.data.model.fragments.WeatherFragmentView;

/**
 * Created by tony on 28.05.16.
 */


public class WeatherViewActivity extends Activity {
    private String TAG = "WeatherViewActivity";
    public static String EXTRA_WEATHER = "extra_weather";
    public static final String TAG_WEATHER_VIEW = "TAG_WEATHER_VIEW";
    private FragmentTransaction fTrans;
    private WeatherModel weatherModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_content_activity);
        WeatherFragmentView retainedWeatherFragment =
                (WeatherFragmentView) getFragmentManager().findFragmentByTag(TAG_WEATHER_VIEW);
        extractWeatherFragment(retainedWeatherFragment);


    }


    private void extractWeatherFragment(WeatherFragmentView retainedWeatherFragment) {
        Log.i(TAG, "----------------------exstractdRetainedWeatherFragmentt-----------------------------" + "\n");
        weatherModel = getIntent().getParcelableExtra(EXTRA_WEATHER);
        if (retainedWeatherFragment != null) {

//            weatherModel = retainedWeatherFragment.getWeatherModel();
            if (weatherModel != null) {
                Log.i(TAG, "Weather from WeatherFragmentView gets!! You choose: " + weatherModel.getGeoname());
                retainedWeatherFragment= new WeatherFragmentView(weatherModel);
                fTrans = getFragmentManager().beginTransaction();
                fTrans.remove(retainedWeatherFragment);
                fTrans.add(R.id.frgmCont, retainedWeatherFragment);
                fTrans.commit();
            } else {
                Log.e(TAG, "Weather Fragment is empry");
            }

        } else {
            try {

                Log.i(TAG, "Weather from WeatherFragment gets!! You choose: " + weatherModel.getGeoname());
                final WeatherFragmentView workerFragment = new WeatherFragmentView(weatherModel);
                getFragmentManager().beginTransaction()
                        .add(R.id.frgmCont, workerFragment, TAG_WEATHER_VIEW)
                        .commit();

            } catch (Exception e) {
                Log.e(TAG, "Could not commit WeatherFragmentView!");
            }


        }


        Log.i(TAG, "\n" + "-----------------------------------------------------------------------");
    }
}
