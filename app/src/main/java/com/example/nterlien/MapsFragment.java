package com.example.nterlien;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mGoogleMap;
    MapView mMapview;
    View mView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {







        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.mapsfragmentlayout, container, false);
        return mView;


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapview = (MapView) mView.findViewById(R.id.map);
        if (mMapview != null) {
            mMapview.onCreate(null);
            mMapview.onResume();
            mMapview.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setTrafficEnabled(true);
        googleMap.setBuildingsEnabled(true);

        googleMap.setMinZoomPreference(14);
        googleMap.setMaxZoomPreference(19);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(52.366183, 5.238210)).title("NTerlien hoofdkwartier"));
        CameraPosition nasa = CameraPosition.builder().target(new LatLng(52.366183, 5.238210)).zoom(15).bearing(2).tilt(45).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(nasa));
        googleMap.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                        this.requireContext(), R.raw.maps));

    }

}











