package com.register.common.ui.presenter;

import android.text.TextUtils;

import com.register.common.components.MessageItem;
import com.register.common.ui.viewimpl.BaseView;
import com.register.common.ui.viewimpl.ErrorView;
import com.register.common.ui.viewimpl.ProgressView;
import com.register.common.ui.viewimpl.SuccessView;
import com.register.common.util.AppLogger;
import com.register.common.util.Util;

/**
 * Created by melolchik on 07.08.2017.
 */

public class BasePresenter implements Presenter<BaseView> {

    protected BaseView mBaseView;

    @Override
    public void attachView(BaseView viewImpl) {
        mBaseView = viewImpl;
    }

    @Override
    public void detachView() {
        mBaseView = null;
    }

    
    protected boolean isAttached(){
        return mBaseView != null;
    }

    protected ProgressView getProgressViewImpl(){
        if(mBaseView != null && mBaseView instanceof ProgressView){
            return (ProgressView)mBaseView;
        }
        return null;
    }

    protected SuccessView getSuccessViewImpl(){
        if(mBaseView != null && mBaseView instanceof SuccessView){
            return (SuccessView)mBaseView;
        }
        return null;
    }


    protected ErrorView getErrorViewImpl(){
        if(mBaseView != null && mBaseView instanceof ErrorView){
            return (ErrorView)mBaseView;
        }
        return null;
    }

    protected void showProgress(){
        AppLogger.log("showProgress");
        if(getProgressViewImpl() != null){
            getProgressViewImpl().showProgressBar(this);
        }
    }

    protected void hideProgress(){
        AppLogger.log("hideProgress");
        if(getProgressViewImpl() != null){
            getProgressViewImpl().hideProgressBar(this);
        }
    }

    protected void showSuccess(MessageItem errorItem){
        if(getSuccessViewImpl() != null){
            getSuccessViewImpl().showSuccess(this,errorItem);
        }
    }

    protected void showError(MessageItem errorItem){
        if(getErrorViewImpl() != null){
            getErrorViewImpl().showError(this,errorItem);
        }
    }

    protected void showNetworkError(){
        if(getErrorViewImpl() != null){
            getErrorViewImpl().showError(this,new MessageItem("Network is not available"));
        }
    }

    public static void showErrorToast(MessageItem messageItem) {
        if(messageItem == null) return;
        if(TextUtils.isEmpty(messageItem.getMessage())) return;
        Util.showErrorToast(messageItem.getMessage());
    }

}
