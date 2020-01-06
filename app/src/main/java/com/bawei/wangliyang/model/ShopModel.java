package com.bawei.wangliyang.model;

import android.util.Log;

import com.bawei.wangliyang.model.bean.FenBean;
import com.bawei.wangliyang.model.bean.ShopBean;
import com.bawei.wangliyang.contract.IContract;
import com.bawei.wangliyang.util.NetUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
/*
 * 功能：M层
 * 作者：王黎杨
 * 时间：2020年1月6日09:40:19
 * */
public class ShopModel implements IContract.IModel {
    @Override
    public void getDataFen(IModelCallback iModelCallback) {
        //调用工具类接口返回分类数据
        NetUtil.getInstance().getApi().fenlei().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FenBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FenBean fenBean) {
                         iModelCallback.onFenSuccess(fenBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iModelCallback.onFenFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getDataShop(String value, IModelCallback iModelCallback) {
        //调用工具类接口返回商品数据
          NetUtil.getInstance().getApi().shop(value).subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Observer<ShopBean>() {
                      @Override
                      public void onSubscribe(Disposable d) {

                      }

                      @Override
                      public void onNext(ShopBean shopBean) {
                        iModelCallback.onShopSuccess(shopBean);
                      }

                      @Override
                      public void onError(Throwable e) {
                          iModelCallback.onShopFailure(e);
                      }

                      @Override
                      public void onComplete() {

                      }
                  });
    }
}
