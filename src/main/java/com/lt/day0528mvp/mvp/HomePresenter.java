package com.lt.day0528mvp.mvp;

public class HomePresenter implements IHomePresenter {
    private IHomeView mView;
    private IHomeModel mModel;

    public HomePresenter(IHomeView mView, IHomeModel pModel) {
        this.mView = mView;
        mModel = pModel;
    }

    @Override
    public void getData(int whichApi, Object... pPS) {
        mModel.getData(this, whichApi, pPS);
    }

    @Override
    public void onSuccess(int whichApi, int loadType, Object[] pD) {
        mView.onSuccess(whichApi, loadType, pD);
    }

    @Override
    public void onFailed(int whichApi, Throwable pThrowable) {
        mView.onFailed(whichApi, pThrowable);
    }
}
