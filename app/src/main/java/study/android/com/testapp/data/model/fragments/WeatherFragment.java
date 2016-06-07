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
public class WeatherFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public WeatherFragment() {
        this.weatherModel = new WeatherModel();
    }

    public WeatherModel getWeatherModel() {
        return weatherModel;
    }

    public void setWeatherModel(WeatherModel weatherModel) {
        this.weatherModel = weatherModel;
    }

    private WeatherModel weatherModel;
}

