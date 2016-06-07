package study.android.com.testapp.data.model.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import study.android.com.testapp.R;
import study.android.com.testapp.data.model.WeatherModel;

/**
 * Created by tony on 28.05.16.
 */
public class WeatherFragmentView extends Fragment {
    @Bind(R.id.date_id)
    TextView dataView;
    @Bind(R.id.city_id)
    TextView cityView;
    @Bind(R.id.cond_id)
    TextView condView;
    @Bind(R.id.temp_id)
    TextView tempView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_inform, container, false);
        ButterKnife.bind(this, view);
        if (weatherModel != null && weatherModel.getDateinf() != null && weatherModel.getGeoname() != null &&
                weatherModel.getCondition() != null && weatherModel.getTempInf() != null) {
            dataView.setText(weatherModel.getDateinf().toString());
            cityView.setText(weatherModel.getGeoname());
            condView.setText(weatherModel.getCondition());
            tempView.setText(weatherModel.getTempInf().toString());
        }

        return view;
    }

    public WeatherFragmentView(WeatherModel weatherModel ) {
        this.weatherModel = weatherModel;
    }

    public WeatherModel getWeatherModel() {
        return weatherModel;
    }

    public void setWeatherModel(WeatherModel weatherModel) {
        this.weatherModel = weatherModel;
    }

    private WeatherModel weatherModel;
}
