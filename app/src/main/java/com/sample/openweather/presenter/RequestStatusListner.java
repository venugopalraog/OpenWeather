package com.sample.openweather.presenter;

import com.sample.openweather.models.BaseResponse;

/**
 * Created by venugopalraog on 10/13/17.
 */

public interface RequestStatusListner {

    void onRequestSuccess(BaseResponse baseResponse);

    void onRequestFailed(String message);

}
