package study.android.com.testapp.data.services;

import android.support.annotation.NonNull;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import io.realm.RealmObject;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by tony on 28.03.16.
 */
public class ApiFactory {
    private static final int TIMEOUT = 60;
    private static final int WRITE_TIMEOUT = 120;
    private static final int CONNECT_TIMEOUT = 10;
    public static final String API_ENDPOINT = "http://nano.aviasales.ru";
    public static final  String URL_WEATHER_API ="https://query.yahooapis.com/v1/public";
    public static final String URL_CITIES_API="http://api.geonames.org";

    private static final OkHttpClient CLIENT = new OkHttpClient();

    private static APIService service;


    static {
        CLIENT.setConnectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS);
        CLIENT.setWriteTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        CLIENT.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);
    }

    private static final Gson GSON = new GsonBuilder()
            .setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return f.getDeclaringClass().equals(RealmObject.class);
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            })
            .create();
    @NonNull
    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(GSON))
                .client(CLIENT)
                .build();
    }
    //Compose  airports request
    @NonNull
    public static APIService getAirportsAPIInstance() {
        return getRetrofit().create(APIService.class);
    }
    //Compose weather request
    public static APIService getWeatherAPIInstance(){
            Retrofit retrofit= new Retrofit.Builder()
                    .baseUrl(URL_WEATHER_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
         return retrofit.create(APIService.class);
    }
   // Compose geonames request
    public static APIService getGeonameAPIInstance(){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(URL_CITIES_API)
                .addConverterFactory(GsonConverterFactory.create(GSON))
                .build();
        return retrofit.create(APIService.class);




    }
}
