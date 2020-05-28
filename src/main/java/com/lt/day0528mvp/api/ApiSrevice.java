package com.lt.day0528mvp.api;

import com.lt.day0528mvp.bean.TestInfo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiSrevice {
    @GET(".")
    Observable<TestInfo> getTestData(@QueryMap Map<String, Object> params, @Query("page_id") int pageId);
}
