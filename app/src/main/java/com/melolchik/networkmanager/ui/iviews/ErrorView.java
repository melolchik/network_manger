package com.melolchik.networkmanager.ui.iviews;

import com.melolchik.networkmanager.components.MessageItem;
import com.melolchik.networkmanager.ui.presenters.Presenter;

/**
 * Created by melolchik on 29.03.2017.
 */
public interface ErrorView extends BaseView {

    void showError(Presenter presenter, MessageItem errorItem);

    void networkError(Presenter presenter, MessageItem errorItem);

}
