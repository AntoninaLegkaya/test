package study.android.com.testapp.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tony on 28.05.16.
 */
public class WeatherModel implements Parcelable {


    public WeatherModel() {
    }

    public WeatherModel(String geoname, String dateInf, String condition, String tempInf, String geonameLike) {
        this.geoname = geoname;
        this.dateinf = dateInf;
        this.condition = condition;
        this.tempInf = tempInf;
        this.geonameLike=geonameLike;
    }

    public boolean isEmpty(WeatherModel weatherModel) {
        if (weatherModel.getGeoname() == null) {
            return true;
        } else
            return false;
    }

    public String getDateinf() {
        return dateinf;
    }

    public void setDateinf(String dateinf) {
        this.dateinf = dateinf;
    }

    public WeatherModel(String geoname) {
        this.geoname = geoname;
    }


    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getTempInf() {
        return tempInf;
    }

    public void setTempInf(String tempInf) {
        this.tempInf = tempInf;
    }

    public String getGeoname() {
        return geoname;
    }

    public void setGeoname(String geoname) {
        this.geoname = geoname;
    }

    public String getGeonameLike() {
        return geonameLike;
    }

    public void setGeonameLike(String geonameLike) {
        this.geonameLike = geonameLike;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    private WeatherModel(Parcel in) {
        this.geoname = in.readString();
        this.dateinf = in.readString();
        this.condition = in.readString();
        this.tempInf = in.readString();
        this.geonameLike = in.readString();

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        public WeatherModel createFromParcel(Parcel in) {
            return new WeatherModel(in);
        }

        public WeatherModel[] newArray(int size) {
            return new WeatherModel[size];
        }

    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(geoname);
        dest.writeString(condition);
        dest.writeString(dateinf);
        dest.writeString(tempInf);
        dest.writeString(geonameLike);
    }

    private String condition;
    private String tempInf;
    private String geoname;
    private String dateinf;
    private String geonameLike;
}
