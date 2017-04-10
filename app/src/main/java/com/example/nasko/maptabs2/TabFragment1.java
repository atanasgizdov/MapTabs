package com.example.nasko.maptabs2;

/**
 * Created by agizdov on 4/7/2017.
 */

//http://stackoverflow.com/questions/19353255/how-to-put-google-maps-v2-on-a-fragment-using-viewpager

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class TabFragment1 extends Fragment  {

    MapView mMapView;
    private GoogleMap googleMap;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.tab_fragment_1, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }



        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                //googleMap.setMyLocationEnabled(true);

                // For dropping a marker at a point on the Map
                LatLng brandeis = new LatLng(42.3662392,-71.2606579);
                googleMap.addMarker(new MarkerOptions().position(brandeis).title("Brandeis").snippet("Brandeis University"));

                LatLng bentley = new LatLng(42.3856989,-71.2238687);
                googleMap.addMarker(new MarkerOptions().position(bentley).title("Bentley").snippet("Bentley University"));

                LatLng harvard = new LatLng(42.3770029,-71.1188488);
                googleMap.addMarker(new MarkerOptions().position(harvard).title("Harvard").snippet("Harvard University"));

                LatLng mit = new LatLng(42.360091,-71.0963487);
                googleMap.addMarker(new MarkerOptions().position(mit).title("MIT").snippet("MIT"));



                // For zooming automatically to the location of the marker
                //CameraPosition cameraPosition = new CameraPosition.Builder().target(brandeis).zoom(12).build();
                //googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                googleMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(42.3770029,-71.1188488) , 10.0f) );

                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
                {

                    @Override
                    public boolean onMarkerClick(Marker arg0) {
                        Toast.makeText(getActivity(), arg0.getTitle(), Toast.LENGTH_SHORT).show();// display toast


                        //create bundle to pass parameters
                        TabFragment2 articleFrag = getMapFragment();
                        Bundle bundle = new Bundle();

                        // grab website address depending on which college is clicked
                        String website = "";

                        if (arg0.getTitle().equals("MIT")){
                            website = "http://web.mit.edu/";
                        }
                        else if (arg0.getTitle().equals ("Harvard")){
                            website = "http://www.harvard.edu/";
                        }
                        else if (arg0.getTitle().equals("Bentley")){
                            website = "http://www.bentley.edu/";
                        }
                        else if (arg0.getTitle().equals ("Brandeis")){
                            website = "https://www.brandeis.edu/";
                        }

                        //pass bundle data
                        bundle.putString("passedKey", website);
                        articleFrag.setArguments(bundle);
                        getFragmentManager().beginTransaction().add(R.id.tab_layout, articleFrag).commit();

                        return true;
                    }

                });


            }
        });



        return rootView;

}
    //grab fragment to pass bundle to
    public TabFragment2 getMapFragment(){
        return (TabFragment2) getFragmentManager().findFragmentById(R.id.tab_layout);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}




