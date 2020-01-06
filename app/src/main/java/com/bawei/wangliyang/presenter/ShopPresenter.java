package com.bawei.wangliyang.presenter;

import com.bawei.wangliyang.base.BasePresenter;
import com.bawei.wangliyang.model.bean.FenBean;
import com.bawei.wangliyang.model.bean.ShopBean;
import com.bawei.wangliyang.contract.IContract;
import com.bawei.wangliyang.model.ShopModel;
/*
 * 功能：P层
 * 作者：王黎杨
 * 时间：2020年1月6日09:40:19
 * */
public class ShopPresenter extends BasePresenter<IContract.IView> implements IContract.IPresenter {
    protected ShopModel shopModel;
    @Override
    protected void initModel() {
        shopModel = new ShopModel();
    }

    @Override
    public void getDataFen() {
        //分类数据
        shopModel.getDataFen(new IContract.IModel.IModelCallback() {
            @Override
            public void onFenSuccess(FenBean fenBean) {
                view.onFenSuccess(fenBean);
            }

            @Override
            public void onFenFailure(Throwable throwable) {
                view.onFenFailure(throwable);
            }

            @Override
            public void onShopSuccess(ShopBean shopBean) {

            }

            @Override
            public void onShopFailure(Throwable throwable) {

            }
        });
    }

    @Override
    public void getDataShop(String value) {
        //商品数据
        shopModel.getDataShop(value, new IContract.IModel.IModelCallback() {
            @Override
            public void onFenSuccess(FenBean fenBean) {

            }

            @Override
            public void onFenFailure(Throwable throwable) {

            }

            @Override
            public void onShopSuccess(ShopBean shopBean) {
                view.onShopSuccess(shopBean);
            }

            @Override
            public void onShopFailure(Throwable throwable) {
                view.onShopFailure(throwable);
            }
        });
    }
}
