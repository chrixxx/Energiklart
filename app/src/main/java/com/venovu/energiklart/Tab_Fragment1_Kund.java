package com.venovu.energiklart;

/**
 * Created by Christoffer Nordfeldt on 2016-03-03.
 * Venovu
 * christoffer.nordfeldt@venovu.com
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Tab_Fragment1_Kund extends Fragment {
    StringRequest request;

    public static final String URL = "http://venovu.com/registerKund.php";
    public static final String URL1 = "http://venovu.com/registerBroker.php";
    RequestQueue requestQueue;

    private EditText name;
    private EditText phone;
    private EditText fakturaAd;
    private EditText cell;
    private EditText postOrt;
    private EditText postNr;
    private EditText mail;
    private EditText ssn;
    private EditText adress;
    private CheckBox faktura10;
    private CheckBox faktura30;
    private EditText fastighetsNr;
    public static final String userDetails = "userDetails" ;
    public static final String socialNr = "ssnKey";
    public static final String fastighetNr = "fastighetKey";





















    private Button save;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment1_kund, container, false);
        save =(Button) view.findViewById(R.id.save);

        name = (EditText) view.findViewById(R.id.name);
        phone = (EditText) view.findViewById(R.id.phone);
        fakturaAd = (EditText) view.findViewById(R.id.fakturaAd);
        cell = (EditText) view.findViewById(R.id.cell);
        postOrt = (EditText) view.findViewById(R.id.postort);
        postNr = (EditText) view.findViewById(R.id.postNr);
        mail = (EditText) view.findViewById(R.id.mail);
        ssn = (EditText) view.findViewById(R.id.ssn);
        adress = (EditText) view.findViewById(R.id.adress);
        faktura10 = (CheckBox) view.findViewById(R.id.faktura10);
        faktura30 = (CheckBox) view.findViewById(R.id.faktura30);
        fastighetsNr = (EditText)view.findViewById(R.id.fastighetsNr);




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertKund();
            }
        });

        return view;
    }


    public void InsertKund(){
        requestQueue = Volley.newRequestQueue(this.getActivity());

        request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.names().get(0).equals("success")) {
                        Toast.makeText(getActivity().getApplicationContext(), "SUCCESS " + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Error" + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                SharedPreferences prefs = getActivity().getSharedPreferences(userDetails, Context.MODE_PRIVATE);
                String restoredName = prefs.getString("nameKey", null);
                String restoredPass = prefs.getString("passKey",null);

                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("ssn", ssn.getText().toString());
                hashMap.put("namn", name.getText().toString());
                hashMap.put("hemTele", phone.getText().toString());
                hashMap.put("adress", adress.getText().toString());
                hashMap.put("postNr", postNr.getText().toString());
                hashMap.put("mobil", cell.getText().toString());
                hashMap.put("mail", mail.getText().toString());
                hashMap.put("user_userName", restoredName);
                hashMap.put("user_userPass", restoredPass);
                hashMap.put("fastighetsNr", fastighetsNr.getText().toString());
                hashMap.put("fakturaAdress", fakturaAd.getText().toString());
                hashMap.put("postOrt", postOrt.getText().toString());
                hashMap.put("fakturanummer", "40");
                if(faktura10.isChecked())
                    hashMap.put("faktura10", "1");
                else
                hashMap.put("faktura10", "0");
                if(faktura30.isChecked())
                hashMap.put("faktura30", "1");
                else
                    hashMap.put("faktura30", "0");
                //Sparar inskrivna fastighetnr och SSN till shared preferences
                String fastighet  = fastighetsNr.getText().toString();
                String ssNr = ssn.getText().toString();

                SharedPreferences.Editor editor = prefs.edit();

                editor.putString(fastighetNr, fastighet);
                editor.putString(socialNr, ssNr);


                editor.commit();


                return hashMap;
            }
        };
        requestQueue.add(request);
    }


}




