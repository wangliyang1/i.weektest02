package com.bawei.wangliyang.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.wangliyang.R;
import com.bawei.wangliyang.model.bean.ShopBean;
import com.bawei.wangliyang.util.NetUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/*
 * 功能：右侧Adapter
 * 作者：王黎杨
 * 时间：2020年1月6日09:40:19
 * */
public class MyAdapterRight extends RecyclerView.Adapter<MyAdapterRight.MyViewHolder> {

    List<ShopBean.DataBean> data;

    public MyAdapterRight(List<ShopBean.DataBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建布局
        View inflate = View.inflate(parent.getContext(), R.layout.right, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //绑定布局
        ShopBean.DataBean dataBean = data.get(position);
        holder.rightOne.setText(dataBean.getGoods_name());
        holder.rightTwo.setText(dataBean.getCurrency_price());
        NetUtil.getInstance().getPho(dataBean.getGoods_thumb(),holder.rightImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.right_image)
        ImageView rightImage;
        @BindView(R.id.right_one)
        TextView rightOne;
        @BindView(R.id.right_two)
        TextView rightTwo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
