package com.bawei.wangliyang.contract;

import com.bawei.wangliyang.model.bean.FenBean;
import com.bawei.wangliyang.model.bean.ShopBean;
/*
 * 功能：契约类
 * 作者：王黎杨
 * 时间：2020年1月6日09:40:19
 * */
public interface IContract {
    interface IView{
        void onFenSuccess(FenBean fenBean);
        void onFenFailure(Throwable throwable);

        void onShopSuccess(ShopBean shopBean);
        void onShopFailure(Throwable throwable);
    }
    interface IPresenter{
        void getDataFen();
        void getDataShop(String value);
    }
    interface IModel{
        void getDataFen(IModelCallback iModelCallback);
        void getDataShop(String value,IModelCallback iModelCallback);
        interface IModelCallback{
            void onFenSuccess(FenBean fenBean);
            void onFenFailure(Throwable throwable);

            void onShopSuccess(ShopBean shopBean);
            void onShopFailure(Throwable throwable);
        }
    }
}
