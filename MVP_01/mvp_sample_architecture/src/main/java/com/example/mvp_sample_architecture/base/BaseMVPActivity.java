package com.example.mvp_sample_architecture.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mvp_sample_architecture.R;
import com.example.mvp_sample_architecture.base.BaseMVP;
import com.example.mvp_sample_architecture.base.BasePresenter;
import com.example.mvp_sample_architecture.base.lib.Model;
import com.example.mvp_sample_architecture.base.lib.View;


public abstract class BaseMVPActivity<M extends Model, V extends View, P extends BasePresenter> extends AppCompatActivity implements BaseMVP<M, V, P> {

    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = createPresenter();
        if (presenter != null) {
            //将Model层注册到Presenter中
            presenter.registeModel(createModel());
            //将View层注册到Presenter中
            presenter.registeView(createView());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            //Activity销毁时的调用，让具体实现BasePresenter中onViewDestroy()方法做出决定
            presenter.destory();
        }
    }
}
