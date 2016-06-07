package study.android.com.testapp.data.response;

import android.content.Context;
import android.util.Log;

import java.util.List;

import study.android.com.testapp.data.model.Weather;


/**
 * Created by tony on 29.03.16.
 */
public class WeatherResponse extends Response {

    @Override
    public void save(Context contextt) {
        Weather weather = getTypedAnswer();
        if (weather != null) {

//                Query query = weather.getQuery();
//                Channel channel = query.getResults().getChannel();
//                if (channel != null) {
//                    String temp = channel.getItem().getCondition().getTemp();
//                    Log.i("Main", "WeatherResponse: temprecher: " + temp);
//                    tempView.setText(temp);
//                    dateView.setText(channel.getItem().getCondition().getDate());
//                    cityView.setText(channel.getLocation().getCity());
//                    condView.setText(channel.getItem().getCondition().getText());
//                }


//            AirportsTable.save(context, airports);
        }

    }
}
