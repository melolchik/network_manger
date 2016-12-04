package com.melolchik.networkmanager.ui.view;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Olga Melekhina on 11.10.2016.
 */
public class SearchableTextInput extends TextInput {

    protected OnChangeSearchListener mOnChangeSearchListener;

    protected Timer mTimer;

    protected SearchTimerTask mTimerTask;

    protected Handler mUiHandler;

    protected String mCurrentSearchValue = "";


    public interface OnChangeSearchListener {
        void onChangeSearchString(String searchString);
    }

    public SearchableTextInput(Context context) {
        this(context, null);
    }

    public SearchableTextInput(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchableTextInput(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addTextChangedListener(mSearchTextWatcher);
        mEditText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                                                @Override
                                                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                                    if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                                            actionId == EditorInfo.IME_ACTION_DONE ||
                                                            event.getAction() == KeyEvent.ACTION_DOWN &&
                                                                    event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                                                        onSearchAction(v);
                                                        return true;
                                                    }
                                                    return false;
                                                }
                                            }

        );
    }

    public void setOnChangeSearchListener(OnChangeSearchListener onChangeSearchListener) {
        mOnChangeSearchListener = onChangeSearchListener;
    }

    protected TextWatcher mSearchTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mCurrentSearchValue = s.toString();
            startTimer();
        }
    };

    protected void onSearchAction(View view){
        cancelTimer();
        mCurrentSearchValue = mEditText.getText().toString();
        if (mOnChangeSearchListener != null) {
            mOnChangeSearchListener.onChangeSearchString(mCurrentSearchValue);
        }
    }

    public void startTimer() {
        //log("start timer");
        cancelTimer();
        mTimer = new Timer();
        mTimerTask = new SearchTimerTask();
        mTimer.schedule(mTimerTask, 1800);
        mUiHandler = new Handler();
    }

    /**
     * Cancel timer.
     */
    public void cancelTimer() {
        if (mTimerTask != null) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }

    }

    /**
     * The type Counter timer task.
     */
    protected class SearchTimerTask extends TimerTask {

        @Override
        public void run() {
            //log("run ---> start search");
            mUiHandler.post(new Runnable() {
                @Override
                public void run() {

                    //if (string != null && string.equals(mCurrentSearchValue)) {
                    if (mOnChangeSearchListener != null) {
                        mOnChangeSearchListener.onChangeSearchString(mCurrentSearchValue);
                    }
                    // }
                }
            });
        }
    }

}
