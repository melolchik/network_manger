package com.melolchik.networkmanager.ui.presenters;

import com.melolchik.networkmanager.ui.iviews.BaseView;

/**
 * Created by melolchik on 29.03.2017.
 */

public interface Presenter<T extends BaseView> {

    public void attachView(T viewImpl);
    public void detachView();
}
