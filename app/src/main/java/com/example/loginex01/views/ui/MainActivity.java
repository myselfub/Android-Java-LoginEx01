package com.example.loginex01.views.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.loginex01.R;
import com.example.loginex01.models.repository.LocalRepository;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("ActionBar");
        LocalRepository localRepository = new LocalRepository(this);
        Map<String, String> map = localRepository.getLoginInfo();
        Log.d("aaaa", map.toString());
    }
}