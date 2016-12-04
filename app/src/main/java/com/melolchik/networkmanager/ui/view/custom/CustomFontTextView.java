package com.melolchik.networkmanager.ui.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.melolchik.networkmanager.R;


/**
 * Created by Olga Melekhina on 15.06.2016.
 */
public class CustomFontTextView extends TextView {

    public CustomFontTextView(Context context) {
        this(context, null);
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
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


