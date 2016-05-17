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
        this.setNames(names);
        this.setfNr(fNr);
        this.setAdress(adress);
        this.setByggår(byggår);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.listview_layout, null, true);
        TextView lv_owner = (TextView) listViewItem.findViewById(R.id.lv_owner);
        TextView lv_fNr = (TextView) listViewItem.findViewById(R.id.lv_fNr);
        TextView lv_adress = (TextView) listViewItem.findViewById(R.id.lv_adress);
        TextView lv_byggår = (TextView) listViewItem.findViewById(R.id.lv_byggår);

        lv_owner.setText("Ägare: " + getNames()[position]);
        lv_fNr.setText("Fastighets Nr: " + getfNr()[position]);
        lv_adress.setText("Adress: " + getAdress()[position]);
        lv_byggår.setText("Byggår: " + getByggår()[position]);


        return listViewItem;
    }

    public String[] getNames() {
        return names;
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public String[] getfNr() {
        return fNr;
    }

    public void setfNr(String[] fNr) {
        this.fNr = fNr;
    }

    public String[] getAdress() {
        return adress;
    }

    public void setAdress(String[] adress) {
        this.adress = adress;
    }

    public String[] getByggår() {
        return byggår;
    }

    public void setByggår(String[] byggår) {
        this.byggår = byggår;
    }
}
