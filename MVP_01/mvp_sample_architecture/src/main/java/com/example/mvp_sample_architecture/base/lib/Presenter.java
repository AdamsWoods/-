package com.example.mvp_sample_architecture.base.lib;

public interface Presenter<M extends Model, V extends View> {

    /**
     * 注册model
     * @param model
     */
    void registeModel(M model);

    /**
     * 注册view
     * @param view
     */
    void registeView(V view);

    /**
     * 获取view
     */
    View getView();

    /**
     * 销毁
     */
    void destory();
}
