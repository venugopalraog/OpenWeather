package com.sample.openweather.models.weather;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by venugopalraog on 10/12/17.
 */

public class Sys implements Parcelable {

    @SerializedName("type")
    private String type;

    @SerializedName("id")
    private String id;

    @SerializedName("message")
    private String message;

    @SerializedName("country")
    private String country;

    @SerializedName("sunrise")
    private String sunrise;

    @SerializedName("sunset")
    private String sunset;

    protected Sys(Parcel in) {
        type = in.readString();
        id = in.readString();
        message = in.readString();
        country = in.readString();
        sunrise = in.readString();
        sunset = in.readString();
    }

    public static final Creator<Sys> CREATOR = new Creator<Sys>() {
        @Override
        public Sys createFromParcel(Parcel in) {
            return new Sys(in);
        }

        @Override
        public Sys[] newArray(int size) {
            return new Sys[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(type);
        parcel.writeString(id);
        parcel.writeString(message);
        parcel.writeString(country);
        parcel.writeString(sunrise);
        parcel.writeString(sunset);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Sys sys = (Sys) o;

        return new EqualsBuilder()
                .append(type, sys.type)
                .append(id, sys.id)
                .append(message, sys.message)
                .append(country, sys.country)
                .append(sunrise, sys.sunrise)
                .append(sunset, sys.sunset)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(type)
                .append(id)
                .append(message)
                .append(country)
                .append(sunrise)
                .append(sunset)
                .toHashCode();
    }
}
