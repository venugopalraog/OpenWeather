package com.sample.openweather.models.weather;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by venugopalraog on 10/12/17.
 */

public class Wind implements Parcelable {

    @SerializedName("speed")
    private String speed;

    @SerializedName("deg")
    private String deg;

    protected Wind(Parcel in) {
        speed = in.readString();
        deg = in.readString();
    }

    public static final Creator<Wind> CREATOR = new Creator<Wind>() {
        @Override
        public Wind createFromParcel(Parcel in) {
            return new Wind(in);
        }

        @Override
        public Wind[] newArray(int size) {
            return new Wind[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(speed);
        parcel.writeString(deg);
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Wind wind = (Wind) o;

        return new EqualsBuilder()
                .append(speed, wind.speed)
                .append(deg, wind.deg)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(speed)
                .append(deg)
                .toHashCode();
    }
}
