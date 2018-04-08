package com.melolchik.networkmanager.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.melolchik.networkmanager.R;
import com.melolchik.networkmanager.network.ResponseCallback;
import com.melolchik.networkmanager.network.objects.JsonCollege;
import com.melolchik.networkmanager.network.responses.CollegeListResponse;
import com.melolchik.networkmanager.network.responses.CollegeLocationResponse;
import com.melolchik.networkmanager.network.resquests.CollegeListRequest;
import com.melolchik.networkmanager.network.resquests.CollegeLocationRequest;
import com.melolchik.networkmanager.ui.adapters.CollegeListAdapter;
import com.melolchik.networkmanager.ui.adapters.decorations.DividerItemDecoration;
import com.melolchik.networkmanager.ui.view.SearchableTextInput;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Olga Melekhina on 10.10.2016.
 */
public class SelectCollegeFragment extends BaseFragmentWithToolbar implements SearchableTextInput.OnChangeSearchListener {

    protected final static String ARG_HINT = SelectCollegeFragment.class.getCanonicalName() + "ARG_HINT";

    protected
    @BindView(R.id.input_place)
    SearchableTextInput mPlaceInput;

    protected
    @BindView(R.id.text_empty)
    TextView mEmptyTextView;

    protected
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    protected
    @BindView(R.id.progress_bar_layout)
    View mProgressBarLayout;

    protected CollegeListAdapter mAdapter;

    protected CollegeListener mPlaceListener = null;

    protected
    @StringRes
    int mHint;

    public static SelectCollegeFragment createInstance(@StringRes int title, @StringRes int hint, CollegeListener placeListener) {
        SelectCollegeFragment fragment = new SelectCollegeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TITLE_STRING_ID, title);
        args.putInt(ARG_HINT, hint);
        fragment.setArguments(args);
        fragment.setPlaceListener(placeListener);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mHint = args.getInt(ARG_HINT);
        }
    }


    @Override
    protected int getViewLayoutID() {
        return R.layout.fragment_select_place;
    }


    @Override
    protected void onCreateView(View rootView, Bundle savedInstanceState) {
        super.onCreateView(rootView,savedInstanceState);
        ButterKnife.bind(this, rootView);

        mPlaceInput.setHint(getString(mHint));
        mPlaceInput.setOnChangeSearchListener(this);


        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext()));
        mAdapter = new CollegeListAdapter(mRecyclerView);
        //mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        setProgressBarVisible(false);
        setListEmpty(true);
    }

    public void setProgressBarVisible(boolean isVisible) {
        if(isVisible){
            mProgressBarLayout.setVisibility(View.VISIBLE);
            mEmptyTextView.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.GONE);
        }else {
            mProgressBarLayout.setVisibility(View.GONE);
            mEmptyTextView.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
    }

    protected void setListEmpty(boolean isEmpty){
        mProgressBarLayout.setVisibility(View.GONE);
        if(isEmpty){
            mEmptyTextView.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }else {
            mEmptyTextView.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onChangeSearchString(final String searchString) {
        loadCollegeList(searchString);
    }

    public void setPlaceListener(CollegeListener placeListener) {
        mPlaceListener = placeListener;
    }

    protected void loadCollegeList(String query) {
        ResponseCallback<CollegeListResponse> callback = new ResponseCallback<CollegeListResponse>() {
            @Override
            public void onStartLoad() {
                setProgressBarVisible(true);
            }

            @Override
            public void onSuccess(CollegeListResponse response) {
                setProgressBarVisible(false);
                //log("response = " + response);
                mAdapter.setData(response.getResult());
                setListEmpty(response.getResult().isEmpty());
            }

            @Override
            public void onError(int statusCode, String message) {

                setProgressBarVisible(false);
                setListEmpty(true);
            }

            @Override
            public void onFailure(int statusCode, String message) {

                setProgressBarVisible(false);
                setListEmpty(true);
            }
        };

        CollegeListRequest request = new CollegeListRequest(callback);
        request.setQuery( query);
        request.setLimit(10);
        request.execute();
    }

    protected void loadCollegeLocation(String unitId) {
        ResponseCallback<CollegeLocationResponse> callback = new ResponseCallback<CollegeLocationResponse>() {
            @Override
            public void onStartLoad() {

            }

            @Override
            public void onSuccess(CollegeLocationResponse response) {

                if(mPlaceListener != null){
                    mPlaceListener.onCollegeSelected(response.getResult());
                }


            }

            @Override
            public void onError(int statusCode, String message) {

            }

            @Override
            public void onFailure(int statusCode, String message) {

            }
        };

        CollegeLocationRequest request = new CollegeLocationRequest(callback);
        request.setUnitId( unitId);
        request.execute();
    }

    protected void checkSelected() {
        int selected = mAdapter.getSelected();
        if(selected < 0) return;
        final JsonCollege jsonCollege = mAdapter.getItem(selected);
        if(jsonCollege == null) return;
        loadCollegeLocation(jsonCollege.getUnitid());
    }
}
