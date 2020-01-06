package com.bawei.wangliyang.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bawei.wangliyang.R;

import butterknife.ButterKnife;
/*
* 功能：activity基类
* 作者：王黎杨
* 时间：2020年1月6日09:40:19
* */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    protected P mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        //绑定butterknife
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        mPresenter = proviteProsenter();
        if (mPresenter!=null){
            mPresenter.actach(this);
        }
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P proviteProsenter();

    protected abstract int layoutId();
    //防止内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detach();
        }
    }
}
