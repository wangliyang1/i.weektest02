package com.bawei.wangliyang.base;
/*
 * 功能：P层基类
 * 作者：王黎杨
 * 时间：2020年1月6日09:40:19
 * */
public abstract class BasePresenter <V> {
    protected V view;

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void actach(V view) {
        this.view = view;
    }

    public void detach(){
        view = null;
    }
}
