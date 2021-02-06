package com.example.loginex01.util;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.loginex01.models.model.UserModel;
import com.example.loginex01.models.repository.LoginRepository;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

import org.json.JSONObject;

import java.util.ArrayList;


public class FacebookLogin implements FacebookCallback<LoginResult> {
    private final String TAG = "FacebookLogin";
    private MutableLiveData<ArrayList<UserModel>> mutableLiveData;
    private LoginRepository loginRepository;

    private FacebookLogin() {
    }

    public FacebookLogin(MutableLiveData<ArrayList<UserModel>> mutableLiveData) {
        this.mutableLiveData = mutableLiveData;
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        loginRepository = new LoginRepository(this.mutableLiveData);
        AccessToken accessToken = loginResult.getAccessToken();
        UserModel userModel = new UserModel();
        userModel.setAccess_token(accessToken.getToken());
        loginRepository.callFacebookLogin(userModel);
        Log.d(TAG + " token", accessToken.getToken());
        if (accessToken != null && !accessToken.isExpired()) {
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    Log.d(TAG + " Info", object.toString());
                }
            });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email");
            graphRequest.setParameters(parameters);
            graphRequest.executeAsync();
        }
    }

    @Override
    public void onCancel() {
    }

    @Override
    public void onError(FacebookException error) {
    }
}
