package com.bawei.wangliyang;

import com.bawei.wangliyang.model.bean.FenBean;
import com.bawei.wangliyang.model.bean.ShopBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
/*
 * 功能：Api
 * 作者：王黎杨
 * 时间：2020年1月6日09:40:19
 * */
public interface Api {
    @GET("baweiapi/category")
    Observable<FenBean> fenlei();

    @GET("baweiapi/shopByCategory")
    Observable<ShopBean> shop(@Query("category") String category);
}
