package study.android.com.testapp.data.services;

import retrofit.Callback;
import retrofit.Response;

/**
 * Created by tony on 28.03.16.
 */
public abstract class RetrofitCallback<T> implements Callback<T> {
    @Override
    public void onResponse(Response<T> response) {
    }

    @Override
    public void onFailure(Throwable t) {
    }
}
