package com.bawei.wangliyang.model.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
/*
 * 功能：商品数据库
 * 作者：王黎杨
 * 时间：2020年1月6日09:40:19
 * */
@Entity
public class ShopDao {
    private String ShopJson;

    @Generated(hash = 984529306)
    public ShopDao(String ShopJson) {
        this.ShopJson = ShopJson;
    }

    @Generated(hash = 300302291)
    public ShopDao() {
    }

    public String getShopJson() {
        return this.ShopJson;
    }

    public void setShopJson(String ShopJson) {
        this.ShopJson = ShopJson;
    }
}
