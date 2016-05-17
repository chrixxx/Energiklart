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

public class Listview_activity extends AppCompatActivity implements View.OnClickListener {

    public static final String JSON_URL = "http://venovu.com/fetchHouseData.php";
    public static final String JSON_URL1 = "http://venovu.com/fetchOwner.php";
    public static final String userDetails = "userDetails" ;
    private Button buttonGet;
    private Button pdfButton;
    RequestQueue requestQueue;
    StringRequest request;
    private ListView listView;
    private EditText nameText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        nameText = (EditText) findViewById(R.id.owner);
        pdfButton = (Button) findViewById(R.id.pdfButton);
        buttonGet = (Button) findViewById(R.id.buttonGet);
        buttonGet.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);



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



                    TextView adresstv = (TextView) view.findViewById(R.id.lv_adress);

                





                    String str = (String) parent.getItemAtPosition(position);
                    String adress = adresstv.getText().toString();
                    System.out.println(str);
                    System.out.println(adress);


                }
            }
        });

    }



    private void sendRequest(){

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

    @Override
    public void onClick(View v) {
        sendRequest();
    }



    public void Pdf(){
        final PdfCreator pdfCreator = new PdfCreator();
        requestQueue = Volley.newRequestQueue(this);

        request = new StringRequest(Request.Method.POST, JSON_URL1,   new Response.Listener<String>() {




            @Override
            public void onResponse(String response) {
                try {



                    JSONArray value = new JSONArray(response);


                    for(int i=0;i<value.length();i++) {
                        JSONObject jr = (JSONObject) value.get(i);

                        String namn = jr.getString("namn");
                        String fastighetsNr = jr.getString("fastighetsNr");
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
                hashMap.put("namn", nameText.getText().toString());


                return hashMap;
            }
        };
        requestQueue.add(request);
    }



    //popup dialog test
    public void alertD() {

        AlertDialog.Builder alertdialog = new AlertDialog.Builder(Listview_activity.this);
        alertdialog.setTitle("PDF/Editera");
        alertdialog.setMessage("Vad vill du göra?").setCancelable(true)

                .setNegativeButton("PDF", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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







