package com.app.kotlinbasews.api;


import com.app.kotlinbasews.api.responsepojos.BaseResponse;
import com.app.kotlinbasews.api.responsepojos.User;
import com.app.kotlinbasews.helper.AppConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by chintans on 21-02-2018.
 */

public interface AppApi {

    @POST(AppConstants.LOGIN_URL)
    Call<BaseResponse<List<User>>> doLogin(@Body LoginReq loginReq);


}
