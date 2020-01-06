package com.bawei.wangliyang.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.wangliyang.R;
import com.bawei.wangliyang.base.BaseActivity;
import com.bawei.wangliyang.contract.IContract;
import com.bawei.wangliyang.database.DaoMaster;
import com.bawei.wangliyang.database.DaoSession;
import com.bawei.wangliyang.database.FenDaoDao;
import com.bawei.wangliyang.database.ShopDaoDao;
import com.bawei.wangliyang.model.Dao.FenDao;
import com.bawei.wangliyang.model.Dao.ShopDao;
import com.bawei.wangliyang.model.bean.FenBean;
import com.bawei.wangliyang.model.bean.ShopBean;
import com.bawei.wangliyang.presenter.ShopPresenter;
import com.bawei.wangliyang.util.NetUtil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/*
 * 功能：activity
 * 作者：王黎杨
 * 时间：2020年1月6日09:40:19
 * */
public class MainActivity extends BaseActivity<ShopPresenter> implements IContract.IView {


    @BindView(R.id.rectclerleft)
    RecyclerView rectclerleft;
    @BindView(R.id.rectclerright)
    RecyclerView rectclerright;
    private FenDaoDao fenDaoDao;
    private ShopDaoDao shopDaoDao;

    @Override
    protected void initData() {
        //判断网络
        if (NetUtil.getInstance().HasNext(this)){
            mPresenter.getDataFen();
        }else {
            //从数据库请求数据
            FenDao unique = fenDaoDao.queryBuilder().unique();
            String fenJson = unique.getFenJson();
            FenBean fenBean = new Gson().fromJson(fenJson, FenBean.class);
            List<String> category = fenBean.getCategory();
            MyAdapterLeft myAdapterLeft = new MyAdapterLeft(category);
            rectclerleft.setLayoutManager(new LinearLayoutManager(this));
            rectclerleft.setAdapter(myAdapterLeft);
            myAdapterLeft.setOnItemClickListener(new MyAdapterLeft.OnItemClickListener() {
                @Override
                public void onItemClick(int i) {
                    List<ShopDao> list = shopDaoDao.queryBuilder().list();
                    ShopDao shopDao = list.get(i);
                    String shopJson = shopDao.getShopJson();
                    ShopBean shopBean = new Gson().fromJson(shopJson, ShopBean.class);
                    List<ShopBean.DataBean> data = shopBean.getData();
                    MyAdapterRight myAdapterRight = new MyAdapterRight(data);
                    rectclerright.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                    rectclerright.setAdapter(myAdapterRight);
                }
            });
        }
    }

    @Override
    protected void initView() {
        //创建数据库对象
        DaoSession daoSession = DaoMaster.newDevSession(this, "app.db");
        fenDaoDao = daoSession.getFenDaoDao();
        shopDaoDao = daoSession.getShopDaoDao();
    }

    @Override
    protected ShopPresenter proviteProsenter() {
        return new ShopPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onFenSuccess(FenBean fenBean) {
        //分类请求成功 设置适配器
        fenDaoDao.deleteAll();
        List<String> category = fenBean.getCategory();
        MyAdapterLeft myAdapterLeft = new MyAdapterLeft(category);
        rectclerleft.setLayoutManager(new LinearLayoutManager(this));
        rectclerleft.setAdapter(myAdapterLeft);
        myAdapterLeft.setOnItemClickListener(new MyAdapterLeft.OnItemClickListener() {
            @Override
            public void onItemClick(int i) {
                Toast.makeText(MainActivity.this, ""+i, Toast.LENGTH_SHORT).show();
                String s = category.get(i);
                EventBus.getDefault().post(s);
            }
        });
        //数据库存入数据
        String s = new Gson().toJson(fenBean);
        FenDao fenDao = new FenDao(s);
        fenDaoDao.insert(fenDao);

        String s1 = category.get(0);
        mPresenter.getDataShop(s1);

    }

    @Override
    public void onFenFailure(Throwable throwable) {
        Toast.makeText(this, "失败fen", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShopSuccess(ShopBean shopBean) {
        //商品请求成功
        List<ShopBean.DataBean> data = shopBean.getData();
        MyAdapterRight myAdapterRight = new MyAdapterRight(data);
        rectclerright.setLayoutManager(new GridLayoutManager(this,2));
        rectclerright.setAdapter(myAdapterRight);
        //商品存入数据
        String s = new Gson().toJson(shopBean);
        ShopDao shopDao = new ShopDao(s);
        shopDaoDao.insert(shopDao);
    }

    @Override
    public void onShopFailure(Throwable throwable) {
        Toast.makeText(this, "失败shop", Toast.LENGTH_SHORT).show();
    }
    //绑定eventbus
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    //解绑eventbus
    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    //接受eventbus
    @Subscribe
    public void onEvent(String value){
        mPresenter.getDataShop(value);
    }
}
