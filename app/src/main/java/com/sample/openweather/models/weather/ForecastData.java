package com.sample.openweather.models.weather;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * Created by venugopalraog on 10/13/17.
 */

public class ForecastData implements Parcelable {

    @SerializedName("main")
    private Main main;

    @SerializedName("weather")
    private List<Weather> weatherList;

    @SerializedName("wind")
    private Wind wind;

    @SerializedName("temp")
    private Temp temp;

    @SerializedName("dt")
    private long dt;

    @SerializedName("dt_txt")
    private String dt_txt;


    protected ForecastData(Parcel in) {
        main = in.readParcelable(Main.class.getClassLoader());
        weatherList = in.createTypedArrayList(Weather.CREATOR);
        wind = in.readParcelable(Wind.class.getClassLoader());
        temp = in.readParcelable(Temp.class.getClassLoader());
        dt = in.readLong();
        dt_txt = in.readString();
    }

    public static final Creator<ForecastData> CREATOR = new Creator<ForecastData>() {
        @Override
        public ForecastData createFromParcel(Parcel in) {
            return new ForecastData(in);
        }

        @Override
        public ForecastData[] newArray(int size) {
            return new ForecastData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(main, i);
        parcel.writeTypedList(weatherList);
        parcel.writeParcelable(wind, i);
        parcel.writeParcelable(temp, i);
        parcel.writeLong(dt);
        parcel.writeString(dt_txt);
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ForecastData that = (ForecastData) o;

        return new EqualsBuilder()
                .append(dt, that.dt)
                .append(main, that.main)
                .append(weatherList, that.weatherList)
                .append(wind, that.wind)
                .append(temp, that.temp)
                .append(dt_txt, that.dt_txt)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(main)
                .append(weatherList)
                .append(wind)
                .append(temp)
                .append(dt)
                .append(dt_txt)
                .toHashCode();
    }
}
