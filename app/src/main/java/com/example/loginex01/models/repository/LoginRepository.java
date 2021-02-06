package com.example.loginex01.models.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.loginex01.models.model.UserModel;
import com.example.loginex01.models.network.RetrofitRestAPI;
import com.example.loginex01.models.network.RetrofitRestAPIService;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class LoginRepository {
    private RetrofitRestAPI retrofitRestAPI;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<ArrayList<UserModel>> mutableLiveData;

    public LoginRepository(MutableLiveData<ArrayList<UserModel>> mutableLiveData) {
        retrofitRestAPI = RetrofitRestAPIService.getInstance().getRetrofitRestAPI(null);
        this.mutableLiveData = mutableLiveData;
    }

    public LoginRepository(MutableLiveData<ArrayList<UserModel>> mutableLiveData, String url) {
        retrofitRestAPI = RetrofitRestAPIService.getInstance().getRetrofitRestAPI(url);
        this.mutableLiveData = mutableLiveData;
    }

    public void callLogin(Map<String, String> data) {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
//            disposable.clear();
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(retrofitRestAPI.login(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> onNext(result.getData()),
                        e -> onError(e), () -> onComplete()));
    }

    public void callSign(Map<String, String> data) {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(retrofitRestAPI.signIn(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> onNext(result.getData()),
                        e -> onError(e), () -> onComplete()));
    }

    public void callGoogleLogin(UserModel data) {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(retrofitRestAPI.googleLogin(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> onNext(result.getData()),
                        e -> onError(e), () -> onComplete()));
    }

    public void callFacebookLogin(UserModel data) {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(retrofitRestAPI.facebookLogin(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> onNext(result.getData()),
                        e -> onError(e), () -> onComplete()));
    }

    public void callNaverLogin(UserModel data) {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(retrofitRestAPI.naverLogin(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> onNext(result.getData()),
                        e -> onError(e), () -> onComplete()));
    }

    private void onNext(ArrayList<UserModel> arrayList) {
        if (arrayList != null) {
            mutableLiveData.setValue(arrayList);
        }
    }

    private void onError(Throwable t) {
        t.printStackTrace();
//        if (t instanceof HttpException) {
//            HttpException httpException = (HttpException) t;
//            String errorMessage = null;
//            try {
//                errorMessage = httpException.response().errorBody().string();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    private void onComplete() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

}