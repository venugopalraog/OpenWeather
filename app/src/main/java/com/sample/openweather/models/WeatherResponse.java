package com.sample.openweather.models;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;
import com.sample.openweather.models.weather.Coord;
import com.sample.openweather.models.weather.Main;
import com.sample.openweather.models.weather.Weather;
import com.sample.openweather.models.weather.Wind;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * Created by venugopalraog on 10/12/17.
 */

public class WeatherResponse extends BaseResponse {

    @SerializedName("coord")
    private Coord coord;

    @SerializedName("weather")
    private List<Weather> weatherList;

    @SerializedName("main")
    private Main main;

    @SerializedName("visibility")
    private String visibility;

    @SerializedName("wind")
    private Wind wind;

    @SerializedName("dt")
    private int dt;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("cod")
    private String cod;


    public WeatherResponse(String screenHeader) {
        super(screenHeader);
    }

    protected WeatherResponse(Parcel in) {
        super(in);
        coord = in.readParcelable(Coord.class.getClassLoader());
        weatherList = in.createTypedArrayList(Weather.CREATOR);
        main = in.readParcelable(Main.class.getClassLoader());
        visibility = in.readString();
        wind = in.readParcelable(Wind.class.getClassLoader());
        dt = in.readInt();
        id = in.readInt();
        name = in.readString();
        cod = in.readString();
    }

    public static final Creator<WeatherResponse> CREATOR = new Creator<WeatherResponse>() {
        @Override
        public WeatherResponse createFromParcel(Parcel in) {
            return new WeatherResponse(in);
        }

        @Override
        public WeatherResponse[] newArray(int size) {
            return new WeatherResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(coord, i);
        parcel.writeTypedList(weatherList);
        parcel.writeParcelable(main, i);
        parcel.writeString(visibility);
        parcel.writeParcelable(wind, i);
        parcel.writeInt(dt);
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(cod);
    }


    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        WeatherResponse that = (WeatherResponse) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(dt, that.dt)
                .append(id, that.id)
                .append(coord, that.coord)
                .append(weatherList, that.weatherList)
                .append(main, that.main)
                .append(visibility, that.visibility)
                .append(wind, that.wind)
                .append(name, that.name)
                .append(cod, that.cod)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(coord)
                .append(weatherList)
                .append(main)
                .append(visibility)
                .append(wind)
                .append(dt)
                .append(id)
                .append(name)
                .append(cod)
                .toHashCode();
    }
}
