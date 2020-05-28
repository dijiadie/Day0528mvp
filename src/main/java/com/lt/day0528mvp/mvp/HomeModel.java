package com.lt.day0528mvp.mvp;

import com.lt.day0528mvp.api.ApiSrevice;
import com.lt.day0528mvp.bean.TestInfo;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeModel implements IHomeModel {
    @Override
    public void getData(final IHomePresenter pPresenter, final int whichApi, Object[] params) {
        final int loadType = (int) params[0];
        Map param = (Map) params[1];
        int pageId = (int) params[2];
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://static.owspace.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build();

        Observable<TestInfo> data = retrofit.create(ApiSrevice.class).getTestData(param, pageId);
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TestInfo>() {
                    @Override
                    public void accept(TestInfo pTestInfo) throws Exception {
                        pPresenter.onSuccess(whichApi, loadType, pTestInfo);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable pThrowable) throws Exception {
                        pPresenter.onFailed(whichApi, pThrowable);
                    }
                });
    }
}
