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

    public static final String JSON_ARRAY = "result";
    public static final String KEY_NAME = "userName";
    public static final String KEY_PASS = "userPass";

    private JSONArray users = null;

    private String json;

    public ParseJSON(String json) {
        this.json = json;
    }

    protected void parseJSON() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            names = new String[users.length()];
            pass = new String[users.length()];

            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);
                names[i] = jo.getString(KEY_NAME);
                pass[i] = jo.getString(KEY_PASS);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
