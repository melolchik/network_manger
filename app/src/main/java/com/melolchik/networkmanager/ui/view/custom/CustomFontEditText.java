package com.melolchik.networkmanager.ui.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.melolchik.networkmanager.R;


/**
 * Created by Olga Melekhina on 15.06.2016.
 */
public class CustomFontEditText extends EditText {

    public CustomFontEditText(Context context) {
        this(context, null);
    }

    public CustomFontEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomFontEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CustomFont,
                0, 0);
        int fontCode = 0;
        final Typeface customTypeface;
        try {
            fontCode = array.getInteger(R.styleable.CustomFont_customFont,100);
        } finally {
            array.recycle();
        }
        customTypeface = CustomFont.fromCode(fontCode).asTypeface(context);

        setTypeface(customTypeface);

    }
}


