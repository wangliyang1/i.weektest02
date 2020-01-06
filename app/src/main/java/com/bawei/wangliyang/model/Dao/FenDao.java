package com.bawei.wangliyang.model.Dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
/*
 * 功能：分类 数据库
 * 作者：王黎杨
 * 时间：2020年1月6日09:40:19
 * */
@Entity
public class FenDao {
    private String FenJson;

    @Generated(hash = 984769861)
    public FenDao(String FenJson) {
        this.FenJson = FenJson;
    }

    @Generated(hash = 42742375)
    public FenDao() {
    }

    public String getFenJson() {
        return this.FenJson;
    }

    public void setFenJson(String FenJson) {
        this.FenJson = FenJson;
    }
}
