package com.sample.openweather.models.events;

import com.sample.openweather.models.BaseResponse;

/**
 * Created by venugopalraog on 10/14/17.
 */

public class NetworkResponseEvent {

    private BaseResponse baseResponse;

    public NetworkResponseEvent(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }
}
