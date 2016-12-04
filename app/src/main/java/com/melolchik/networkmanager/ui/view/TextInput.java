package com.melolchik.networkmanager.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputLayout;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.melolchik.networkmanager.R;
import com.melolchik.networkmanager.ui.view.custom.CustomFont;
import com.melolchik.networkmanager.utils.Util;

/**
 * Created by Olga Melekhina on 14.09.2016.
 */
public class TextInput extends FrameLayout {

    protected
    @BindView(R.id.input_layout)
    TextInputLayout mTextInputLayout;

    protected
    @BindView(R.id.input)
    EditText mEditText;
    protected final String mErrorText;
    protected final String mErrorTextNotCorrect;

    protected @LayoutRes
    int getLayoutId(){
        return R.layout.view_text_input_layout;
    }

    public TextInput(Context context) {
        this(context, null);
    }

    public TextInput(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextInput(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, getLayoutId(), this);
        ButterKnife.bind(this, this);

        TypedArray array = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TextInput,
                0, 0);
        String hint = "";
        int type = 0;
        boolean errorEnabled = false;
        try {
            hint = array.getString(R.styleable.TextInput_hint);
            type = array.getInteger(R.styleable.TextInput_inputType,0);
            errorEnabled = array.getBoolean(R.styleable.TextInput_errorShow,false);
            mErrorText = array.getString(R.styleable.TextInput_errorText);
            mErrorTextNotCorrect = array.getString(R.styleable.TextInput_errorNotCorrect);
        } finally {
            array.recycle();
        }
        //mEditText.setHint(hint);
        mEditText.setInputType(type);
        mTextInputLayout.setTypeface(CustomFont.ROBOTO_BOLD.asTypeface(getContext()));
        mTextInputLayout.setHint(hint);
        mTextInputLayout.setErrorEnabled(errorEnabled);
        mEditText.setTextSize(TypedValue.COMPLEX_UNIT_DIP,13);
    }

    public void setText(String text){
        mEditText.setText(text);
    }

    public String getText(){
        return mEditText.getText().toString();
    }

    @Override
    public void setOnTouchListener(final OnTouchListener listener) {
        super.setOnTouchListener(listener);
        mEditText.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(listener != null){
                    listener.onTouch(TextInput.this,event);
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void setOnFocusChangeListener(final OnFocusChangeListener listener) {
        super.setOnFocusChangeListener(listener);
        mEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(listener != null){
                    listener.onFocusChange(TextInput.this,hasFocus);
                }
            }
        });
    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        mEditText.requestFocus(direction,previouslyFocusedRect);
        return super.requestFocus(direction, previouslyFocusedRect);
    }

    public void setErrorEnable(boolean isErrorEnable){
        mTextInputLayout.setErrorEnabled(isErrorEnable);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        mEditText.setEnabled(enabled);
    }

    public void setError(String text){
        mTextInputLayout.setError(text);
    }

    public void setError(@StringRes int textResId){
        mTextInputLayout.setError(getContext().getString(textResId));
    }

   // @Override
    public void setHint(@Nullable CharSequence hint) {
        mTextInputLayout.setHint(hint);
    }

    public void setHint(@StringRes int hint){
        mTextInputLayout.setHint(getContext().getString(hint));
    }

    public void addTextChangedListener(TextWatcher textWatcher){
        mEditText.addTextChangedListener(textWatcher);
    }

    public void removeTextChangedListener(TextWatcher textWatcher){
        mEditText.removeTextChangedListener(textWatcher);
    }

    public void showErrorToast(String message) {
        Toast toast = Toast.makeText(getContext() , message, Toast.LENGTH_LONG);
        toast.show();
    }

    public String check(){

        String text = getText();
        if(TextUtils.isEmpty(text)) {
            setError(mErrorText);
            showErrorToast(mErrorText);
            return null;

        }else if(mEditText.getInputType() ==  (InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)){
            if(!Util.isEmailValid(text)){
                setError(mErrorTextNotCorrect);
                showErrorToast(mErrorTextNotCorrect);
                return null;
            }
        }
        setError("");
        return text;

    }

}
