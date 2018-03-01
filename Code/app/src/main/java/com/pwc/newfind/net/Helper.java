package com.pwc.newfind.net;

import android.content.Context;
import android.util.Log;

import com.pwc.newfind.base.Application;
import com.pwc.newfind.base.UserHelper;
import com.pwc.newfind.bean.ActionStartCompanyBean;
import com.pwc.newfind.bean.ActionStartIndustryBean;
import com.pwc.newfind.bean.ActionStartReportBean;
import com.pwc.newfind.bean.PostResult;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by lhuang126 on 1/23/2018.
 */

public class Helper {
    public static final String ACTION_TYPE_INSERT="insert";
    public static final String ACTION_TYPE_DELETE="delete";
    public static final String ACTION_TYPE_OVERRIDE="override";
    public static void actionStartReport(Context context,String fullName,String actionType){
        ArrayList<String> fullNames = new ArrayList<>();
        fullNames.add(fullName);
        actionStartReport(context, fullNames, actionType);
    }
    private static void actionStartReport(Context context, ArrayList<String> fullNames, String actionType) {
        ActionStartReportBean actionStartReportBean = new ActionStartReportBean();
        actionStartReportBean.setActionType(actionType);
        actionStartReportBean.setId(fullNames);
        try {
            RetrofitHelper.Companion.getInstance(context).getServer().actionStarResearch(UserHelper.getUserToken(), actionStartReportBean)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<PostResult>() {
                        @Override
                        public void call(PostResult postResult) {

                        }
                    });
        } catch (Exception e) {
            Log.e("HLA", "actionStarCompany error, e:" + e.getMessage());
        }
    }
    public static void actionStarCompany(Context context, String fullName, String actionType) {
        ArrayList<String> fullNames = new ArrayList<>();
        fullNames.add(fullName);
        actionStarCompany(context, fullNames, actionType);
    }

    private static void actionStarCompany(Context context, ArrayList<String> fullNames, String actionType) {
        ActionStartCompanyBean actionStartCompanyBean = new ActionStartCompanyBean();
        actionStartCompanyBean.setActionType(actionType);
        actionStartCompanyBean.setFullName(fullNames);
        try {
            RetrofitHelper.Companion.getInstance(context).getServer().actionStarCompany(UserHelper.getUserToken(), actionStartCompanyBean)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<PostResult>() {
                        @Override
                        public void call(PostResult postResult) {

                        }
                    });
        } catch (Exception e) {
            Log.e("HLA", "actionStarCompany error, e:" + e.getMessage());
        }

    }

    public static void actionStarIndustry(Context context, String industry, String actionType) {
        ArrayList<String> industries = new ArrayList<>();
        industries.add(industry);
        actionStarCompany(context, industries, actionType);
    }

    public static void actionStarIndustry(Context context, ArrayList<String> industries, String actionType) {
        ActionStartIndustryBean actionStartIndustryBean = new ActionStartIndustryBean();
        actionStartIndustryBean.setActionType(actionType);
        actionStartIndustryBean.setIndustry(industries);
        try {
            RetrofitHelper.Companion.getInstance(context).getServer()
                    .actionStarIndustry(UserHelper.getUserToken(), actionStartIndustryBean)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<PostResult>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(PostResult postResult) {
                            Log.e("HLA", "actionStarIndustry," + postResult.getMsg());
                        }

                    });
        } catch (Exception e) {
            Log.e("HLA", "actionStarCompany error, e:" + e.getMessage());
        }

    }
}
