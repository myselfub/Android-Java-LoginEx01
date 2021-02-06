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


public class SignInViewModel extends ViewModel implements View.OnClickListener {
    private MutableLiveData<String> email;
    private MutableLiveData<String> password;
    private MutableLiveData<String> passwordCheck;
    private MutableLiveData<Integer> btn;

    MutableLiveData<ArrayList<UserModel>> mutableLiveData;
    private LoginRepository loginRepository;

    public SignInViewModel() {
        email = new MutableLiveData<>();
        email.setValue("");
        password = new MutableLiveData<>();
        password.setValue("");
        passwordCheck = new MutableLiveData<>();
        passwordCheck.setValue("");
        btn = new MutableLiveData<>();
        mutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<String> getEmail() {
        return email;
    }

    public void setEmail(MutableLiveData<String> email) {
        this.email = email;
    }

    public void setStringEmail(String email) {
        this.email.setValue(email);
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void setPassword(MutableLiveData<String> password) {
        this.password = password;
    }

    public MutableLiveData<String> getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(MutableLiveData<String> passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public MutableLiveData<Integer> getBtn() {
        return btn;
    }

    public MutableLiveData<ArrayList<UserModel>> getMutableLiveData() {
        return mutableLiveData;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_in:
                if (loginRepository == null) {
                    loginRepository = new LoginRepository(mutableLiveData);
                }
                Map<String, String> data = new HashMap<>();
                data.put("username", email.getValue());
                data.put("email", email.getValue());
                data.put("password1", password.getValue());
                data.put("password2", passwordCheck.getValue());
                loginRepository.callSign(data);
                break;
            case R.id.btn_back:
                btn.setValue(view.getId());
                break;
        }
    }
}
