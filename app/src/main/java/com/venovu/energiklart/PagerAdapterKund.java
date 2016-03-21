package com.venovu.energiklart;

/**
 * Created by Christoffer Nordfeldt on 2016-03-18.
 * Venovu
 * christoffer.nordfeldt@venovu.com
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapterKund extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterKund(FragmentManager fm, int NumOfTabs){
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                Tab_Fragment1_Kund tab1 = new Tab_Fragment1_Kund();
                return tab1;
            case 1:
                Tab_Fragment2_Kund tab2 = new Tab_Fragment2_Kund();
                return tab2;
            case 2:
                Tab_Fragment3_Kund tab3 = new Tab_Fragment3_Kund();
                return tab3;
            case 3:
                Tab_Fragment4_Kund tab4 = new Tab_Fragment4_Kund();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount(){
        return mNumOfTabs;

    }
}
