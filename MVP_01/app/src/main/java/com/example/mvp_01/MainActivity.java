package com.example.mvp_01;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.mvp_01.model.MainModel;
import com.example.mvp_01.model.MainModelImpl;

public class MainActivity extends BaseActivity<MainModel, MainView, MainPresenter> implements MainView {

    private TextView tvFrist;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvFrist = findViewById(R.id.frist_tv);
        tvFrist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        init();
    }

    public void init() {
        if (presenter != null){
            presenter.getData();
        }
    }

    @Override
    public MainModel createModel() {
        return new MainModelImpl();
    }

    @Override
    public MainView createView() {
        return this;
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    public void setData(String str) {
        tvFrist.setText(str);
    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showProgress() {

    }
}
