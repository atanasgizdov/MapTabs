package com.example.nasko.maptabs2;

/**
 * Created by Nasko on 4/6/2017.
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

//http://www.truiton.com/2015/06/android-tabs-example-fragments-viewpager/
//http://www.truiton.com/2015/12/android-activity-fragment-communication/


public class MainActivity extends AppCompatActivity implements TabFragment3.OnHeadlineSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //create tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        //label tabs
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        //add on event listeners to Tabs that interact with Pager adapter
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }


        });
    }

    //https://developer.android.com/training/basics/fragments/communicating.html#Deliver
    // listener for grabbing values from activity and send to another activity

    public void onArticleSelected(String position) {
        // The user selected the headline of an article from the HeadlinesFragment
        // Do something here to display that article
    }

    //ovveride on back pressed so clicking the back button sends user back one link in webview fragment
    @Override
    public void onBackPressed() {

        TabFragment2 fragment = (TabFragment2)
                getSupportFragmentManager().findFragmentById(R.id.tab_layout);
        if (fragment.canGoBack()) {
            fragment.goBack();
        } else {
            super.onBackPressed();
        }
    }

}

        //TabFragment2 newFragment2 = new TabFragment2();
        //TabFragment2 articleFrag = (TabFragment2)
                //getSupportFragmentManager().findFragmentById(R.id.tabFragment2);

        //if (articleFrag == null) {
            // If article frag is available, we're in two-pane layout...

            // Call a method in the ArticleFragment to update its content
            /*Bundle args= new Bundle();
            args.putString("category", "clothes");
            args.putString("item", "shirts");
            newFragment2.setArguments(args);*/
            //getSupportFragmentManager().beginTransaction()
                    //.replace(R.id.tabFragment2, newFragment2).commit();

       // } else {
            /*TabFragment2 newFragment = new TabFragment2();
            Bundle args = new Bundle();
            args.putInt(TabFragment2.ARG_POSITION, position);
            newFragment.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.tabFragment2, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit(); */
       // }



  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    } */

