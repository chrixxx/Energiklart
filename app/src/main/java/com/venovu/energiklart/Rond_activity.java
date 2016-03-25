package com.venovu.energiklart;

/**
 * Created by Christoffer Nordfeldt on 2016-03-03.
 * Venovu
 * christoffer.nordfeldt@venovu.com
 */

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Rond_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rond);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout2);
        tabLayout.addTab(tabLayout.newTab().setText("Vent/Vindsisolering"));
        tabLayout.addTab(tabLayout.newTab().setText("Radiatorer/Fönster"));
        //tabLayout.addTab(tabLayout.newTab().setText("Övrigt"));
        //tabLayout.addTab(tabLayout.newTab().setText("Fönster"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager2);
        final PagerAdapterRond adapter = new PagerAdapterRond
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

    /*
    public void startRond(View v){
        startActivity(new Intent(Kund_activity.this, Rond_activity.class));
    }
    */
}
