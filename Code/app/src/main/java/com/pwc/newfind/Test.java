package com.pwc.newfind;

import android.graphics.drawable.Drawable;
import android.util.Log;

import com.pwc.newfind.bean.FindingTitleBean;
import com.pwc.newfind.entity.FindingTitleEntity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lhuang126 on 1/11/2018.
 */

public class Test {
    public static ArrayList<String> getStrings() {
        ArrayList<String> ss = new ArrayList<>();
        ss.add("hello");
        ss.add("hello1");
        ss.add("hello2");
        return ss;
    }

    private void test() {
        Observable.OnSubscribe<String> onSubscribe = new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.e("HLA", "onSubscribe");
            }
        };
        Observable<String> observable = Observable.create(onSubscribe);
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("HLA", "observer");
            }
        };
        observable.subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())

                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return null;
                    }
                })
                .subscribe(observer);
    }

    @NotNull
    public static FindingTitleEntity getTitleListEntity() {
        FindingTitleEntity list = new FindingTitleEntity();
        ArrayList<FindingTitleBean.FilterBean> titles = new ArrayList<>();
//        titles.add("Test tile A");
//        titles.add("Test tile B");
//        titles.add("Test tile C");
//        titles.add("Test tile D");
//        titles.add("Test tile E");
//        titles.add("Test tile F");
//        titles.add("Test tile G");
//        titles.add("Test tile H");
//        titles.add("Test tile I");
//        titles.add("Test tile J");
//        titles.add("Test tile K");
//        list.setTileList(titles);
        return list;
    }

    public static ArrayList<String> getCompanyList() {
        ArrayList<String> titles = new ArrayList<>();
        titles.add("Test Company A");
        titles.add("Test Company B");
        titles.add("Test Company C");
        titles.add("Test Company D");
        titles.add("Test Company E");
        titles.add("Test Company F");
        titles.add("Test Company G");
        titles.add("Test Company H");
        titles.add("Test Company I");
        titles.add("Test Company J");
        titles.add("Test Company K");

        return titles;
    }
}
