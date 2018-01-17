package com.pwc.newfind;

import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;

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
    public static ArrayList<String> getStrings(){
        ArrayList<String> ss=new ArrayList<>();
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

}
