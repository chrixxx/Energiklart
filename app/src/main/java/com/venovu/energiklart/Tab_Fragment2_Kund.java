package com.venovu.energiklart;

/**
 * Created by Christoffer Nordfeldt on 2016-03-03.
 * Venovu
 * christoffer.nordfeldt@venovu.com
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
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

public class Tab_Fragment2_Kund extends Fragment {
    public static final String URL = "http://venovu.com/registerHouse.php";
    public static final String URL1 = "http://venovu.com/registerBroker.php";
    public static final String userDetails = "userDetails" ;
    private Spinner boende;
    private Spinner våningar;
    private EditText byggår;
    private EditText källarTemp;
    private EditText bostadTemp;
    private RadioButton energibesparande_ja;
    private RadioButton getEnergibesparande_nej;
    private RadioButton friliggande;
    private RadioButton gavel;
    private RadioButton mellan;
    StringRequest request;
    StringRequest request1;
    RequestQueue requestQueue;
    private CheckBox brokerPay;
    private EditText broker;
    private Button save;
    String value;
    String energibespar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment2_kund, container, false);

        boende =(Spinner) view.findViewById(R.id.boendeSpinner);
        våningar =(Spinner) view.findViewById(R.id.våningSpinner);
        byggår = (EditText) view.findViewById(R.id.byggÅr);
        källarTemp = (EditText) view.findViewById(R.id.källarTemp);
        bostadTemp = (EditText) view.findViewById(R.id.bostadTemp);
        energibesparande_ja = (RadioButton) view.findViewById(R.id.energibesparande_ja);
        //getEnergibesparande_nej =(RadioButton) view.findViewById(R.id.energibesparande_nej);
        friliggande = (RadioButton) view.findViewById(R.id.friliggande);
        gavel = (RadioButton) view.findViewById(R.id.gavel);
        mellan = (RadioButton) view.findViewById(R.id.mellanHus);
        brokerPay = (CheckBox) view.findViewById(R.id.brokerPay);
        save =(Button) view.findViewById(R.id.save);
        broker = (EditText) view.findViewById(R.id.broker);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertHouse();
               // InsertBroker();
            }
        });

        return view;
    }


    public void InsertHouse(){
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
                String restoredPass = prefs.getString("passKey", null);
                String restoredFnr = prefs.getString("fastighetKey", null);
                String restoredSsn = prefs.getString("ssnKey", null);

                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("user_userName", restoredName);
                hashMap.put("user_userPass", restoredPass);
                hashMap.put("våningar", "2");
                hashMap.put("antalBoende", "1");

                if(friliggande.isChecked())
                hashMap.put("friliggande", "1");
                else
                hashMap.put("friliggande", "0");

                if(gavel.isChecked())
                hashMap.put("gavelHus", "1");
                else
                hashMap.put("gavelHus", "0");

                if(mellan.isChecked())
                hashMap.put("mellanLiggande", "1");
                else
                    hashMap.put("mellanLiggande", "0");
                hashMap.put("byggnadsår", byggår.getText().toString());
                hashMap.put("owner_fastighetsNr", restoredFnr);
                hashMap.put("owner_ssn", restoredSsn);
                hashMap.put("tempVintertid", bostadTemp.getText().toString());
                hashMap.put("tempVtK", källarTemp.getText().toString());
                if(energibesparande_ja.isChecked())
                hashMap.put("energibespar", "1");
                else
                    hashMap.put("energibespar", "0");






                return hashMap;
            }
        };
        requestQueue.add(request);
    }

    public void InsertBroker(){
        requestQueue = Volley.newRequestQueue(this.getActivity());

        request1 = new StringRequest(Request.Method.POST, URL1, new Response.Listener<String>() {

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


                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("namn", broker.getText().toString());
                if(brokerPay.isChecked())
                    hashMap.put("betalar", value = "1" );
                else
                    hashMap.put("betalar", value = "0" );

                return hashMap;
            }
        };
        requestQueue.add(request1);
    }
}
