package com.lt.day0528mvp.mvp;

public interface IHomeModel<T> {
    void getData(IHomePresenter pPresenter,int whichApi,T... params);
}
