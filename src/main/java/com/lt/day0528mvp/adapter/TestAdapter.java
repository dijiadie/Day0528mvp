package com.lt.day0528mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import com.bumptech.glide.Glide;
import com.lt.day0528mvp.R;
import com.lt.day0528mvp.bean.TestInfo;


import java.util.List;

/**
 * Created by 任小龙 on 2020/5/15.
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    private List<TestInfo.DataInfo> datas;
    private Context mContext;

    public TestAdapter(List<TestInfo.DataInfo> pDatas, Context pContext) {
        datas = pDatas;
        mContext = pContext;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.test_adapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        TestInfo.DataInfo dataInfo = datas.get(position);
        holder.title.setText(dataInfo.title);
        Glide.with(mContext).load(dataInfo.thumbnail).into(holder.leftImage);
        holder.desc.setText(!TextUtils.isEmpty(dataInfo.description) ? dataInfo.description : !TextUtils.isEmpty(dataInfo.author) ? dataInfo.author : dataInfo.title);
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView leftImage;
        TextView title;
        TextView desc;

        public ViewHolder( View itemView) {
            super(itemView);
            leftImage = itemView.findViewById(R.id.left_image);
            title = itemView.findViewById(R.id.title_content);
            desc = itemView.findViewById(R.id.desc_content);
        }
    }
}
