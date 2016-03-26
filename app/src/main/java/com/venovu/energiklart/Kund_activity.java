package com.venovu.energiklart;

/**
 * Created by Christoffer Nordfeldt on 2016-03-03.
 * Venovu
 * christoffer.nordfeldt@venovu.com
 */
// Stoffe l33t

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toolbar;

public class Kund_activity extends AppCompatActivity {

    private Button spara;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kund);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //spara = (Button)findViewById(R.id.spara);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Kontakt-uppg"));
        tabLayout.addTab(tabLayout.newTab().setText("Användande"));
        tabLayout.addTab(tabLayout.newTab().setText("Användande"));
        tabLayout.addTab(tabLayout.newTab().setText("Förbrukning"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapterKund adapter = new PagerAdapterKund
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

      /*           Försök att kalla på metoder i fragments i activity
        spara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                Tab_Fragment1_Kund fragmentKund1 = (Tab_Fragment1_Kund) fm.findFragmentById(R.id.tab_fragment1_kund);
                Tab_Fragment2_Kund fragmentKund2 = (Tab_Fragment2_Kund) fm.findFragmentById(R.id.tab_layout);
                fragmentKund1.InsertKund();
                fragmentKund2.InsertHouse();
            }
        });
    }


    public void startRond(View v){
        startActivity(new Intent(Kund_activity.this, Rond_activity.class));
    }
    */
}
}
