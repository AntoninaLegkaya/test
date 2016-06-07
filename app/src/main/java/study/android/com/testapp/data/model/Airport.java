
package study.android.com.testapp.data.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Airport {

    @SerializedName("iata")
    @Expose
    private String iata;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("coordinates")
    @Expose
    private List<Double> coordinates = new ArrayList<Double>();
    @SerializedName("index_strings")
    @Expose
    private List<String> indexStrings = new ArrayList<String>();
    @SerializedName("airport_name")
    @Expose
    private Object airportName;
    @SerializedName("searches_count")
    @Expose
    int searchesCount;
    public Airport(String iata, String name, String airportName) {
        this.iata = iata;
        this.name = name;
        this.airportName = airportName;
    }




    /**
     * 
     * @return
     *     The iata
     */
    public String getIata() {
        return iata;
    }

    /**
     * 
     * @param iata
     *     The iata
     */
    public void setIata(String iata) {
        this.iata = iata;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The coordinates
     */
    public List<Double> getCoordinates() {
        return coordinates;
    }

    /**
     * 
     * @param coordinates
     *     The coordinates
     */
    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * 
     * @return
     *     The indexStrings
     */
    public List<String> getIndexStrings() {
        return indexStrings;
    }

    /**
     * 
     * @param indexStrings
     *     The index_strings
     */
    public void setIndexStrings(List<String> indexStrings) {
        this.indexStrings = indexStrings;
    }

    /**
     * 
     * @return
     *     The airportName
     */
    public Object getAirportName() {
        return airportName;
    }

    /**
     * 
     * @param airportName
     *     The airport_name
     */
    public void setAirportName(Object airportName) {
        this.airportName = airportName;
    }

    /**
     * 
     * @return
     *     The searchesCount
     */
    public int getSearchesCount() {
        return searchesCount;
    }

    /**
     * 
     * @param searchesCount
     *     The searches_count
     */
    public void setSearchesCount(int searchesCount) {
        this.searchesCount = searchesCount;
    }

}
