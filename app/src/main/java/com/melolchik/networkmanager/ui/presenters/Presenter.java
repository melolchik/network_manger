package com.register.common.ui.presenter;


import com.register.common.ui.viewimpl.BaseView;

/**
 * Created by melolchik on 29.03.2017.
 */

public interface Presenter<T extends BaseView> {

    public void attachView(T viewImpl);
    public void detachView();
}
