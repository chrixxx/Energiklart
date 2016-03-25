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
    private String [] pass;
    private Activity context;

    public CustomList (Activity context, String[] names, String[] pass){
        super(context, R.layout.listview_layout, names);
        this.context = context;
        this.names = names;
        this.pass = pass;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.listview_layout, null, true);
        TextView lv_userName = (TextView) listViewItem.findViewById(R.id.lv_userName);
        TextView lv_userpass = (TextView) listViewItem.findViewById(R.id.lv_userPass);

        lv_userName.setText("Username: " + names[position]);
        lv_userpass.setText("Password: " + pass [position]);


        return listViewItem;
    }
}
