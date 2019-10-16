package com.example.mvp_sample_architecture.base;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.example.mvp_sample_architecture.base.BaseMVP;
import com.example.mvp_sample_architecture.base.BasePresenter;
import com.example.mvp_sample_architecture.base.lib.Model;
import com.example.mvp_sample_architecture.base.lib.View;

public abstract class BaseMVPFragment<M extends Model, V extends View, P extends BasePresenter> extends Fragment implements BaseMVP<M, V, P> {

    protected P presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter = createPresenter();
        if (presenter != null) {
            presenter.registeModel(createModel());
            presenter.registeView(createView());
        }
    }
}
