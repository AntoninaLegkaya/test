
package study.android.com.testapp.data.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Geonames {

    @SerializedName("geonames")
    @Expose
    private List<Geoname> geonames = new ArrayList<Geoname>();

    /**
     * 
     * @return
     *     The geonames
     */
    public List<Geoname> getGeonames() {
        return geonames;
    }

    /**
     * 
     * @param geonames
     *     The geonames
     */
    public void setGeonames(List<Geoname> geonames) {
        this.geonames = geonames;
    }

}
