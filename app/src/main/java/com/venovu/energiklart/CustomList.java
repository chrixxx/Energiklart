package com.venovu.energiklart;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Christoffer Nordfeldt on 2016-03-25.
 * Venovu
 * christoffer.nordfeldt@venovu.com
 */
public class CustomList extends ArrayAdapter<String> {

    private String [] names;
    private String [] fNr;
    private String [] adress;
    private String [] byggår;
    private Activity context;

    public CustomList (Activity context, String[] names, String[] fNr, String[] adress, String[] byggår){
        super(context, R.layout.listview_layout, names);
        this.context = context;
        this.names = names;
        this.fNr = fNr;
        this.adress = adress;
        this.byggår = byggår;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.listview_layout, null, true);
        TextView lv_owner = (TextView) listViewItem.findViewById(R.id.lv_owner);
        TextView lv_fNr = (TextView) listViewItem.findViewById(R.id.lv_fNr);
        TextView lv_adress = (TextView) listViewItem.findViewById(R.id.lv_adress);
        TextView lv_byggår = (TextView) listViewItem.findViewById(R.id.lv_byggår);

        lv_owner.setText("Ägare: " + names[position]);
        lv_fNr.setText("Fastighets Nr: " + fNr [position]);
        lv_adress.setText("Adress: " + adress [position]);
        lv_byggår.setText("Byggår: " + byggår [position]);


        return listViewItem;
    }
}
