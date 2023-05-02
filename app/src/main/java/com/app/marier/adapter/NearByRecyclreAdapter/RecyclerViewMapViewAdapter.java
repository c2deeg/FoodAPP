package com.app.marier.adapter.NearByRecyclreAdapter;

import android.view.ViewGroup;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class RecyclerViewMapViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final FragmentActivity activity;

    public RecyclerViewMapViewAdapter(@NotNull FragmentActivity fragmentActivity) {
        this.activity = fragmentActivity;
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MapViewListItemView mapViewListItemView = new MapViewListItemView(activity);
        mapViewListItemView.mapViewOnCreate(null);
//        mMapViewListItemViews.add(mapViewListItemView);
        return new MapViewHolder(mapViewListItemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MapViewHolder mapViewHolder = (MapViewHolder) holder;
        mapViewHolder.mapViewListItemViewOnResume();
    }
}
