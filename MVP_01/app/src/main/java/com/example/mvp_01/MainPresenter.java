package com.example.mvp_01;

import com.example.mvp_01.model.MainModel;
import com.example.mvp_sample_architecture.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainModel, MainView> {

    public void getData() {
        String dataFromNet = null;
        if (model != null) {
            dataFromNet = model.getDataFromNet();
        }
        if (getView() != null) {
            getView().setData(dataFromNet);
        }
    }

    //  销毁activity时，停止model的操作
    @Override
    protected void onViewDestory() {
        if (model != null) {
            model.stopRequest();
        }
    }
}
