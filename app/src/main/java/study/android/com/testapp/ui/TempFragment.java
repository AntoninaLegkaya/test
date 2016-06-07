package study.android.com.testapp.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import study.android.com.testapp.R;


/**
 * Created by tony on 04.04.16.
 */
public class TempFragment extends Fragment {
    @Bind(R.id.date_id)
    TextView dataView;
    @Bind(R.id.city_id)
    TextView cityView;
    @Bind(R.id.cond_id)
    TextView condView;
    @Bind(R.id.temp_id)
    TextView tempView;


    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_inform, container, false);
        ButterKnife.bind(this, view);
        // TODO Use fields...
        return view;
    }

}
