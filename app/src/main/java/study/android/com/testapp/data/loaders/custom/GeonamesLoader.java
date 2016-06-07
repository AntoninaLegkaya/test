package study.android.com.testapp.data.loaders.custom;

import android.content.Context;
import android.util.Log;

import java.io.IOException;


import retrofit.Call;
import study.android.com.testapp.data.model.Geonames;
import study.android.com.testapp.data.response.GeonameResponse;
import study.android.com.testapp.data.response.RequestResult;
import study.android.com.testapp.data.response.Response;
import study.android.com.testapp.data.services.APIService;
import study.android.com.testapp.data.services.ApiFactory;

/**
 * Created by tony on 31.03.16.
 */
public class GeonamesLoader extends  BaseLoader {
    private String TAG="GeonamesLoader";
    private final double mEast;
    private final double mWest;
    private final double mNorth;
    private final double mSouth;
    private final String mLang;
    private final String mUsername;
    private final Context mContext;
    public GeonamesLoader(Context context, double mEast, double mWest, double mNorth, double mSouth, String mLang, String mUsername) {

        super(context);

        mContext = context;
        this.mEast = mEast;
        this.mWest = mWest;
        this.mNorth = mNorth;
        this.mSouth = mSouth;
        this.mLang = mLang;
        this.mUsername = mUsername;
    }
    @Override
    protected Response apiCall() throws IOException {
        Log.i("TAG", "Call Geonames API");
        APIService service = ApiFactory.getGeonameAPIInstance();
        Call<Geonames>call = service.getCities(mNorth,mSouth,mEast,mWest,mLang,mUsername);
        // deserialize the JSON response to the cities objects.
        Geonames result = call.execute().body();
        return new GeonameResponse()
                .setRequestResult(RequestResult.SUCCESS)
                .setAnswer(result);
    }


    @Override
    protected void onAbandon() {
        super.onAbandon();
        Log.i(TAG,"Geoname Loader Abandon");
    }

    @Override
    protected void onReset() {
        super.onReset();
        Log.i(TAG, "Geoname Loader Reset");
    }

}
