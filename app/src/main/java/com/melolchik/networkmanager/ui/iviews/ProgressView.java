package com.register.common.ui.viewimpl;


import com.register.common.ui.presenter.Presenter;

/**
 * Created by melolchik on 29.03.2017.
 */
public interface ProgressView extends BaseView {
    /**
     * Show progress bar.
     */
    void showProgressBar(Presenter presenter);

    /**
     * Hide progress bar.
     */
    void hideProgressBar(Presenter presenter);



}
