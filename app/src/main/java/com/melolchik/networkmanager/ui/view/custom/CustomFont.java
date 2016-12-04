package com.melolchik.networkmanager.ui.view.custom;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Olga Melekhina on 15.06.2016.
 */
public enum CustomFont {
    DEFAULT("", 0),
    ROBOTO_REGULAR("fonts/RobotoRegular.ttf", 100),
    ROBOTO_LIGHT("fonts/RobotoLight.ttf", 101),
    ROBOTO_MEDIUM("fonts/RobotoMedium.ttf", 102),//default
    ROBOTO_BOLD("fonts/RobotoBold.ttf", 103),

    SF_UI_REGULAR("fonts/SFUIText-Regular.otf",200),
    SF_UI_LIGHT("fonts/SFUIText-Light.otf",201),
    SF_UI_MEDIUM("fonts/SFUIText-Medium.otf",202),
    SF_UI_BOLD("fonts/SFUIText-Bold.otf",203);

    private final String fileName;
    private final int code;

    CustomFont(String fileName, int code) {
        this.fileName = fileName;
        this.code = code;
    }

    public static CustomFont fromString(String fontName) {
        CustomFont local = null;
        for (CustomFont item : CustomFont.values()) {
            if (item.fileName == fontName) {
                local = item;
                break;
            }
        }
        if (local == null) {
            local = ROBOTO_REGULAR;
        }
        return local;
    }

    public static CustomFont fromCode(int code) {
        CustomFont local = null;
        for (CustomFont item : CustomFont.values()) {
            if (item.code == code) {
                local = item;
                break;
            }
        }
        if (local == null) {
            local = ROBOTO_REGULAR;
        }
        return local;
    }

    public Typeface asTypeface(Context context) {
        if (this == DEFAULT)
            return Typeface.DEFAULT;
        else
            return Typeface.createFromAsset(context.getAssets(), fileName);
    }
}

