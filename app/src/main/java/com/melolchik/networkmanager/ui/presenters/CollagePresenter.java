package com.melolchik.networkmanager.ui.presenters;

import com.melolchik.networkmanager.components.MessageItem;
import com.melolchik.networkmanager.network.ResponseCallback;
import com.melolchik.networkmanager.network.objects.JsonCollege;
import com.melolchik.networkmanager.network.responses.CollegeListResponse;
import com.melolchik.networkmanager.network.resquests.CollegeListRequest;
import com.melolchik.networkmanager.ui.iviews.BaseView;
import com.melolchik.networkmanager.ui.iviews.CollageView;
import java.util.List;

/**
 * Created by melolchik on 08.04.2018.
 */

public class CollagePresenter extends BasePresenter {
	
	@Override public void attachView(BaseView viewImpl) {
		super.attachView(viewImpl);
		hideProgress();
		showEmptyState(true);
	}
	
	public void loadCollegeList(String query) {
		ResponseCallback<CollegeListResponse> callback = new ResponseCallback<CollegeListResponse>() {
			@Override public void onStartLoad() {
				showProgress();
			}
			
			@Override public void onSuccess(CollegeListResponse response) {
				hideProgress();
				//log("response = " + response);
				showList(response.getResult());
				showEmptyState(response.getResult().isEmpty());
			}
			
			@Override public void onError(int statusCode, String message) {
				
				hideProgress();
				showEmptyState(true);
				showError(new MessageItem(message));
			}
			
			@Override public void onFailure(int statusCode, String message) {
				
				hideProgress();
				showEmptyState(true);
			}
		};
		
		CollegeListRequest request = new CollegeListRequest(callback);
		request.setQuery(query);
		request.setLimit(10);
		request.execute();
	}
	
	protected CollageView getCollageView() {
		if (mBaseView != null && mBaseView instanceof CollageView) {
			return (CollageView) mBaseView;
		}
		return null;
	}
	
	protected void showList(List<JsonCollege> list) {
		CollageView collageView = getCollageView();
		if (collageView != null) {
			collageView.showList(list);
		}
	}
	
	protected void showEmptyState(boolean isEmpty) {
		CollageView collageView = getCollageView();
		if (collageView != null) {
			collageView.showEmptyState(isEmpty);
		}
	}
}
