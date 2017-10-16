package com.sample.openweather.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by venugopalraog on 10/12/17.
 */

public class BaseResponse implements Parcelable {

    private String screenHeader;

    public BaseResponse(String screenHeader) {
        this.screenHeader = screenHeader;
    }

    protected BaseResponse(Parcel in) {
        screenHeader = in.readString();
    }

    public static final Creator<BaseResponse> CREATOR = new Creator<BaseResponse>() {
        @Override
        public BaseResponse createFromParcel(Parcel in) {
            return new BaseResponse(in);
        }

        @Override
        public BaseResponse[] newArray(int size) {
            return new BaseResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(screenHeader);
    }

    public String getScreenHeader() {
        return screenHeader;
    }

    public void setScreenHeader(String screenHeader) {
        this.screenHeader = screenHeader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BaseResponse that = (BaseResponse) o;

        return new EqualsBuilder()
                .append(screenHeader, that.screenHeader)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(screenHeader)
                .toHashCode();
    }
}
