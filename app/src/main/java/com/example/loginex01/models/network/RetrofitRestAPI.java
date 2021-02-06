package com.example.loginex01.models.network;

import com.example.loginex01.models.model.JsonResultModel;
import com.example.loginex01.models.model.UserModel;
import com.example.loginex01.models.model.VentilationTimeModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitRestAPI {
    @GET("/air/ventilationtime")
    Observable<JsonResultModel<VentilationTimeModel>> ventilationtime();

    @FormUrlEncoded
    @POST("/logins/login/")
    Observable<JsonResultModel<UserModel>> login(@FieldMap Map<String, String> data);

    @FormUrlEncoded
    @POST("/logins/signin/")
    Observable<JsonResultModel<UserModel>> signIn(@FieldMap Map<String, String> data);

    @POST("/logins/google/")
    Observable<JsonResultModel<UserModel>> googleLogin(@Body UserModel data);

    @POST("/logins/facebook/")
    Observable<JsonResultModel<UserModel>> facebookLogin(@Body UserModel data);

    @POST("/logins/naver/")
    Observable<JsonResultModel<UserModel>> naverLogin(@Body UserModel data);
}
