package com.sample.openweather.models.weather;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by venugopalraog on 10/12/17.
 */

public class Main implements Parcelable {

    @SerializedName("temp")
    private float temp;

    @SerializedName("pressure")
    private String pressure;

    @SerializedName("humidity")
    private String humidity;

    @SerializedName("temp_min")
    private float temp_min;

    @SerializedName("temp_max")
    private float temp_max;

    protected Main(Parcel in) {
        temp = in.readFloat();
        pressure = in.readString();
        humidity = in.readString();
        temp_min = in.readFloat();
        temp_max = in.readFloat();
    }

    public static final Creator<Main> CREATOR = new Creator<Main>() {
        @Override
        public Main createFromParcel(Parcel in) {
            return new Main(in);
        }

        @Override
        public Main[] newArray(int size) {
            return new Main[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(temp);
        parcel.writeString(pressure);
        parcel.writeString(humidity);
        parcel.writeFloat(temp_min);
        parcel.writeFloat(temp_max);
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Main main = (Main) o;

        return new EqualsBuilder()
                .append(temp, main.temp)
                .append(pressure, main.pressure)
                .append(humidity, main.humidity)
                .append(temp_min, main.temp_min)
                .append(temp_max, main.temp_max)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(temp)
                .append(pressure)
                .append(humidity)
                .append(temp_min)
                .append(temp_max)
                .toHashCode();
    }
}
