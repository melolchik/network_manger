package com.register.common.components;

import android.support.annotation.StringRes;

import com.register.common.util.Util;

/**
 * Created by melolchik on 31.03.2017.
 */

public class MessageItem {
	
	protected String mTitle;
	protected String mMessage;
	
	public MessageItem(String message) {
		setMessage(message);
	}
	
	public MessageItem(String title, String message) {
		setTitle(title);
		setMessage(message);
	}
	
	public MessageItem(@StringRes int title, @StringRes int message) {
		setTitle(title);
		setMessage(message);
	}
	public MessageItem(@StringRes int message) {
		setMessage(message);
	}
	
	public String getTitle() {
		return Util.notEmptyString(mTitle);
	}
	
	public void setTitle(String title) {
		mTitle = title;
	}
	
	public void setTitle(@StringRes int title) {
		mTitle = Util.getApplicationContext().getString(title);
	}
	
	public String getMessage() {
		return Util.notEmptyString(mMessage);
	}
	
	public void setMessage(String message) {
		mMessage = message;
	}
	
	public void setMessage(@StringRes int message) {
		mMessage = Util.getApplicationContext().getString(message);
	}
	
	@Override
	public String toString() {
		return mMessage;
	}
}
