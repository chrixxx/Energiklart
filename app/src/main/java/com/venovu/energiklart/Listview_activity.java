package com.venovu.energiklart;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Listview_activity extends AppCompatActivity  {

    public static String JSON_URL = "";
    public static  String JSON_URL1 = "";
    public static final String userDetails = "userDetails" ;
    private Button buttonGet;
    private Button pdfButton;
    RequestQueue requestQueue;
    StringRequest request;
    private ListView listView;
    private EditText nameText;
    private String name;
    private String adress;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        nameText = (EditText) findViewById(R.id.owner);
        pdfButton = (Button) findViewById(R.id.pdfButton);
        buttonGet = (Button) findViewById(R.id.buttonGet);

        listView = (ListView) findViewById(R.id.listView);


        buttonGet.setOnClickListener(new View.OnClickListener() {

            @Override public void onClick(View v) {
                sendRequest();

            }
        });


                pdfButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Pdf();
                    }
                });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {




                if (position < 0) {
                    System.out.println("WRONG!!!!");

                } else {

                    alertD();
                    System.out.println("CORRECT!!!");
                    System.out.println(position);
                    System.out.println(id);

                    adress = ParseJSON.adress[position];
                    name = ParseJSON.names[position];
                    System.out.println("TEST TEST TEST >>> " + adress);



                        /**
                         TextView adresstv = (TextView) view.findViewById(R.id.lv_adress);

                         String str = (String) parent.getItemAtPosition(position);
                         String adress = adresstv.getText().toString();
                         System.out.println(str);
                         System.out.println(adress);
                         */


                    }
                }
            }

            );

        }

    /**
    public void show(String json){

        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        CustomList cl = new CustomList(this, pj.names, pj.fNr, pj.adress, pj.byggår);

        String[] name = cl.getNames();
        String[] adress = cl.getAdress();

        System.out.println(name);
        System.out.println(adress);

    }
     */



    private void sendRequest(){
        JSON_URL = "http://enegiklart.azurewebsites.net/owner" + "/" + nameText.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Listview_activity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }) ;



        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        CustomList cl = new CustomList(this, pj.names, pj.fNr, pj.adress, pj.byggår);
        listView.setAdapter(cl);

    }









    public void Pdf(){
        final PdfCreator pdfCreator = new PdfCreator();
        JSON_URL = "http://enegiklart.azurewebsites.net/owner" + "/" + nameText.getText().toString();
        requestQueue = Volley.newRequestQueue(this);

        request = new StringRequest(Request.Method.POST, JSON_URL,   new Response.Listener<String>() {




            @Override
            public void onResponse(String response) {
                try {



                    JSONArray value = new JSONArray(response);


                    for(int i=0;i<value.length();i++) {
                        JSONObject jr = (JSONObject) value.get(i);

                        String namn = jr.getString("name");
                        String fastighetsNr = jr.getString("propertyNr");
                        String adress = jr.getString("adress");
                        String buildYear = jr.getString("buildYear");



                        pdfCreator.createPDF(name, fastighetsNr, adress, buildYear);


                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {



                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("name", name);


                return hashMap;
            }
        };
        requestQueue.add(request);
    }





    //popup dialog test
    public void alertD() {
        JSON_URL1 = "http://enegiklart.azurewebsites.net/owner/" + nameText.getText().toString();
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(Listview_activity.this);
        alertdialog.setTitle("PDF/Editera");
        alertdialog.setMessage("Vad vill du göra?").setCancelable(true)

                .setNegativeButton("PDF", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        RequestQueue requestQueue;
                        final PdfCreator pdfCreator = new PdfCreator();
                        requestQueue = Volley.newRequestQueue(Listview_activity.this);

                        request = new StringRequest(Request.Method.POST, JSON_URL1,   new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONArray value = new JSONArray(response);


                                    for(int i=0;i<value.length();i++) {
                                        JSONObject jr = (JSONObject) value.get(i);

                                        String namn = jr.getString("name");
                                        String fastighetsNr = jr.getString("propertyNr");
                                        String adress = jr.getString("adress");
                                        String buildYear = jr.getString("buildYear");

                                        pdfCreator.createPDF(namn, fastighetsNr, adress, buildYear);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {



                                HashMap<String, String> hashMap = new HashMap<String, String>();
                                hashMap.put("name", name);


                                return hashMap;
                            }
                        };
                        requestQueue.add(request);























                        Toast.makeText(Listview_activity.this, "You Clicked on no", Toast.LENGTH_LONG).show();

                    }
                }).setPositiveButton("Editera", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Listview_activity.this.finish();
            }
        })
                .setNeutralButton("Avbryt", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Listview_activity.this, "You Clicked on cancel", Toast.LENGTH_LONG).show();
                    }
                });
        alertdialog.show();

    }
}







