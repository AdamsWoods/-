package com.example.mvp_sample_architecture.base;



import com.example.mvp_sample_architecture.base.lib.Model;
import com.example.mvp_sample_architecture.base.lib.Presenter;
import com.example.mvp_sample_architecture.base.lib.View;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<M extends Model, V extends View> implements Presenter<M, V> {

    // 弱引用，容易被回收掉
    private WeakReference<V> wrf;
    protected M model;

    @Override
    public void registeModel(M model) {
        this.model = model;
    }

    @Override
    public void registeView(V view) {
        wrf = new WeakReference<V>(view);
    }

    @Override
    public V getView() {
        return wrf == null ? null : wrf.get();
    }

    @Override
    public void destory() {
        if (wrf != null) {
            wrf.clear();
            wrf = null;
        }
        onViewDestory();
    }

    protected abstract void onViewDestory();
}
