package com.melolchik.networkmanager.ui.iviews;

import com.melolchik.networkmanager.ui.presenters.Presenter;

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
