package study.android.com.testapp.data.loaders.custom;

import android.annotation.TargetApi;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Build;
import android.util.Log;


import java.io.IOException;

import study.android.com.testapp.data.response.RequestResult;
import study.android.com.testapp.data.response.Response;


/**
 * Created by tony on 28.03.16.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public abstract class BaseLoader extends AsyncTaskLoader <Response> {

    public BaseLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public Response loadInBackground() {
        try {
            Response response = apiCall();
            if (response.getRequestResult() == RequestResult.SUCCESS) {
                response.save(getContext());
                onSuccess();
            } else {
                onError();
            }
            return response;
        } catch (IOException e) {
            onError();
            return new Response();
        }
    }

    protected void onSuccess() {
    }

    protected void onError() {
    }

    protected abstract Response apiCall() throws IOException;
}
