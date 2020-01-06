package com.bawei.wangliyang.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.wangliyang.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/*
 * 功能：左侧adapter
 * 作者：王黎杨
 * 时间：2020年1月6日09:40:19
 * */
public class MyAdapterLeft extends RecyclerView.Adapter<MyAdapterLeft.MyViewHolder> {
    List<String> category;

    public MyAdapterLeft(List<String> category) {
        this.category = category;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建布局
        View inflate = View.inflate(parent.getContext(), R.layout.left, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //绑定布局
        String s = category.get(position);
        holder.leftTv.setText(s);
        //接口回调返回数据
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.left_tv)
        TextView leftTv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int i);
    }
}
