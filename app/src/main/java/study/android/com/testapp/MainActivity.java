package study.android.com.testapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;


import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import study.android.com.testapp.data.adapters.RVAdapter;
import study.android.com.testapp.data.interfaces.GeomeIsSelected;
import study.android.com.testapp.data.interfaces.Observer;
import study.android.com.testapp.data.loaders.custom.GeonamesLoader;
import study.android.com.testapp.data.loaders.custom.WeatherLoader;
import study.android.com.testapp.data.model.Airport;
import study.android.com.testapp.data.model.Channel;
import study.android.com.testapp.data.model.Geoname;
import study.android.com.testapp.data.model.Geonames;
import study.android.com.testapp.data.model.LocationModel;
import study.android.com.testapp.data.model.Query;
import study.android.com.testapp.data.model.Weather;
import study.android.com.testapp.data.model.WeatherModel;
import study.android.com.testapp.data.model.fragments.GeonamesFragment;
import study.android.com.testapp.data.model.fragments.LocationFragment;
import study.android.com.testapp.data.model.fragments.WeatherFragment;
import study.android.com.testapp.data.response.Response;
import study.android.com.testapp.data.services.LocationService;
import study.android.com.testapp.ui.WeatherViewActivity;

public class MainActivity extends AppCompatActivity implements Observer, LoaderManager.LoaderCallbacks<Response>, GeomeIsSelected, View.OnClickListener {


    private String TAG = "MainActivity";
    private static final String TAG_LOCATION = "TAG_LOCATION";
    private static final String TAG_GEONAMES = "TAG_GEONAMES";
    public static final String TAG_WEATHER_MAIN = "TAG_WEATHER_MAIN";
    private LocationModel locationModel;
    private WeatherModel weatherModel;
    private LocationManager locationManager;
    private Geonames geonamesModel;
    private String geoname;
    private String countryCode;
    private Loader<Geonames> loader;
    @Bind(R.id.view_progress)
    View mProgress;
    @Bind(R.id.update)
    Button updateClick;
    RecyclerView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        final LocationFragment retainedWorkerFragment =
                (LocationFragment) getFragmentManager().findFragmentByTag(TAG_LOCATION);
        final GeonamesFragment retainedGeonamesFragment =
                (GeonamesFragment) getFragmentManager().findFragmentByTag(TAG_GEONAMES);
        final WeatherFragment retainedWeatherFragment =
                (WeatherFragment) getFragmentManager().findFragmentByTag(TAG_WEATHER_MAIN);
        extractLocationFragment(retainedWorkerFragment);
        exstractdRetainedGeonamesFragment(retainedGeonamesFragment);
        extractWeatherFragment(retainedWeatherFragment);
        updateClick.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Log.i(TAG, "----------------------Button Update Press-----------------------------" + "\n");
        Log.i(TAG, "Updated Location");
        final LocationFragment locationFragment =
                (LocationFragment) getFragmentManager().findFragmentByTag(TAG_LOCATION);
        final GeonamesFragment geonamesFragment =
                (GeonamesFragment) getFragmentManager().findFragmentByTag(TAG_GEONAMES);

        if (locationFragment != null&&geonamesFragment!=null) {
            getFragmentManager().beginTransaction()
                    .remove(locationFragment)
                    .commit();
            getFragmentManager().beginTransaction()
                    .remove(geonamesFragment)
                    .commit();
        }

        final LocationFragment updateLocationFragment = new LocationFragment();
        getFragmentManager().beginTransaction().add(updateLocationFragment, TAG_LOCATION).commit();

        final GeonamesFragment udateGeonamesFragment = new GeonamesFragment();
        getFragmentManager().beginTransaction().add(udateGeonamesFragment, TAG_GEONAMES).commit();

        locationManager = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);
        locationModel = updateLocationFragment.getLocationModel();
        locationModel.registerObserver(this);
        locationModel.startGetLocation(this, locationManager);
        geonamesModel = udateGeonamesFragment.getGeonames();




    }
    private void extractLocationFragment(LocationFragment retainedWorkerFragment) {
        Log.i(TAG, "----------------------extractLocationFragment-----------------------------" + "\n");
        if (retainedWorkerFragment != null) {
            locationModel = retainedWorkerFragment.getLocationModel();
            locationModel.registerObserver(this);
            Log.i(TAG, "Location from LocationFragment gets!!");
        } else {
            final LocationFragment workerFragment = new LocationFragment();
            getFragmentManager().beginTransaction()
                    .add(workerFragment, TAG_LOCATION)
                    .commit();
            locationManager = (LocationManager) this
                    .getSystemService(this.LOCATION_SERVICE);
            locationModel = workerFragment.getLocationModel();
            locationModel.registerObserver(this);
            locationModel.startGetLocation(this, locationManager);


        }
        Log.i(TAG, "\n" + "---------------------------------------------------------");
    }

    private void exstractdRetainedGeonamesFragment(GeonamesFragment retainedGeonamesFragment) {
        Log.i(TAG, "----------------------exstractdRetainedGeonamesFragment-----------------------------" + "\n");
//        rv = (RecyclerView) findViewById(R.id.rv);
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        rv.setLayoutManager(llm);
        if (retainedGeonamesFragment != null) {
            geonamesModel = retainedGeonamesFragment.getGeonames();
            Log.i(TAG, "Geonames from GeonamesFragment gets!!");
            if (geonamesModel != null) {
                Log.i(TAG, "Initialization RV Adapter");
                initRVAdapter(geonamesModel.getGeonames());

            } else {
                Log.e(TAG, "Geonames Fragment is empry");
            }

        } else {
            final GeonamesFragment workerFragment = new GeonamesFragment();
            getFragmentManager().beginTransaction()
                    .add(workerFragment, TAG_GEONAMES)
                    .commit();
            geonamesModel = workerFragment.getGeonames();

        }
        Log.i(TAG, "\n" + "-----------------------------------------------------------------------");
    }

    private void extractWeatherFragment(WeatherFragment retainedWeatherFragment) {
        Log.i(TAG, "----------------------exstractdRetainedWeatherFragmentt-----------------------------" + "\n");
        if (retainedWeatherFragment != null) {
            weatherModel = retainedWeatherFragment.getWeatherModel();
            Log.i(TAG, "Weather from WeatherFragmentView gets!!");
            if (weatherModel != null) {

            } else {
                Log.e(TAG, "Weather Fragment is empry");
            }

        } else {
            final WeatherFragment workerFragment = new WeatherFragment();
            getFragmentManager().beginTransaction()
                    .add(workerFragment, TAG_WEATHER_MAIN)
                    .commit();
            weatherModel = workerFragment.getWeatherModel();

        }
        Log.i(TAG, "\n" + "-----------------------------------------------------------------------");
    }

    @Override
    public Loader<Response> onCreateLoader(int id, Bundle args) {
        Log.i(TAG, "-----------------------onCreateLoader----------------");
        Log.i(TAG, "onCreateLoader: create loader by id: " + id);
        switch (id) {
            case R.id.cities_loader:
                if (locationModel != null) {
                    double east = locationModel.coordBox[0];
                    double west = locationModel.coordBox[1];
                    double north = locationModel.coordBox[2];
                    double south = locationModel.coordBox[3];
                    String lang = "en";
                    String username = "antoninalegkaya";
                    return new GeonamesLoader(this, east, west, north, south, lang, username);
                }

            case R.id.weather_loader:
                return getWeatherLoader(geoname, countryCode);


            case R.id.airports_loader:
//                String coordinats = String.valueOf(locationManager.latitude) + "," + String.valueOf(locationManager.longitude);
//                Log.i("Main", "airports_loader: coordinats : " + coordinats);
//                return new AirportsLoader(this, coordinats);


            default:
                return null;
        }
    }


    @Override
    public void onLoadFinished(Loader<Response> loader, Response data) {
        int id = loader.getId();

        if (id == R.id.cities_loader) {
            Log.i(TAG, "----------------------Load Geonames Finished-----------------------------");
            Geonames geonames = data.getTypedAnswer();

            if (geonames != null) {
                geonamesModel.setGeonames(geonames.getGeonames());
                initRVAdapter(geonamesModel.getGeonames());

            } else {
                Log.e(TAG, "Geoname loader is empty");
                Toast.makeText(this, "Couldn't get cities information", Toast.LENGTH_SHORT).show();
            }
            Log.i(TAG, "\n" + "----------------------------------------------------------------");
        }

        if (id == R.id.weather_loader) {
            Log.i(TAG, "----------------------onLoadFinished Weather-----------------------------");
            Weather weather = data.getTypedAnswer();
            if (weather != null) {
                Query query = weather.getQuery();
                if (query != null) {
                    if (query.getResults() != null) {
                        Channel channel = query.getResults().getChannel();
                        if (channel != null) {
                            String temp = channel.getItem().getCondition().getTemp();
                            String geoname = channel.getLocation().getCity();
                            String condition = channel.getItem().getCondition().getText();
                            String dataInf = channel.getItem().getCondition().getDate();
                            weatherModel = new WeatherModel(geoname, dataInf, condition, temp);
//                            Log.i(TAG, "Weather temprature: " + temp);
                            showProgress(false);
                            Intent intent = new Intent(this, WeatherViewActivity.class);
                            intent.putExtra(WeatherViewActivity.EXTRA_WEATHER, weatherModel);
                            startActivity(intent);

                        } else

                        {
                            Toast.makeText(this, "Couldn't get weather information", Toast.LENGTH_SHORT).show();

                        }
                    } else

                    {
                        Toast.makeText(this, "Couldn't get weather information", Toast.LENGTH_SHORT).show();

                    }
                } else

                {
                    Toast.makeText(this, "Couldn't get weather information", Toast.LENGTH_SHORT).show();

                }


            } else {
                Toast.makeText(this, "Couldn't get weather information", Toast.LENGTH_SHORT).show();

            }

        }
        if (id == R.id.airports_loader) {
            List<Airport> airports = data.getTypedAnswer();
            if (airports != null) {
                for (Airport airport : airports) {
                    Log.i("Main", " IATA:  " + airport.getIata());
                    Log.i("Main", "NAME: " + airport.getName());
                    if (airport.getAirportName() != null) {
                        Log.i("Main", "AIRPORT_NAME: " + airport.getAirportName());
                    }
                }
            }
        }

        getLoaderManager().destroyLoader(id);
    }


    private void initRVAdapter(List<Geoname> geonames) {
        Log.i(TAG, "----------------------initRVAdapter-----------------------------");
        try {

            RVAdapter adapter = new RVAdapter(this, geonames);
            rv.setAdapter(adapter);
            showProgress(false);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }

    @Override
    public void onLoaderReset(Loader<Response> loader) {
        loader = null;
        Log.i(TAG, "Loader Reset");

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.i(TAG, " ------------------Method: onRequestPermissionsResult()-----------------------" + "\n");
        if (requestCode == LocationService.MY_PERMISSION_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission  granted " + "\n" + " -----------------------------------------");
            locationModel.startGetLocation(this, locationManager);

        } else {
            Log.i(TAG, "Could not get permissions " + "\n" + "-----------------------------------------");
        }
    }


    @NonNull
    private WeatherLoader getWeatherLoader(String geoname, String countryCode) {
        return new WeatherLoader(this, geoname, countryCode);
    }

    @Override
    public void getDataToRequest(String geoname, String countryCode) {
        Log.i(TAG, "----------------------Request weatherData for " + geoname + " -----------------------------");
//        showProgress(true);
        this.geoname = geoname;
        this.countryCode = countryCode;
        getLoaderManager().initLoader(R.id.weather_loader, Bundle.EMPTY, this);
    }


    @Override
    public void onSucceeded(Object model) {
        Log.i(TAG, "onLocationSucceeded");
        showProgress(false);
        locationModel.stopGetLocation();
        Toast.makeText(this, "Location gets", Toast.LENGTH_SHORT).show();
        getLoaderManager().initLoader(R.id.cities_loader, Bundle.EMPTY, this);

    }

    @Override
    public void onFailed(Object model) {
        Log.i(TAG, "onLocationFailed");
        showProgress(false);
        Toast.makeText(this, "Could not get location. Start task again!", Toast.LENGTH_SHORT).show();

    }

    private void showProgress(final boolean show) {
        mProgress.setVisibility(show ? View.VISIBLE : View.GONE);
    }


    @Override
    public void onResume() {
//        Log.i(TAG, "onResume");
        super.onResume();

    }

    @Override
    protected void onDestroy() {
//        Log.i(TAG, "onDestroy");
        super.onDestroy();
        locationModel.unregisterObserver(this);

        if (isFinishing()) {
            locationModel.stopGetLocation();
        }
    }

    @Override
    public void onStarted(Object model) {
        showProgress(true);
    }


    @Override
    public void onStart() {
//        Log.i(TAG, "onStart");
        super.onStart();
    }

    @Override
    public void onStop() {
//        Log.i(TAG, "onStop");
        super.onStop();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
