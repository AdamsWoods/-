package com.example.mvp_01;

import com.example.mvp_sample_architecture.base.BaseMVPActivity;
import com.example.mvp_sample_architecture.base.BasePresenter;
import com.example.mvp_sample_architecture.base.lib.Model;
import com.example.mvp_sample_architecture.base.lib.View;

public abstract class BaseActivity<M extends Model, V extends View, P extends BasePresenter> extends BaseMVPActivity<M, V, P> {

}
