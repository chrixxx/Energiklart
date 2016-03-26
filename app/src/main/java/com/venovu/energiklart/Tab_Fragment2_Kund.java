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
    public static final String userDetails = "userDetails" ;
    private Spinner boende;
    private Spinner våningar;
    private EditText byggår;
    private RadioButton energibesparande_ja;
    private RadioButton getEnergibesparande_nej;
    private RadioButton friliggande;
    private RadioButton gavel;
    private RadioButton mellan;
    StringRequest request;
    RequestQueue requestQueue;
    String fri;
    String gav;
    String mel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment2_kund, container, false);

        boende =(Spinner) view.findViewById(R.id.boendeSpinner);
        våningar =(Spinner) view.findViewById(R.id.våningSpinner);
        byggår = (EditText) view.findViewById(R.id.byggÅr);
        energibesparande_ja = (RadioButton) view.findViewById(R.id.energibesparande_ja);
        getEnergibesparande_nej =(RadioButton) view.findViewById(R.id.energibesparande_nej);
        friliggande = (RadioButton) view.findViewById(R.id.friliggande);
        gavel = (RadioButton) view.findViewById(R.id.gavel);
        mellan = (RadioButton) view.findViewById(R.id.mellanHus);



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
                hashMap.put("friliggande", fri ="1");
                else
                hashMap.put("friliggande", fri ="0");
                if(gavel.isChecked())
                hashMap.put("gavelHus", gav = "1");
                else
                hashMap.put("gavelHus", gav = "0");
                if(mellan.isChecked())
                hashMap.put("mellanLiggande", mel ="1");
                else
                    hashMap.put("mellanLiggande", mel ="0");
                hashMap.put("byggnadsår", byggår.getText().toString());
                hashMap.put("owner_fastighetsNr", restoredFnr);
                hashMap.put("owner_ssn", restoredSsn);





                return hashMap;
            }
        };
        requestQueue.add(request);
    }
}
