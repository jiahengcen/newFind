package com.pwc.newfind.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.pwc.newfind.net.StringConverterFactory;
import com.pwc.newfind.db.DaoMaster;
import com.pwc.newfind.db.DaoSession;
import com.pwc.newfind.db.UserDao;
import com.pwc.newfind.db.entity.User;
import com.pwc.newfind.net.Constant;
import com.pwc.newfind.net.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by lhuang126 on 1/13/2018.
 */

public class Application extends android.app.Application {
    public static Context appContext;
    private static Application instances;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private String mUserToken;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        instances = this;
        setDatabase();
        getUserTokenNet();
    }

    public void getTempUserToken() {
        getUserTokenNet();
    }

    private void getUserTokenNet() {
        if (mUserToken == null) {
            List<User> user = getDaoSession().getUserDao().queryBuilder().where(UserDao.Properties.Id.eq(1)).list();
            if (!user.isEmpty()) {
                mUserToken = user.get(0).getToken();
                Log.e("HLA", "get token from db:" + mUserToken);
            }
        }
        if (mUserToken == null) {
            new Retrofit.Builder()
                    .addConverterFactory(new StringConverterFactory())
                    .baseUrl(Constant.host)
                    .build().create(RetrofitService.class)
                    .getUserToken()
                    .enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            mUserToken = response.body();
                            User user = new User();
                            user.setId(1L);
                            user.setAge(0);
                            user.setToken(mUserToken);
                            getDaoSession().getUserDao().insertOrReplace(user);
                            Log.e("HLA", "get token from net:" + mUserToken);
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.e("HLA", "onFailure" + call);
                        }
                    });
        }
    }

    public static Application getInstances() {
        return instances;
    }

    /**
     * 设置greenDao
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setUserToken(String mUserToken) {
        this.mUserToken = mUserToken;
        User user = new User();
        user.setId(1L);
        user.setAge(0);
        user.setToken(mUserToken);
        getDaoSession().getUserDao().insertOrReplace(user);
    }

    public String getUserToken() {
        return mUserToken;
    }

}
