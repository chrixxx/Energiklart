package com.venovu.energiklart;

import android.content.Intent;
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

public class Login_activity extends AppCompatActivity {
    public static final String URL = "http://venovu.com/login.php";
    private StringRequest request;

    private Button login;
    private EditText user;
    private EditText pass;
    private Button account;
    String userPassing;

    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        login = (Button) findViewById(R.id.login);
        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);


        requestQueue = Volley.newRequestQueue(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            userPassing = user.getText().toString();
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.names().get(0).equals("success")) {
                                Toast.makeText(getApplicationContext(), "SUCCESS " + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("headerUser", userPassing);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                startActivity(intent);
                                killActivity();


                                //startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                //Intent sendUser = new Intent(Login_activity.this, MainActivity.class);
                                //sendUser.putExtra("user", userPass);
                            } else {
                                Toast.makeText(getApplicationContext(), "Error" + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
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
                        hashMap.put("userName", user.getText().toString());
                        hashMap.put("userPass", pass.getText().toString());

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
