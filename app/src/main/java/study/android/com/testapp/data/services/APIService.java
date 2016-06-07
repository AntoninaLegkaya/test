package study.android.com.testapp.data.services;

import java.util.List;


import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.Url;
import study.android.com.testapp.data.model.Airport;
import study.android.com.testapp.data.model.Geonames;
import study.android.com.testapp.data.model.Weather;

/**
 * Created by tony on 28.03.16.
 */
public interface APIService {
   // GET request for airports places

    @GET("/places/coords_to_places_ru.json?")
    Call<List<Airport>> airports(@Query("coords") String gps);

   // GET request for weather information

    @GET
    Call<Weather> getWhether(@Url String url);

    //GET request for cities list

    @GET("/citiesJSON?")
    Call<Geonames> getCities(@Query("north") double north,
                             @Query("south") double south, @Query("east") double east, @Query("west") double west,
                             @Query("lang") String lang, @Query("username") String username);

}
