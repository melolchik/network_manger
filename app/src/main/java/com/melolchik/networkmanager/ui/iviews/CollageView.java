package com.melolchik.networkmanager.ui.iviews;

import com.melolchik.networkmanager.network.objects.JsonCollege;
import java.util.List;

/**
 * Created by melolchik on 08.04.2018.
 */

public interface CollageView extends BaseView {
	public void showList(List<JsonCollege> list);
	public void showEmptyState(boolean isEmpty);
}
