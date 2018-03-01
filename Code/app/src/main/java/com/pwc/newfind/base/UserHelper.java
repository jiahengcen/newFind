package com.pwc.newfind.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.pwc.newfind.db.UserDao;
import com.pwc.newfind.db.entity.User;
import com.pwc.newfind.net.Constant;
import com.pwc.newfind.net.RetrofitService;
import com.pwc.newfind.net.StringConverterFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by lhuang126 on 2/28/2018.
 */

public class UserHelper {
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String DEFAULT_USER_TOKEN = "0";
    private static String mUserToken = DEFAULT_USER_TOKEN;

    public static String getUserPhone() {
        SharedPreferences sharedPreferences = Application.getInstances().getSharedPreferences("User", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PHONE, "");
    }

    public static void setUserPhone(String phone) {
        SharedPreferences sharedPreferences = Application.getInstances().getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PHONE, phone);
        editor.apply();
    }

    public static String getUserEmail() {
        SharedPreferences sharedPreferences = Application.getInstances().getSharedPreferences("User", Context.MODE_PRIVATE);
        return sharedPreferences.getString(EMAIL, "");
    }

    public static void setUserEmail(String email) {
        SharedPreferences sharedPreferences = Application.getInstances().getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public static void logout() {
        setUserToken(DEFAULT_USER_TOKEN);
        setUserPhone("");
        setUserEmail("");
        getTempUserToken(null);
    }

    public static void setUserToken(String userToken) {
        mUserToken = userToken;
        User user = new User();
        user.setId(1L);
        user.setAge(0);
        user.setToken(mUserToken);
        Application.getInstances().getDaoSession().getUserDao().insertOrReplace(user);
        if (userToken.equals(DEFAULT_USER_TOKEN)) {
            getTempUserToken(null);
        }
    }

    public static String getUserToken() {
        return mUserToken;
    }

    public static void getTempUserToken(OnTempTokenGetFinish onTempTokenGetFinish) {
        getUserTokenNetOrLocal(onTempTokenGetFinish);
    }


    public static void getUserTokenNetOrLocal(final OnTempTokenGetFinish onTempTokenGetFinish) {
        if (mUserToken == DEFAULT_USER_TOKEN) {
            List<User> user = Application.getInstances().getDaoSession().getUserDao().queryBuilder().where(UserDao.Properties.Id.eq(1)).list();
            if (!user.isEmpty()) {
                mUserToken = user.get(0).getToken();
                Log.e("HLA", "get token from db:" + mUserToken);
            }
        }
        if (mUserToken == mUserToken) {
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
                            Application.getInstances().getDaoSession().getUserDao().insertOrReplace(user);
                            if (onTempTokenGetFinish != null) {
                                onTempTokenGetFinish.onCompleted();
                            }
                            Log.e("HLA", "get token from net:" + mUserToken);
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.e("HLA", "onFailure" + call);
                            Log.e("HLA", "onFailure" + t.getMessage());
                            if (onTempTokenGetFinish != null) {
                                onTempTokenGetFinish.onFailure();
                            }
                        }
                    });
        }
    }

    public interface OnTempTokenGetFinish {
        void onCompleted();

        void onFailure();
    }
}
