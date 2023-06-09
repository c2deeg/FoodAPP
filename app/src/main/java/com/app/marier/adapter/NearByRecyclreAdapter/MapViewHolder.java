package com.app.marier.adapter.NearByRecyclreAdapter;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.GoogleMap;

public class MapViewHolder extends RecyclerView.ViewHolder {

    private MapViewListItemView mMapViewListItemView;
    private GoogleMap googleMap;

    public MapViewHolder(MapViewListItemView mapViewListItemView) {
        super(mapViewListItemView);
        mMapViewListItemView = mapViewListItemView;
    }

    public void mapViewListItemViewOnCreate(Bundle savedInstanceState) {
        if (mMapViewListItemView != null) {
            mMapViewListItemView.mapViewOnCreate(savedInstanceState);
        }
    }

    public void mapViewListItemViewOnResume() {
        if (mMapViewListItemView != null) {
            mMapViewListItemView.mapViewOnResume();
        }
    }

    public void mapViewListItemViewOnPause() {
        if (mMapViewListItemView != null) {
            mMapViewListItemView.mapViewOnPause();
        }
    }

    public void mapViewListItemViewOnDestroy() {
        if (mMapViewListItemView != null) {
            mMapViewListItemView.mapViewOnDestroy();
        }
    }

    public void mapViewListItemViewOnLowMemory() {
        if (mMapViewListItemView != null) {
            mMapViewListItemView.mapViewOnLowMemory();
        }
    }

    public void mapViewListItemViewOnSaveInstanceState(Bundle outState) {
        if (mMapViewListItemView != null) {
            mMapViewListItemView.mapViewOnSaveInstanceState(outState);
        }
    }
}
