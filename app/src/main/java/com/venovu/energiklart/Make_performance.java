package com.venovu.energiklart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Make_performance extends AppCompatActivity {
    private EditText ownerName;
    private EditText ssn;
    private EditText propertyNr;
    private EditText adress;
    private EditText postNr;
    private EditText postOrt;
    private EditText fakturAd;
    private EditText phone;
    private EditText cellphone;
    private EditText mail;
    private EditText tempVinter;
    private EditText tempVinterKa;
    private EditText inhabitants;
    private EditText buildYear;
    private EditText floors;
    private CheckBox faktura10;
    private CheckBox faktura30;
    private CheckBox bg;
    private CheckBox basement;
    private CheckBox gabel;
    private CheckBox free;
    private CheckBox middle;
    private Button insertButton;

    StringRequest request;

    public static  String URL = "http://venovu.com/registerKund.php";

    RequestQueue requestQueue;

    public static final String userDetails = "userDetails" ;
    public static final String socialNr = "ssnKey";
    public static final String fastighetNr = "fastighetKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_performance);

        insertButton =(Button)findViewById(R.id.insertButton);
       //Edittexts
        ownerName = (EditText)findViewById(R.id.ownerName);
       ssn = (EditText)findViewById(R.id.ssn);
       propertyNr = (EditText)findViewById(R.id.property);
       adress = (EditText)findViewById(R.id.adress);
       postNr = (EditText)findViewById(R.id.postNr);
       postOrt = (EditText)findViewById(R.id.postOrt);
        fakturAd = (EditText)findViewById(R.id.fakturaAd);
       phone = (EditText)findViewById(R.id.phone);
        cellphone= (EditText)findViewById(R.id.cellphone);
       mail = (EditText)findViewById(R.id.mail);
        tempVinter= (EditText)findViewById(R.id.tempVinter);
        tempVinterKa= (EditText)findViewById(R.id.tempVinterKa);
        inhabitants= (EditText)findViewById(R.id.inhabitants);
       buildYear = (EditText)findViewById(R.id.buildYear);
        floors= (EditText)findViewById(R.id.floors);

        //Checkboxes
       faktura10 =(CheckBox)findViewById(R.id.faktura10);
       faktura30 =(CheckBox)findViewById(R.id.faktura30);
       bg =(CheckBox)findViewById(R.id.bg);
       basement =(CheckBox)findViewById(R.id.basement);
       gabel =(CheckBox)findViewById(R.id.gabel);
       free =(CheckBox)findViewById(R.id.free);
       middle =(CheckBox)findViewById(R.id.middle);

       insertButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                InsertKund();
           }
       });
    }



    public void InsertKund() {
        URL = "http://enegiklart.azurewebsites.net/user";
        SharedPreferences prefs = getSharedPreferences(userDetails, Context.MODE_PRIVATE);
        String restoredName = prefs.getString("nameKey", null);
        String restoredPass = prefs.getString("passKey",null);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("ssn", ssn.getText().toString());
        params.put("name", ownerName.getText().toString());
        params.put("homePhone", phone.getText().toString());
        params.put("adress", adress.getText().toString());
        params.put("postNr", postNr.getText().toString());
        params.put("mobile", cellphone.getText().toString());
        params.put("mail", mail.getText().toString());
        params.put("user_userName", restoredName);
        params.put("user_userPass", restoredPass);
        params.put("propertyNr", propertyNr.getText().toString());
        params.put("fakturaAdress", fakturAd.getText().toString());
        params.put("postOrt", postOrt.getText().toString());
        params.put("fakturaNr", "40");
        if (faktura10.isChecked())
            params.put("faktura10", "1");
        else
            params.put("faktura10", "0");
        if (faktura30.isChecked())
            params.put("faktura30", "1");
        else
            params.put("faktura30", "0");
        params.put("otherFakturor", "0");
        //Sparar inskrivna fastighetnr och SSN till shared preferences
        String fastighet = propertyNr.getText().toString();
        String ssNr = ssn.getText().toString();

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(fastighetNr, fastighet);
        editor.putString(socialNr, ssNr);


        editor.commit();




        JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v(response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        requestQueue.add(req);
    }

    public void InsertHouse(){

        URL = "http://enegiklart.azurewebsites.net/user";
        SharedPreferences prefs = getSharedPreferences(userDetails, Context.MODE_PRIVATE);
        String restoredName = prefs.getString("nameKey", null);
        String restoredPass = prefs.getString("passKey", null);
        String restoredFnr = prefs.getString("fastighetKey", null);
        String restoredSsn = prefs.getString("ssnKey", null);

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("user_userName", restoredName);
        hashMap.put("user_userPass", restoredPass);
        hashMap.put("floors", floors.getText().toString());
        hashMap.put("residentials", inhabitants.getText().toString());

        if(free.isChecked())
            hashMap.put("detached", "1");
        else
            hashMap.put("detached", "0");

        if(gabel.isChecked())
            hashMap.put("gabled", "1");
        else
            hashMap.put("gabled", "0");

        if(middle.isChecked())
            hashMap.put("intermediate", "1");
        else
            hashMap.put("intermediate", "0");
        hashMap.put("buildYear", buildYear.getText().toString());
        hashMap.put("owner_fastighetsNr", restoredFnr);
        hashMap.put("owner_ssn", restoredSsn);
        hashMap.put("date", null);
        hashMap.put("owner_ssn", "1");









        JsonObjectRequest req = new JsonObjectRequest(URL, new JSONObject(hashMap),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v(response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        requestQueue.add(req);

    }





    @Override
    public void onBackPressed(){

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

        super.onBackPressed();

    }
}
