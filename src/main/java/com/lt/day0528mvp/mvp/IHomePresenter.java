package com.lt.day0528mvp.mvp;

public interface IHomePresenter<P>extends IHomeView {
    void getData(int whichApi,P... pPS);
}
