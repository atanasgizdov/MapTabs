package com.example.nasko.maptabs2;

/**
 * Created by agizdov on 4/7/2017.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

//http://stackoverflow.com/questions/20750118/displaying-list-of-strings-in-android

public class TabFragment3 extends Fragment {
    OnHeadlineSelectedListener mCallback;

    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(String position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    private ArrayAdapter<String> adapter;
    ArrayList<String> websites;
    ListView listView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //inflate view
        View view = inflater.inflate(R.layout.tab_fragment_3, container, false);

        listView = (ListView) view.findViewById(R.id.list);

        //add sites to listview
        websites = new ArrayList<String>();

        websites.add("http://www.massvacation.com/explore/sightseeing/");
        websites.add("http://www.restaurantweekboston.com/");
        websites.add("http://www.bostonmagazine.com/best-of-boston/award/ice-cream/");
        websites.add("https://www.boston.gov/departments/boston-bikes/bike-maps-and-routes");
        websites.add("https://www.boston.com/");

        // create adapter to translate data to UI
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, websites);

        listView.setAdapter(adapter);
            //implement onclick listener
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    mCallback.onArticleSelected(websites.get(position));

                    //grab fragment 2 id and create bundle/pass URL info to it
                    TabFragment2 articleFrag = getMapFragment();
                    Bundle bundle = new Bundle();
                    String toPass = websites.get(position);
                    bundle.putString("passedKey", toPass);
                    articleFrag.setArguments(bundle);
                    getFragmentManager().beginTransaction().add(R.id.tab_layout, articleFrag).commit();
                }
            });


        return view;
    }

    //grab fragment id
    public TabFragment2 getMapFragment(){
        return (TabFragment2) getFragmentManager().findFragmentById(R.id.tab_layout);
    }



    }


