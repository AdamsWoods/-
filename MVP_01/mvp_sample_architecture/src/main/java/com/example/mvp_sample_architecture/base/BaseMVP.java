package com.example.mvp_sample_architecture.base;

import com.example.mvp_sample_architecture.base.lib.Model;
import com.example.mvp_sample_architecture.base.lib.View;

public interface BaseMVP<M extends Model, V extends View, P extends BasePresenter> {

    M createModel();

    V createView();

    P createPresenter();

}
