package com.sample.openweather.models.weather;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by venugopalraog on 10/15/17.
 */

public class Temp implements Parcelable{

    @SerializedName("day")
    private String day;

    @SerializedName("min")
    private float min;

    @SerializedName("max")
    private float max;

    @SerializedName("night")
    private String night;

    @SerializedName("eve")
    private String eve;

    @SerializedName("morn")
    private String morn;


    protected Temp(Parcel in) {
        day = in.readString();
        min = in.readFloat();
        max = in.readFloat();
        night = in.readString();
        eve = in.readString();
        morn = in.readString();
    }

    public static final Creator<Temp> CREATOR = new Creator<Temp>() {
        @Override
        public Temp createFromParcel(Parcel in) {
            return new Temp(in);
        }

        @Override
        public Temp[] newArray(int size) {
            return new Temp[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(day);
        parcel.writeFloat(min);
        parcel.writeFloat(max);
        parcel.writeString(night);
        parcel.writeString(eve);
        parcel.writeString(morn);
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getEve() {
        return eve;
    }

    public void setEve(String eve) {
        this.eve = eve;
    }

    public String getMorn() {
        return morn;
    }

    public void setMorn(String morn) {
        this.morn = morn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Temp temp = (Temp) o;

        return new EqualsBuilder()
                .append(day, temp.day)
                .append(min, temp.min)
                .append(max, temp.max)
                .append(night, temp.night)
                .append(eve, temp.eve)
                .append(morn, temp.morn)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(day)
                .append(min)
                .append(max)
                .append(night)
                .append(eve)
                .append(morn)
                .toHashCode();
    }
}
