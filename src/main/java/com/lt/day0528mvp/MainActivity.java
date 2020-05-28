package com.lt.day0528mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.lt.day0528mvp.adapter.TestAdapter;
import com.lt.day0528mvp.bean.TestInfo;
import com.lt.day0528mvp.mvp.ApiConfig;
import com.lt.day0528mvp.mvp.HomeModel;
import com.lt.day0528mvp.mvp.HomePresenter;
import com.lt.day0528mvp.mvp.IHomeView;
import com.lt.day0528mvp.mvp.LoadTypeConfig;
import com.lt.day0528mvp.utils.ParamHashMap;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements IHomeView {

    private RecyclerView recycler;
    private SmartRefreshLayout smart;
    private int pageId = 0;
    private HomePresenter homePresenter;
    private List<TestInfo.DataInfo> datas = new ArrayList<>();
    private TestAdapter testAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HomeModel homeModel = new HomeModel();
        homePresenter = new HomePresenter(this, homeModel);
        final Map<String, Object> params = new ParamHashMap().add("c", "api").add("a", "getList");


        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        smart = (SmartRefreshLayout) findViewById(R.id.smart);
        testAdapter = new TestAdapter(datas, this);
        recycler.setAdapter(testAdapter);
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh( RefreshLayout refreshLayout) {
                pageId = 0;
                homePresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.REFRESH,params,pageId);
            }
        });
        smart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore( RefreshLayout refreshLayout) {
                pageId++;
                homePresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.MORE,params,pageId);
            }
        });
        homePresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.NORMAL,params,pageId);
    }

    @Override
    public void onSuccess(int whichApi, int loadType, Object[] pD) {
        switch (whichApi){
            case ApiConfig.TEST_GET:
                if (loadType == LoadTypeConfig.MORE){
                    smart.finishLoadMore();
                } else if (loadType == LoadTypeConfig.REFRESH){
                    smart.finishRefresh();
                    datas.clear();
                }
                List<TestInfo.DataInfo> datas = ((TestInfo)pD[0]).datas;
                MainActivity.this.datas.addAll(datas);
                testAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onFailed(int whichApi, Throwable pThrowable) {
        Toast.makeText(MainActivity.this, pThrowable.getMessage()!=null ? pThrowable.getMessage():"网络请求发生错误", Toast.LENGTH_SHORT).show();
    }
}
