package com.pwc.newfind.finding;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pwc.newfind.detail.CompanyDetailActivity;
import com.pwc.searchview.bean.CompanyTitleListBean;

import java.util.ArrayList;


/**
 * Created by lhuang126 on 1/13/2018.
 */

public class CompanyListAdapter extends BaseAdapter {
    private ArrayList<CompanyTitleListBean.CompanyTitleSubBean> mData;

    public void setData(ArrayList<CompanyTitleListBean.CompanyTitleSubBean> data) {
        mData = data;
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
//        if (position == 0) {
//            return getTitleView(convertView, parent);
//        }
        if (convertView == null || convertView.getTag() == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(com.pwc.searchview.R.layout.company_title_layout, parent, false);
            viewHolder.titleIcon = convertView.findViewById(com.pwc.searchview.R.id.titleIcon);
            viewHolder.companyName = convertView.findViewById(com.pwc.searchview.R.id.title);
            viewHolder.favourite = convertView.findViewById(com.pwc.searchview.R.id.favourite);
            viewHolder.fullCompanyName = convertView.findViewById(com.pwc.searchview.R.id.fullCompanyName);
            viewHolder.round = convertView.findViewById(com.pwc.searchview.R.id.round);
            viewHolder.establishDate = convertView.findViewById(com.pwc.searchview.R.id.establishDate);
            viewHolder.location = convertView.findViewById(com.pwc.searchview.R.id.location);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(parent.getContext()).load(mData.get(position).logo).into(viewHolder.titleIcon);
        viewHolder.companyName.setText(mData.get(position).name);
        setFavourite(viewHolder.favourite, mData.get(position).starred);
        viewHolder.fullCompanyName.setText(mData.get(position).fullName);
        viewHolder.fullCompanyName.setTag(mData.get(position).fullName);
        viewHolder.round.setText(mData.get(position).round);
        viewHolder.establishDate.setText(mData.get(position).establishDate);
        viewHolder.location.setText(mData.get(position).location);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CompanyDetailActivity.class);
                String fullName = ((TextView) v.findViewById(com.pwc.searchview.R.id.fullCompanyName)).getTag().toString();
                intent.putExtra("companyCode", fullName);
                v.getContext().startActivity(intent);
            }
        });
        return convertView;
    }

    private void setFavourite(TextView favourite, Boolean starred) {
        if (starred) {
            favourite.setText("已关注");
            favourite.setTextColor(favourite.getContext().getResources().getColor(com.pwc.searchview.R.color.colorGray));
        } else {
            favourite.setText("未关注");
            favourite.setTextColor(favourite.getContext().getResources().getColor(com.pwc.searchview.R.color.colorGray));
            //favourite.setTextColor(favourite.getContext().getResources().getColor(R.color.colorRed));
        }
    }

    private View getTitleView(View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(com.pwc.searchview.R.layout.company_layout, parent, false);
        return convertView;
    }

    class ViewHolder {
        public ImageView titleIcon;
        public TextView companyName;
        public TextView favourite;
        public TextView fullCompanyName;
        public TextView round;
        public TextView establishDate;
        public TextView location;
    }
}
