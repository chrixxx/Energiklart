package com.venovu.energiklart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class Create_account extends AppCompatActivity {

    public static final String URL = "http://venovu.com/Register.php";
    private StringRequest request;

    private Button create_button;
    private EditText user_create;
    private EditText email_create;
    private EditText phone_create;
    private EditText password_create;
    private String root = "0";


    /**
    public static final String userDetails = "userDetails" ;
    public static final String userName = "nameKey";
    public static final String userPass = "passKey";
    public static final String logg = "loggKey";
    */

    private RequestQueue requestQueue;
    SharedPreferences sharedpreferences;
    String userPassing;
    Boolean loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);



        create_button = (Button) findViewById(R.id.create_button);
        user_create = (EditText) findViewById(R.id.user_create);
        email_create = (EditText) findViewById(R.id.email_create);
        phone_create = (EditText) findViewById(R.id.phone_create);
        password_create = (EditText) findViewById(R.id.password_create);

        //sharedpreferences = getSharedPreferences(userDetails, Context.MODE_PRIVATE);
        requestQueue = Volley.newRequestQueue(this);

        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            //userPassing = user.getText().toString();
                            JSONObject jsonObject = new JSONObject(response);

                             if (jsonObject.names().get(0).equals("success")) {
                                Toast.makeText(getApplicationContext(), "SUCCESS " + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login_activity.class);

                                 /**
                                //shared preferences för username och password för att kunna skapa kund och hus
                                String n = user.getText().toString();
                                String u = pass.getText().toString();
                                loggedIn = true;
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.clear().commit();
                                editor.putBoolean(logg, loggedIn);
                                editor.putString(userName, n);
                                editor.putString(userPass, u);

                                editor.commit();
                                  */

                             /**
                                //För över username till navigation menu och visar vilken som är inloggad
                                intent.putExtra("headerUser", userPassing);

                              */
                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(intent);
                                killActivity();



                                //startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                //Intent sendUser = new Intent(Login_activity.this, MainActivity.class);
                                //sendUser.putExtra("user", userPass);
                            } else {
                                Toast.makeText(getApplicationContext(), "Error" + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                                loggedIn = false;
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.clear().apply();

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
                        hashMap.put("userName", user_create.getText().toString());
                        hashMap.put("userPass", password_create.getText().toString());
                        hashMap.put("telefon", phone_create.getText().toString());
                        hashMap.put("mail", email_create.getText().toString());
                        hashMap.put("root", root);



                        return hashMap;
                    }
                };
                requestQueue.add(request);
            }
        });

    }

    private void killActivity(){
        finish();
    }
}
