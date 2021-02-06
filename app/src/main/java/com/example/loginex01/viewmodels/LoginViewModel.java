package com.example.loginex01.viewmodels;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginex01.R;
import com.example.loginex01.models.model.UserModel;
import com.example.loginex01.models.repository.LoginRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class LoginViewModel extends ViewModel implements View.OnClickListener {
    private MutableLiveData<String> email;
    private MutableLiveData<String> password;
    private MutableLiveData<Integer> btn;

    MutableLiveData<ArrayList<UserModel>> mutableLiveData;
    private LoginRepository loginRepository;

    public LoginViewModel() {
        email = new MutableLiveData<>();
        email.setValue("");
        password = new MutableLiveData<>();
        password.setValue("");
        btn = new MutableLiveData<>();
    }

    public MutableLiveData<String> getEmail() {
        return email;
    }

    public void setEmail(MutableLiveData<String> email) {
        this.email = email;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void setPassword(MutableLiveData<String> password) {
        this.password = password;
    }

    public MutableLiveData<Integer> getBtn() {
        return btn;
    }

    public void setMutableLiveData(MutableLiveData<ArrayList<UserModel>> mutableLiveData) {
        this.mutableLiveData = mutableLiveData;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sign_in:
            case R.id.btn_google_login:
            case R.id.btn_facebook_login:
            case R.id.btn_naver_login:
                btn.setValue(view.getId());
                break;
            case R.id.btn_login:
                if (loginRepository == null) {
                    loginRepository = new LoginRepository(mutableLiveData);
                }
                Map<String, String> data = new HashMap<>();
                data.put("email", email.getValue());
                data.put("password", password.getValue());
                loginRepository.callLogin(data);
                break;
//            case R.id.sign_out_button:
//                signOut();
////                revokeAccess();
//                break;
        }
    }
}
