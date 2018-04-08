package com.melolchik.networkmanager.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melolchik.networkmanager.network.objects.JsonCollege;
import com.melolchik.networkmanager.ui.view.PlaceItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olga Melekhina on 08.06.2016.
 */
public class CollegeListAdapter extends RecyclerView.Adapter<CollegeListAdapter.ViewHolder> {

    /**
     * The M recycler view.
     */
    protected RecyclerView mRecyclerView;
    /**
     * The M context.
     */
    protected final Context mContext;
    /**
     * The M business list.
     */
    protected List<JsonCollege> mList;

    /**
     * The M list click listener.
     */
    private LayoutInflater inflater;

    protected int mSelected = -1;

    /**
     * Instantiates a new Business list adapter.
     *
     * @param recyclerView the recycler view
     */
    public CollegeListAdapter(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mRecyclerView.setClickable(true);
        mContext = mRecyclerView.getContext();
        mList = new ArrayList<>();
        inflater = LayoutInflater.from(mContext);
    }

    /**
     * Gets context.
     *
     * @return the context
     */
    public Context getContext() {
        return mContext;
    }

    public void setData(List<JsonCollege> list) {
        mSelected = -1;
        setAddressList(list);
        notifyDataSetChanged();
        mRecyclerView.invalidate();
    }

    protected void setAddressList(List<JsonCollege> businessList) {
        if (businessList == null) {
            mList = new ArrayList<>();
        } else {
            mList = new ArrayList<>(businessList);
        }
    }

    @Override
    public CollegeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = inflater.inflate(R.layout.view_business_detail_for_list, parent, false);
        PlaceItem item = new PlaceItem(parent.getContext());
        item.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CollegeListAdapter.ViewHolder holder, int position) {
        //log("onBindViewHolder position = " + position);
        holder.setData(getItem(position), position);

    }

    public JsonCollege getItem(int position) {
        if (mList == null || mList.isEmpty())
            return null;
        return mList.get(position);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
        //return 10;
    }

    public int getSelected() {
        return mSelected;
    }

    /**
     * The type View holder.
     */
    protected class ViewHolder extends RecyclerView.ViewHolder {


        private final PlaceItem mItemView;
        public ViewHolder(PlaceItem itemView) {
            super(itemView);
            mItemView = itemView;
        }

        public void setData(JsonCollege college,final int position) {
            mItemView.setTag(position);
            mItemView.setCollegeInfo(college);
            mItemView.setChecked(mSelected == position);
            mItemView.setClickable(true);
            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mSelected = mRecyclerView.getChildAdapterPosition(view);
                    notifyDataSetChanged();
                }
            });
        }
    
    }

    /**
     * Log.
     *
     * @param message the message
     */
    protected void log(String message) {
       // AppLogger.log(this.getClass().getSimpleName() + " " + message);
    }
}
