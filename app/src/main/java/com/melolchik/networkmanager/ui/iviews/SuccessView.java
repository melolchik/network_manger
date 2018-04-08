package com.register.common.ui.viewimpl;


import com.register.common.components.MessageItem;
import com.register.common.ui.presenter.Presenter;

/**
 * Created by melolchik on 29.03.2017.
 */
public interface SuccessView extends BaseView {

    void showSuccess(Presenter presenter, MessageItem errorItem);

}
