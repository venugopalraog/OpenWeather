package com.sample.openweather.models;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;
import com.sample.openweather.models.weather.City;
import com.sample.openweather.models.weather.ForecastData;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * Created by venugopalraog on 10/13/17.
 */

public class WeatherForecastResponse extends BaseResponse {

    @SerializedName("city")
    private City city;

    @SerializedName("list")
    private List<ForecastData> list;

    protected WeatherForecastResponse(Parcel in) {
        super(in);
        city = in.readParcelable(City.class.getClassLoader());
        list = in.createTypedArrayList(ForecastData.CREATOR);
    }

    public static final Creator<WeatherForecastResponse> CREATOR = new Creator<WeatherForecastResponse>() {
        @Override
        public WeatherForecastResponse createFromParcel(Parcel in) {
            return new WeatherForecastResponse(in);
        }

        @Override
        public WeatherForecastResponse[] newArray(int size) {
            return new WeatherForecastResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(city, i);
        parcel.writeTypedList(list);
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<ForecastData> getList() {
        return list;
    }

    public void setList(List<ForecastData> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        WeatherForecastResponse that = (WeatherForecastResponse) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(city, that.city)
                .append(list, that.list)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(city)
                .append(list)
                .toHashCode();
    }
}
