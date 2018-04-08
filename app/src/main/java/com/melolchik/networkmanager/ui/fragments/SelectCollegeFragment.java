package com.melolchik.networkmanager.ui.fragments;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.melolchik.networkmanager.R;
import com.melolchik.networkmanager.components.MessageItem;
import com.melolchik.networkmanager.network.objects.JsonCollege;
import com.melolchik.networkmanager.ui.adapters.CollegeListAdapter;
import com.melolchik.networkmanager.ui.adapters.decorations.DividerItemDecoration;
import com.melolchik.networkmanager.ui.iviews.CollageView;
import com.melolchik.networkmanager.ui.iviews.ErrorView;
import com.melolchik.networkmanager.ui.iviews.ProgressView;
import com.melolchik.networkmanager.ui.presenters.CollagePresenter;
import com.melolchik.networkmanager.ui.presenters.Presenter;
import com.melolchik.networkmanager.ui.view.SearchableTextInput;
import com.melolchik.networkmanager.utils.Util;
import java.util.List;

/**
 * Created by Olga Melekhina on 10.10.2016.
 */
public class SelectCollegeFragment extends BaseFragmentWithToolbar
	implements SearchableTextInput.OnChangeSearchListener, CollageView, ProgressView, ErrorView {
	
	protected final static String ARG_HINT = SelectCollegeFragment.class.getCanonicalName() + "ARG_HINT";
	
	protected @BindView(R.id.input_place) SearchableTextInput mPlaceInput;
	
	protected @BindView(R.id.text_empty) TextView mEmptyTextView;
	
	protected @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
	
	protected @BindView(R.id.progress_bar_layout) View mProgressBarLayout;
	
	protected CollagePresenter mCollagePresenter;
	
	protected CollegeListAdapter mAdapter;
	
	protected CollegeListener mPlaceListener = null;
	
	protected @StringRes int mHint;
	
	public static SelectCollegeFragment createInstance(@StringRes int title,
	                                                   @StringRes int hint,
	                                                   CollegeListener placeListener) {
		SelectCollegeFragment fragment = new SelectCollegeFragment();
		Bundle                args     = new Bundle();
		args.putInt(ARG_TITLE_STRING_ID, title);
		args.putInt(ARG_HINT, hint);
		fragment.setArguments(args);
		fragment.setPlaceListener(placeListener);
		return fragment;
	}
	
	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle args = getArguments();
		if (args != null) {
			mHint = args.getInt(ARG_HINT);
		}
	}
	
	@Override protected int getViewLayoutID() {
		return R.layout.fragment_select_place;
	}
	
	@Override protected void onCreateView(View rootView, Bundle savedInstanceState) {
		super.onCreateView(rootView, savedInstanceState);
		ButterKnife.bind(this, rootView);
		mCollagePresenter = new CollagePresenter();
		mCollagePresenter.attachView(this);
		
		mPlaceInput.setHint(getString(mHint));
		mPlaceInput.setOnChangeSearchListener(this);
		
		mRecyclerView.setHasFixedSize(true);
		LinearLayoutManager layoutManager = new LinearLayoutManager(mRecyclerView.getContext());
		mRecyclerView.setLayoutManager(layoutManager);
		mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext()));
		mAdapter = new CollegeListAdapter(mRecyclerView);
		//mAdapter.setOnItemClickListener(this);
		mRecyclerView.setAdapter(mAdapter);
	}
	
	@Override public void onChangeSearchString(final String searchString) {
		mCollagePresenter.loadCollegeList(searchString);
	}
	
	public void setPlaceListener(CollegeListener placeListener) {
		mPlaceListener = placeListener;
	}
	
	@Override public void showList(List<JsonCollege> list) {
		if (mAdapter != null) {
			mAdapter.setData(list);
		}
	}
	
	@Override public void showEmptyState(boolean isEmpty) {
		mProgressBarLayout.setVisibility(View.GONE);
		if (isEmpty) {
			mEmptyTextView.setVisibility(View.VISIBLE);
			mRecyclerView.setVisibility(View.GONE);
		} else {
			mEmptyTextView.setVisibility(View.GONE);
			mRecyclerView.setVisibility(View.VISIBLE);
		}
	}
	
	@Override public void showProgressBar(Presenter presenter) {
		mProgressBarLayout.setVisibility(View.VISIBLE);
		mEmptyTextView.setVisibility(View.GONE);
		mRecyclerView.setVisibility(View.GONE);
	}
	
	@Override public void hideProgressBar(Presenter presenter) {
		mProgressBarLayout.setVisibility(View.GONE);
		mEmptyTextView.setVisibility(View.INVISIBLE);
		mRecyclerView.setVisibility(View.GONE);
	}
	
	@Override public void showError(Presenter presenter, MessageItem errorItem) {
		Util.showErrorToast(errorItem.getMessage());
	}
	
	@Override public void networkError(Presenter presenter, MessageItem errorItem) {
		Util.showErrorToast(errorItem.getMessage());
	}

}
