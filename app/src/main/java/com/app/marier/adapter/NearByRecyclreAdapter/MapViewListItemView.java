package com.app.marier.adapter.NearByRecyclreAdapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.app.marier.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapViewListItemView extends LinearLayout {

    protected MapView mMapView;
    GoogleMap googleMap;
    Marker marker;

    public MapViewListItemView(Context context) {
        this(context, null);
    }

    public MapViewListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setupView();
    }

    @SuppressLint("MissingInflatedId")
    private void setupView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.nearbyrecycleritem, this);
        mMapView = (MapView) view.findViewById(R.id.list_item_map_view_mapview);
        mMapView.getMapAsync(new OnMapReadyCallback() {
            private GoogleMap mMap;

            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                this.mMap = googleMap;
                if (googleMap != null) {
                    LatLng mylocation = new LatLng(30.7046, 76.7179);
                    mMap.addMarker(new MarkerOptions().position(mylocation).snippet("TEXT BELLOW TITLE").title("TITLE")).showInfoWindow();
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    mMap.setMyLocationEnabled(true);
                    mMap.getUiSettings().setMyLocationButtonEnabled(true);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mylocation,15));
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mylocation, 10);
                    mMap.animateCamera(cameraUpdate);
                }
            }
        });

        setOrientation(VERTICAL);
    }

    public void mapViewOnCreate(Bundle savedInstanceState) {
        if (mMapView != null) {
            mMapView.onCreate(savedInstanceState);
        }
    }

    public void mapViewOnResume() {
        if (mMapView != null) {
            mMapView.onResume();
        }
    }

    public void mapViewOnPause() {
        if (mMapView != null) {
            mMapView.onPause();
        }
    }

    public void mapViewOnDestroy() {
        if (mMapView != null) {
            mMapView.onDestroy();
        }
    }

    public void mapViewOnLowMemory() {
        if (mMapView != null) {
            mMapView.onLowMemory();
        }
    }

    public void mapViewOnSaveInstanceState(Bundle outState) {
        if (mMapView != null) {
            mMapView.onSaveInstanceState(outState);
        }
    }
}
