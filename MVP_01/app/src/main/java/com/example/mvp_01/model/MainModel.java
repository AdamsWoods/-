package com.example.mvp_01.model;

import com.example.mvp_sample_architecture.base.lib.Model;

public interface MainModel extends Model {

    /**
     * 从网络中获取数据
     * @return
     */
    String getDataFromNet();

    /**
     * 停止请求
     */
    void stopRequest();

}
