package com.pwc.searchview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
        return mData != null ? mData.size() + 1 : 1;
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position - 1);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (position == 0) {
            return getTitleView(convertView, parent);
        }
        if (convertView == null || convertView.getTag() == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_title_layout, parent, false);
            viewHolder.titleIcon = convertView.findViewById(R.id.titleIcon);
            viewHolder.companyName = convertView.findViewById(R.id.title);
            viewHolder.favourite = convertView.findViewById(R.id.favourite);
            viewHolder.fullCompanyName = convertView.findViewById(R.id.fullCompanyName);
            viewHolder.round = convertView.findViewById(R.id.round);
            viewHolder.establishDate = convertView.findViewById(R.id.establishDate);
            viewHolder.location = convertView.findViewById(R.id.location);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(parent.getContext()).load(mData.get(position - 1).logo).into(viewHolder.titleIcon);
        viewHolder.companyName.setText(mData.get(position - 1).name);
        //viewHolder.favourite.setText(mData.get(position - 1).f);
        viewHolder.fullCompanyName.setText(mData.get(position - 1).fullname);
        viewHolder.round.setText(mData.get(position - 1).round);
        viewHolder.establishDate.setText(mData.get(position - 1).establishDate);
        viewHolder.location.setText(mData.get(position - 1).location);
        return convertView;
    }

    private View getTitleView(View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.company_layout, parent, false);
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