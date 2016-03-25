package com.venovu.energiklart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Christoffer Nordfeldt on 2016-03-25.
 * Venovu
 * christoffer.nordfeldt@venovu.com
 */
public class ParseJSON {

    public static String[] names;
    public static String[] pass;


    public static final String KEY_NAME = "userName";
    public static final String KEY_PASS = "userPass";

    private JSONArray users = null;

    private String json;

    public ParseJSON(String json) {
        this.json = json;
    }

    protected void parseJSON() {

        try {
            JSONArray users= new JSONArray(json);


            names = new String[users.length()];
            pass = new String[users.length()];
            JSONObject jsonObject = users.getJSONObject(0);

            for (int i = 0; i < users.length(); i++) {
                JSONObject jr = (JSONObject) users.get(i);
                names[i] = jr.getString(KEY_NAME);
                pass[i] = jr.getString(KEY_PASS);
                System.out.println(pass[i]);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
