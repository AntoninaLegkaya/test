package study.android.com.testapp.data.response;

import android.content.Context;


import study.android.com.testapp.data.database.tables.WeatherTable;
import study.android.com.testapp.data.model.Channel;
import study.android.com.testapp.data.model.Query;
import study.android.com.testapp.data.model.Weather;
import study.android.com.testapp.data.model.WeatherModel;


/**
 * Created by tony on 29.03.16.
 */
public class WeatherResponse extends Response {

    @Override
    public void save(Context context) {
        Weather weather = getTypedAnswer();
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
                        WeatherModel weatherModel = new WeatherModel(geoname, dataInf, condition, temp);
                        WeatherTable.save(context, weatherModel);

                    } else

                    {


                    }
                } else

                {


                }
            } else

            {


            }


        } else {


        }

    }

}

