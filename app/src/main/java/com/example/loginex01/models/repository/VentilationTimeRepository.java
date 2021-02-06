package com.example.loginex01.models.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.loginex01.models.model.VentilationTimeModel;
import com.example.loginex01.models.network.RetrofitRestAPI;
import com.example.loginex01.models.network.RetrofitRestAPIService;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class VentilationTimeRepository {
    private RetrofitRestAPI retrofitRestAPI;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<ArrayList<VentilationTimeModel>> mutableLiveData;

    public VentilationTimeRepository(MutableLiveData<ArrayList<VentilationTimeModel>> mutableLiveData) {
        retrofitRestAPI = RetrofitRestAPIService.getInstance().getRetrofitRestAPI(null);
        this.mutableLiveData = mutableLiveData;
    }

    public VentilationTimeRepository(MutableLiveData<ArrayList<VentilationTimeModel>> mutableLiveData, String url) {
        retrofitRestAPI = RetrofitRestAPIService.getInstance().getRetrofitRestAPI(url);
        this.mutableLiveData = mutableLiveData;
    }

    public void callVentilationTime() {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
//            disposable.clear();
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(retrofitRestAPI.ventilationtime()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> onNext(result.getData()),
                        e -> onError(e), () -> onComplete()));
    }

    private void onNext(ArrayList<VentilationTimeModel> arrayList) {
        if (arrayList != null) {
            mutableLiveData.setValue(arrayList);
        }
    }

    private void onError(Throwable t) {
        t.printStackTrace();
    }

    private void onComplete() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

}