package study.android.com.testapp.data.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import study.android.com.testapp.MainActivity;
import study.android.com.testapp.R;
import study.android.com.testapp.data.interfaces.GeomeIsSelected;
import study.android.com.testapp.data.model.Geoname;

/**
 * Created by tony on 25.05.16.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CityViewHolder> {
    private String TAG = "RVAdapter";
    private Context context;

    public class CityViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView cityName;
        TextView cityPopulation;
        ImageView personPhoto;


        CityViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            cityName = (TextView) itemView.findViewById(R.id.city_name);
            cityPopulation = (TextView) itemView.findViewById(R.id.city_population);


        }
    }

    List<Geoname> geonames;

    public RVAdapter(Context context, List<Geoname> geonames) {
        this.context = context;
        this.geonames = geonames;
    }

    @Override
    public int getItemCount() {
        return geonames.size();
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
        CityViewHolder cvh = new CityViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(CityViewHolder cityViewHolder, int i) {
        CityClickListener cityClickListener;
        if (geonames != null) {
            Log.i(TAG, "\n" + "---------------------onBindViewHolder--------------");
            final String geoname = geonames.get(i).getName();
            final int geopopulation = geonames.get(i).getPopulation();
            if (geoname != null) {
                cityViewHolder.cityName.setText(geonames.get(i).getName());
            }
            cityViewHolder.cityPopulation.setText(String.valueOf(geopopulation));
            cityClickListener = new CityClickListener(geoname,
                    geonames.get(i).getCountrycode());

            cityViewHolder.cv.setOnClickListener(cityClickListener);

        } else {
            Log.e(TAG, "Geonemes cames empty!");
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    private class CityClickListener implements View.OnClickListener {
        String geoname;

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        String countryCode;

        public CityClickListener(String geoname, String countryCode) {
            this.geoname = geoname;
            this.countryCode = countryCode;
        }

        @Override
        public void onClick(View v) {
            GeomeIsSelected geomeIsSelected = (GeomeIsSelected) context;
            Log.i(TAG, "I get city name: " + geoname + "What to do?");
            try {
                geomeIsSelected.getDataToRequest(geoname, countryCode);

            } catch (Exception e) {
                Log.e(TAG, "Error while try click on selected geoname: " + e.getMessage());
            }


        }


    }

}
