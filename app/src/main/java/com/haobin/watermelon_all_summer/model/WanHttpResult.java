package com.haobin.watermelon_all_summer.model;

import cn.droidlover.xdroidmvp.net.IModel;

/**
 * Created by Wenghaobin
 * on 2018/10/16
 * for 玩Android数据基类
 */
public class WanHttpResult<T> implements IModel {

    private T data;
    private int errorCode;
    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }
}
