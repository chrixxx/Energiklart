package com.venovu.energiklart;

/**
 * Created by Christoffer Nordfeldt on 2016-03-03.
 * Venovu
 * christoffer.nordfeldt@venovu.com
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Tab_Fragment1_Kund extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment1_kund, container, false);
    }
}
