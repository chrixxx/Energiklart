package com.venovu.energiklart;

/**
 * Created by Christoffer Nordfeldt on 2016-03-18.
 * Venovu
 * christoffer.nordfeldt@venovu.com
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapterRond extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapterRond(FragmentManager fm, int NumOfTabs){
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                Tab_Fragment1_Rond tab1_rond = new Tab_Fragment1_Rond();
                return tab1_rond;
            case 1:
                Tab_Fragment2_Rond tab2_rond = new Tab_Fragment2_Rond();
                return tab2_rond;
            case 2:
                Tab_Fragment3_Rond tab3_rond = new Tab_Fragment3_Rond();
                return tab3_rond;
            case 3:
                Tab_Fragment4_Rond tab4_rond = new Tab_Fragment4_Rond();
                return tab4_rond;
            default:
                return null;
        }
    }

    @Override
    public int getCount(){
        return mNumOfTabs;

    }
}
