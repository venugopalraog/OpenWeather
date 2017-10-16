package com.sample.openweather.models.weather;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by venugopalraog on 10/12/17.
 */

public class Coord implements Parcelable {

    @SerializedName("lon")
    private String lon;

    @SerializedName("lat")
    private String lat;

    protected Coord(Parcel in) {
        lon = in.readString();
        lat = in.readString();
    }

    public static final Creator<Coord> CREATOR = new Creator<Coord>() {
        @Override
        public Coord createFromParcel(Parcel in) {
            return new Coord(in);
        }

        @Override
        public Coord[] newArray(int size) {
            return new Coord[size];
        }
    };

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(lon);
        parcel.writeString(lat);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Coord coord = (Coord) o;

        return new EqualsBuilder()
                .append(lon, coord.lon)
                .append(lat, coord.lat)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(lon)
                .append(lat)
                .toHashCode();
    }
}
